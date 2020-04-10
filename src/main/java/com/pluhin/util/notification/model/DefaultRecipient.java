package com.pluhin.util.notification.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DefaultRecipient implements Recipient {

  private final RecipientType type;
  private final String address;

  @JsonCreator
  public DefaultRecipient(
      @JsonProperty("type") RecipientType type,
      @JsonProperty("address") String address
  ) {
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
