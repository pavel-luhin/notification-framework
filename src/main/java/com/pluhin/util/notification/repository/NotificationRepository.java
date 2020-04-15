package com.pluhin.util.notification.repository;

import com.pluhin.util.notification.model.NotificationEntity;

public interface NotificationRepository {

  void save(NotificationEntity entity);
}
