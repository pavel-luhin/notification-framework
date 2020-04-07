package com.pluhin.util.notification.model;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface NotificationRequest {

  Recipient getRecipient();

  String getTemplateName();

  Map<String, String> getParams();

  List<File> getAttachments();
}
