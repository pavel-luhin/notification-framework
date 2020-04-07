package com.pluhin.util.notification.sender;

import com.pluhin.util.notification.model.EmailNotification;
import com.pluhin.util.notification.model.Notification;
import com.pluhin.util.notification.model.Recipient;
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

public class EmailNotificationSender implements NotificationSender {

  private final Boolean hasSsl;
  private final String host;
  private final String port;
  private final String username;
  private final String password;
  private final String from;

  public EmailNotificationSender(Boolean hasSsl, String host, String port, String username, String password,
      String from) {
    this.hasSsl = hasSsl;
    this.host = host;
    this.port = port;
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

    Properties prop = new Properties();
    prop.put("mail.smtp.auth", true);
    prop.put("mail.smtp.host", host);
    prop.put("mail.smtp.port", port);
    prop.put("mail.smtp.ssl.trust", host);

    if (hasSsl) {
      prop.put("mail.smtp.starttls.enable", "true");
    }

    Session session = Session.getInstance(prop, new Authenticator() {
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

    }
  }
}
