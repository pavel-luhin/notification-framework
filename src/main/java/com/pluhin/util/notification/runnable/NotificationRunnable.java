package com.pluhin.util.notification.runnable;

import com.pluhin.util.notification.model.Notification;
import com.pluhin.util.notification.model.NotificationRequest;
import com.pluhin.util.notification.model.Recipient;
import com.pluhin.util.notification.model.Template;
import com.pluhin.util.notification.processor.TemplateProcessor;
import com.pluhin.util.notification.repository.TemplateRepository;
import com.pluhin.util.notification.sender.NotificationSender;

public class NotificationRunnable implements Runnable {

  private final TemplateRepository templateRepository;
  private final TemplateProcessor templateProcessor;
  private final NotificationSender sender;
  private final NotificationRequest request;

  public NotificationRunnable(TemplateRepository templateRepository,
      TemplateProcessor templateProcessor, NotificationSender sender,
      NotificationRequest request) {
    this.templateRepository = templateRepository;
    this.templateProcessor = templateProcessor;
    this.sender = sender;
    this.request = request;
  }

  @Override
  public void run() {
    Template template = templateRepository.findTemplate(request.getTemplateName());
    Notification notification = templateProcessor.process(template, request.getParams());
    Recipient recipient = request.getRecipient();
    sender.send(notification, request.getRecipient(), request.getAttachments());
  }
}
