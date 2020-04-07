package com.pluhin.util.notification.processor;

import com.pluhin.util.notification.builder.TemplateBuilder;
import com.pluhin.util.notification.model.DefaultEmailNotification;
import com.pluhin.util.notification.model.EmailNotification;
import com.pluhin.util.notification.model.EmailTemplate;
import com.pluhin.util.notification.model.Template;
import java.util.Map;

public class DefaultEmailTemplateProcessor implements TemplateProcessor {

  private final TemplateBuilder templateBuilder;

  public DefaultEmailTemplateProcessor(TemplateBuilder templateBuilder) {
    this.templateBuilder = templateBuilder;
  }

  @Override
  public EmailNotification process(Template template, Map<String, String> params) {
    if (!(template instanceof EmailTemplate)) {
      throw new RuntimeException("EmailTemplateProcessor cannot handle non email templates");
    }

    EmailTemplate emailTemplate = (EmailTemplate) template;
    String body = templateBuilder.build(emailTemplate.getTemplate(), params);
    String subject = templateBuilder.build(emailTemplate.getSubject(), params);
    return new DefaultEmailNotification(body, subject);
  }
}
