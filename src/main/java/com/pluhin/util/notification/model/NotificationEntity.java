package com.pluhin.util.notification.model;

import java.time.LocalDateTime;

public interface NotificationEntity {

  String address();

  RecipientType getType();

  String getTemplateName();

  LocalDateTime dateSent();
}
