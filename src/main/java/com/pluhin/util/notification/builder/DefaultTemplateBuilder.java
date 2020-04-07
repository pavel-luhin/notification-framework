package com.pluhin.util.notification.builder;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultTemplateBuilder implements TemplateBuilder {

  @Override
  public String build(String template, Map<String, String> parameters) {
    String regex = "\\$\\{([^}]+)\\}";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(template);
    String result = template;
    while (matcher.find()) {
      String token = matcher.group();
      String replacementValue = null;
      if (parameters.containsKey(token)) {
        replacementValue = parameters.get(token);
      } else {
        throw new IllegalArgumentException("No parameter with name " + token);
      }

      result = result.replaceFirst(Pattern.quote(token), replacementValue);
    }

    return result;
  }
}
