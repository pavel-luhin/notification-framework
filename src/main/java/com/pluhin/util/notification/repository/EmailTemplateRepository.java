package com.pluhin.util.notification.repository;

import com.pluhin.util.notification.model.EmailTemplate;

public interface EmailTemplateRepository extends TemplateRepository {

  @Override
  EmailTemplate findTemplate(String templateName);
}
