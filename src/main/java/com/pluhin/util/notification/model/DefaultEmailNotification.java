package com.pluhin.util.notification.model;

public class DefaultEmailNotification implements EmailNotification {

  private final String text;
  private final String subject;

  public DefaultEmailNotification(String text, String subject) {
    this.text = text;
    this.subject = subject;
  }

  @Override
  public String getText() {
    return text;
  }

  @Override
  public String getSubject() {
    return subject;
  }
}
