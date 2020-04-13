package com.pluhin.util.notification.exception;

public class CannotSendNotificationException extends RuntimeException {

  public CannotSendNotificationException(String message) {
    super(message);
  }

  public CannotSendNotificationException(String message, Throwable cause) {
    super(message, cause);
  }
}
