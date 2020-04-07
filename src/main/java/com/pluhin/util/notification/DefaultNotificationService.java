package com.pluhin.util.notification;

import com.pluhin.util.notification.model.Notification;
import com.pluhin.util.notification.model.NotificationRequest;
import com.pluhin.util.notification.model.Template;
import com.pluhin.util.notification.processor.TemplateProcessor;
import com.pluhin.util.notification.repository.TemplateRepository;
import com.pluhin.util.notification.sender.NotificationSender;

public class DefaultNotificationService implements NotificationService {

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
    sender.send(notification, request.getRecipient(), request.getAttachments());
  }
}
