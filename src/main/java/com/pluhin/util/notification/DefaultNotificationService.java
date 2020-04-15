package com.pluhin.util.notification;

import com.pluhin.util.notification.exception.CannotFindTemplateException;
import com.pluhin.util.notification.model.DefaultNotificationEntity;
import com.pluhin.util.notification.model.Notification;
import com.pluhin.util.notification.model.NotificationEntity;
import com.pluhin.util.notification.model.NotificationRequest;
import com.pluhin.util.notification.model.Template;
import com.pluhin.util.notification.processor.TemplateProcessor;
import com.pluhin.util.notification.repository.TemplateRepository;
import com.pluhin.util.notification.sender.NotificationSender;
import java.time.LocalDateTime;

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
  public NotificationEntity send(NotificationRequest request) {
    Template template = templateRepository.findTemplate(request.getTemplateName());
    if (template == null) {
      throw new CannotFindTemplateException("Template " + request.getTemplateName() + " could not be found");
    }

    Notification notification = templateProcessor.process(template, request.getParams());
    sender.send(notification, request.getRecipient(), request.getAttachments());
    return new DefaultNotificationEntity(
        request.getRecipient().getAddress(),
        request.getRecipient().getType(),
        request.getTemplateName(),
        LocalDateTime.now()
    );
  }
}
