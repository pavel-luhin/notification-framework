package com.pluhin.util.notification.model;

public class DefaultRecipient implements Recipient {

  private final RecipientType type;
  private final String address;

  public DefaultRecipient(RecipientType type, String address) {
    this.type = type;
    this.address = address;
  }

  @Override
  public RecipientType getType() {
    return type;
  }

  @Override
  public String getAddress() {
    return address;
  }
}
