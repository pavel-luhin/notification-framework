package com.pluhin.util.notification.sender;

import com.pluhin.util.notification.model.EmailNotification;
import com.pluhin.util.notification.model.Notification;
import com.pluhin.util.notification.model.Recipient;
import com.pluhin.util.notification.exception.CannotSendNotificationException;
import java.io.File;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailNotificationSender implements NotificationSender {

  private static final Logger LOGGER = LoggerFactory.getLogger(EmailNotificationSender.class);

  private final Properties properties;
  private final String username;
  private final String password;
  private final String from;

  public EmailNotificationSender(boolean hasSsl, String host, String port, String username, String password,
      String from) {
    Properties copy = new Properties();
    copy.put("mail.smtp.auth", true);
    copy.put("mail.smtp.host", host);
    copy.put("mail.smtp.port", port);
    if (hasSsl) {
      copy.put("mail.smtp.ssl.trust", host);
      copy.put("mail.smtp.starttls.enable", "true");
    }

    this.properties = new Properties(copy);
    this.username = username;
    this.password = password;
    this.from = from;
  }

  @Override
  public void send(Notification notification, Recipient recipient, List<File> attachments) {
    if (!(notification instanceof EmailNotification)) {
      throw new RuntimeException("EmailNotificationSender cannot handle non email notifications");
    }

    EmailNotification emailNotification = (EmailNotification) notification;

    Session session = Session.getInstance(properties, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
      }
    });

    try {
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(from));
      message.setRecipients(
          Message.RecipientType.TO, InternetAddress.parse(recipient.getAddress()));
      message.setSubject(emailNotification.getSubject());

      MimeBodyPart mimeBodyPart = new MimeBodyPart();
      mimeBodyPart.setContent(emailNotification.getText(), "text/html");

      Multipart multipart = new MimeMultipart();
      multipart.addBodyPart(mimeBodyPart);

      MimeBodyPart attachmentBodyPart = new MimeBodyPart();
      for (File file : attachments) {
        attachmentBodyPart.attachFile(file);
      }
      multipart.addBodyPart(attachmentBodyPart);

      message.setContent(multipart);

      Transport.send(message);
    } catch (Exception ex) {
      LOGGER.error("Cannot send email to {} due to exception {}", recipient.getAddress(), ex);
      throw new CannotSendNotificationException("Cannot send email to " + recipient.getAddress(), ex);
    }
  }
}
