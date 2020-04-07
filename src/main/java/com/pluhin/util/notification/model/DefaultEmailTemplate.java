package com.pluhin.util.notification.model;

public class DefaultEmailTemplate implements EmailTemplate {

  private final String subject;
  private final String template;

  public DefaultEmailTemplate(String subject, String template) {
    this.subject = subject;
    this.template = template;
  }

  @Override
  public String getSubject() {
    return subject;
  }

  @Override
  public String getTemplate() {
    return template;
  }
}
