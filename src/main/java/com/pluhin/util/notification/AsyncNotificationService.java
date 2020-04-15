package com.pluhin.util.notification;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.pluhin.util.notification.model.NotificationEntity;
import com.pluhin.util.notification.model.NotificationRequest;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AsyncNotificationService implements NotificationService {

  private static final Logger LOGGER = LoggerFactory.getLogger(AsyncNotificationService.class);

  private final NotificationService delegate;
  private final ExecutorService executor;

  public AsyncNotificationService(NotificationService delegate) {
    this.delegate = delegate;

    ThreadFactory threadFactory = new ThreadFactoryBuilder()
        .setNameFormat("notification-%s")
        .build();
    this.executor = Executors.newFixedThreadPool(10, threadFactory);
  }

  @Override
  public NotificationEntity send(NotificationRequest notifications) {
    try {
      return executor.submit(() -> delegate.send(notifications)).get();
    } catch (InterruptedException e) {
      LOGGER.error("Thread interrupted");
    } catch (ExecutionException e) {
      LOGGER.error("Exception while execution", e);
    }
    return null;
  }
}
