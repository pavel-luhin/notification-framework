package com.pluhin.util.notification.model;

public interface Recipient {

  RecipientType getType();

  String getAddress();
}
