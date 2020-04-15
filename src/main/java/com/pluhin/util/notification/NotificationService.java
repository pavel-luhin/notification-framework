package com.pluhin.util.notification;

import com.pluhin.util.notification.model.NotificationEntity;
import com.pluhin.util.notification.model.NotificationRequest;
import java.util.List;

public interface NotificationService {

  default void send(List<NotificationRequest> notificationRequests) {
    notificationRequests.forEach(this::send);
  }

  NotificationEntity send(NotificationRequest notifications);
}
