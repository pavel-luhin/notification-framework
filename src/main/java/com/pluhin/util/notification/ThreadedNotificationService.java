package com.pluhin.util.notification;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.pluhin.util.notification.model.NotificationRequest;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ThreadedNotificationService implements NotificationService {

  private final NotificationService delegate;
  private final Executor executor;

  public ThreadedNotificationService(NotificationService delegate) {
    this.delegate = delegate;
    ThreadFactory threadFactory = new ThreadFactoryBuilder()
        .setNameFormat("notification-%s")
        .build();
    this.executor = Executors.newFixedThreadPool(10, threadFactory);
  }

  @Override
  public void send(NotificationRequest notifications) {
    executor.execute(() -> delegate.send(notifications));
  }
}
