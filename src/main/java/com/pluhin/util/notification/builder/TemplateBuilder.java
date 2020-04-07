package com.pluhin.util.notification.builder;

import java.util.Map;

public interface TemplateBuilder {

  String build(String template, Map<String, String> parameters);
}
