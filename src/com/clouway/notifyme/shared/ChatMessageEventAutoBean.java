package com.clouway.notifyme.shared;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public interface ChatMessageEventAutoBean extends PushChannelEvent {

  void setMessage(String message);

  String getMessage();

  void setEventName(String eventName);

  String getEventName();
}
