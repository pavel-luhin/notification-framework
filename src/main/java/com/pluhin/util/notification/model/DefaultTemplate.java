package com.pluhin.util.notification.model;

public class DefaultTemplate implements Template {

  private final String template;

  public DefaultTemplate(String template) {
    this.template = template;
  }

  @Override
  public String getTemplate() {
    return template;
  }
}
