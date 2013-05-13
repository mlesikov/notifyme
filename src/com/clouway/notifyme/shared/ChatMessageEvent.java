package com.clouway.notifyme.shared;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public class ChatMessageEvent implements PushChannelEvent {

  public String getEventName() {
    return this.getClass().toString();
  }
}
