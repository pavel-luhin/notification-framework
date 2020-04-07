package com.pluhin.util.notification.model;

import java.io.File;
import java.util.List;
import java.util.Map;

public class DefaultNotificationRequest implements NotificationRequest {

  private final Recipient recipient;
  private final String templateName;
  private final Map<String, String> params;
  private final List<File> attachments;

  public DefaultNotificationRequest(
      Recipient recipient,
      String templateName,
      Map<String, String> params,
      List<File> attachments
  ) {
    this.recipient = recipient;
    this.templateName = templateName;
    this.params = params;
    this.attachments = attachments;
  }

  @Override
  public Recipient getRecipient() {
    return recipient;
  }

  @Override
  public String getTemplateName() {
    return templateName;
  }

  @Override
  public Map<String, String> getParams() {
    return params;
  }

  @Override
  public List<File> getAttachments() {
    return attachments;
  }
}
