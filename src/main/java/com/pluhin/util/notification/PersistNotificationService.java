package com.pluhin.util.notification;

import com.pluhin.util.notification.model.NotificationEntity;
import com.pluhin.util.notification.model.NotificationRequest;
import com.pluhin.util.notification.repository.NotificationRepository;

public class PersistNotificationService implements NotificationService {

  private final NotificationService delegate;
  private final NotificationRepository repository;

  public PersistNotificationService(NotificationService delegate,
      NotificationRepository repository) {
    this.delegate = delegate;
    this.repository = repository;
  }

  @Override
  public NotificationEntity send(NotificationRequest notifications) {
    NotificationEntity entity = delegate.send(notifications);
    repository.save(entity);
    return entity;
  }
}
