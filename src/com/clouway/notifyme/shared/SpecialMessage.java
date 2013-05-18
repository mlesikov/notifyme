package com.clouway.notifyme.shared;

import java.io.Serializable;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public class SpecialMessage implements Serializable {

  private String message;

  public SpecialMessage() {
  }

  public SpecialMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
