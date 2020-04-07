package com.pluhin.util.notification.repository;

import com.pluhin.util.notification.model.Template;

public interface TemplateRepository {

  Template findTemplate(String templateName);
}
