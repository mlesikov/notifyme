package com.clouway.notifyme.shared;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public class ChatMessageEvent implements PushChannelEvent {

  private String message;


  public ChatMessageEvent() {
  }

  public ChatMessageEvent(String message) {
    this.message = message;
  }

  public String getEventName() {
    return "ChatMessageEvent";
  }

  public String getMessage() {
    return message;
  }
}
