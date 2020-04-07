package com.pluhin.util.notification.sender;

import com.pluhin.util.notification.model.Notification;
import com.pluhin.util.notification.model.Recipient;
import java.io.File;
import java.util.List;

public interface NotificationSender {

  void send(Notification notification, Recipient recipient, List<File> attachments);
}
