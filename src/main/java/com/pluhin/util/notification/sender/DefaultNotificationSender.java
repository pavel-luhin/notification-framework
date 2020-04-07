package com.pluhin.util.notification.sender;

import com.pluhin.util.notification.model.Notification;
import com.pluhin.util.notification.model.Recipient;
import com.pluhin.util.notification.provider.SenderProvider;
import java.io.File;
import java.util.List;

public class DefaultNotificationSender implements NotificationSender {

  private final SenderProvider senderProvider;

  public DefaultNotificationSender(SenderProvider senderProvider) {
    this.senderProvider = senderProvider;
  }

  @Override
  public void send(Notification notification, Recipient recipient, List<File> attachments) {
    senderProvider.send(notification.getText(), recipient.getAddress(), attachments);
  }
}
