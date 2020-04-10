package com.pluhin.util.notification;

import com.pluhin.util.notification.model.Notification;
import com.pluhin.util.notification.model.NotificationRequest;
import com.pluhin.util.notification.model.Recipient;
import com.pluhin.util.notification.model.Template;
import com.pluhin.util.notification.processor.TemplateProcessor;
import com.pluhin.util.notification.repository.TemplateRepository;
import com.pluhin.util.notification.sender.NotificationSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultNotificationService implements NotificationService {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultNotificationService.class);

  private final TemplateRepository templateRepository;
  private final TemplateProcessor templateProcessor;
  private final NotificationSender sender;

  public DefaultNotificationService(TemplateRepository templateRepository,
      TemplateProcessor templateProcessor, NotificationSender sender) {
    this.templateRepository = templateRepository;
    this.templateProcessor = templateProcessor;
    this.sender = sender;
  }

  @Override
  public void send(NotificationRequest request) {
    Template template = templateRepository.findTemplate(request.getTemplateName());
    Notification notification = templateProcessor.process(template, request.getParams());
    Recipient recipient = request.getRecipient();
    LOGGER.info("Sending {} notification to {} with name {}",
        recipient.getType(),
        recipient.getAddress(),
        request.getTemplateName()
    );
    sender.send(notification, request.getRecipient(), request.getAttachments());
  }
}
