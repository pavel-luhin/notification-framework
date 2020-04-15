package com.pluhin.util.notification;

import com.pluhin.util.notification.model.NotificationEntity;
import com.pluhin.util.notification.model.NotificationRequest;
import com.pluhin.util.notification.model.Recipient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingNotificationService implements NotificationService {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoggingNotificationService.class);

  private final NotificationService delegate;

  public LoggingNotificationService(NotificationService delegate) {
    this.delegate = delegate;
  }

  @Override
  public NotificationEntity send(NotificationRequest notification) {
    Recipient recipient = notification.getRecipient();
    LOGGER.info("Sending {} notification to {} with name {}",
        recipient.getType(),
        recipient.getAddress(),
        notification.getTemplateName()
    );
    return delegate.send(notification);
  }
}
