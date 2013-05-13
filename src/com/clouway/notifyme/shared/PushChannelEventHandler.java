package com.clouway.notifyme.shared;

import com.clouway.notifyme.shared.ChatMessageEvent;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public interface PushChannelEventHandler {

  void onMessage(ChatMessageEvent event);
}
