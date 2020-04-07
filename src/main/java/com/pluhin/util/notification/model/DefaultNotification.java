package com.pluhin.util.notification.model;

public class DefaultNotification implements Notification {

  private final String text;

  public DefaultNotification(String text) {
    this.text = text;
  }

  @Override
  public String getText() {
    return text;
  }
}
