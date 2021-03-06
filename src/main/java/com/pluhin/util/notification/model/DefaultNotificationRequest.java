package com.pluhin.util.notification.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DefaultNotificationRequest implements NotificationRequest {

  private final Recipient recipient;
  private final String templateName;
  private final Map<String, String> params;
  private final List<File> attachments;

  @JsonCreator
  public DefaultNotificationRequest(
      @JsonProperty("recipient") Recipient recipient,
      @JsonProperty("templateName") String templateName,
      @JsonProperty("params") Map<String, String> params,
      @JsonProperty("attachments") List<File> attachments
  ) {
    this.recipient = recipient;
    this.templateName = templateName;
    this.params = params;
    this.attachments = attachments;
  }

  public DefaultNotificationRequest(Recipient recipient, String templateName,
      Map<String, String> params) {
    this.recipient = recipient;
    this.templateName = templateName;
    this.params = params;
    this.attachments = Collections.emptyList();
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
