package com.pluhin.util.notification;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.pluhin.util.notification.model.NotificationRequest;
import com.pluhin.util.notification.processor.TemplateProcessor;
import com.pluhin.util.notification.repository.TemplateRepository;
import com.pluhin.util.notification.runnable.NotificationRunnable;
import com.pluhin.util.notification.sender.NotificationSender;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class DefaultNotificationService implements NotificationService {

  private final Executor executor;

  private final TemplateRepository templateRepository;
  private final TemplateProcessor templateProcessor;
  private final NotificationSender sender;

  public DefaultNotificationService(TemplateRepository templateRepository,
      TemplateProcessor templateProcessor, NotificationSender sender) {
    this.templateRepository = templateRepository;
    this.templateProcessor = templateProcessor;
    this.sender = sender;

    ThreadFactory threadFactory = new ThreadFactoryBuilder()
        .setNameFormat("notification-%s")
        .build();
    this.executor = Executors.newFixedThreadPool(10, threadFactory);
  }

  @Override
  public void send(NotificationRequest request) {
    Runnable runnable = new NotificationRunnable(templateRepository, templateProcessor, sender, request);
    executor.execute(runnable);
  }
}
