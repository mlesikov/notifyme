package com.clouway.notifyme.shared;

import java.util.Date;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public class ChatMessageEvent implements PushChannelEvent {

  private String sender;

  private String message;

  private Date createdOn;

  public ChatMessageEvent() {
  }

  public ChatMessageEvent(String sender, String message, Date createdOn) {
    this.sender = sender;
    this.message = message;
    this.createdOn = createdOn;
  }

  public String getEventName() {
    return "ChatMessageEvent";
  }

  public String getMessage() {
    return message;
  }

  public String getSender() {
    return sender;
  }

  public Date getCreatedOn() {
    return createdOn;
  }
}
