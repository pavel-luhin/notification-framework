package com.pluhin.util.notification;

import com.pluhin.util.notification.model.NotificationEntity;
import com.pluhin.util.notification.model.NotificationRequest;
import com.pluhin.util.notification.model.RecipientType;
import java.util.Map;

public class DictionaryNotificationService implements NotificationService {

  private final Map<RecipientType, NotificationService> dictionary;

  public DictionaryNotificationService(
      Map<RecipientType, NotificationService> dictionary) {
    this.dictionary = dictionary;
  }

  @Override
  public NotificationEntity send(NotificationRequest notificationRequest) {
    RecipientType type = notificationRequest.getRecipient().getType();

    return dictionary.get(type).send(notificationRequest);
  }
}
