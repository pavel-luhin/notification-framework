package com.pluhin.util.notification;

import com.pluhin.util.notification.model.NotificationRequest;
import java.util.List;

public interface NotificationService {

  default void send(List<NotificationRequest> notificationRequests) {
    notificationRequests.forEach(this::send);
  }

  void send(NotificationRequest notifications);
}
