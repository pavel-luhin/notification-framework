package com.pluhin.util.notification.provider;

import java.io.File;
import java.util.List;

public interface SenderProvider {

  void send(String text, String address, List<File> attachments);
}
