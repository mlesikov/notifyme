package com.clouway.notifyme.shared;

import com.clouway.notifyme.shared.spi.PushChannelEvent;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public class SpecialMessageEvent implements PushChannelEvent {

  private SpecialMessage specialMessage;

  public SpecialMessageEvent() {
  }

  public SpecialMessageEvent(SpecialMessage specialMessage) {
    this.specialMessage = specialMessage;
  }

  public SpecialMessage getSpecialMessage() {
    return specialMessage;
  }

  @Override
  public String getEventName() {
    return "SpecialMessageEvent";
  }
}
