package com.pluhin.util.notification.processor;

import com.pluhin.util.notification.model.Notification;
import com.pluhin.util.notification.model.Template;
import java.util.Map;

public interface TemplateProcessor {

  Notification process(Template template, Map<String, String> params);
}
