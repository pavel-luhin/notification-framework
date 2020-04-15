package com.pluhin.util.notification.model;

import java.time.LocalDateTime;

public class DefaultNotificationEntity implements NotificationEntity {

  private final String address;
  private final RecipientType type;
  private final String templateName;
  private final LocalDateTime dateSent;

  public DefaultNotificationEntity(String address, RecipientType type, String templateName,
      LocalDateTime dateSent) {
    this.address = address;
    this.type = type;
    this.templateName = templateName;
    this.dateSent = dateSent;
  }

  @Override
  public String address() {
    return address;
  }

  @Override
  public RecipientType getType() {
    return type;
  }

  @Override
  public String getTemplateName() {
    return templateName;
  }

  @Override
  public LocalDateTime dateSent() {
    return dateSent;
  }
}
