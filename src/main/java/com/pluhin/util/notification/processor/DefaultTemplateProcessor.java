package com.pluhin.util.notification.processor;

import com.pluhin.util.notification.builder.TemplateBuilder;
import com.pluhin.util.notification.model.DefaultNotification;
import com.pluhin.util.notification.model.Notification;
import com.pluhin.util.notification.model.Template;
import java.util.Map;

public class DefaultTemplateProcessor implements TemplateProcessor {

  private final TemplateBuilder templateBuilder;

  public DefaultTemplateProcessor(TemplateBuilder templateBuilder) {
    this.templateBuilder = templateBuilder;
  }

  @Override
  public Notification process(Template template, Map<String, String> params) {
    String text = templateBuilder.build(template.getTemplate(), params);
    return new DefaultNotification(text);
  }
}
