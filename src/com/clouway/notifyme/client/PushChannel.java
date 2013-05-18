package com.clouway.notifyme.client;

import com.clouway.notifyme.shared.PushChannelEvent;
import com.clouway.notifyme.shared.PushChannelEventHandler;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public interface PushChannel {

  void connect(String username, ConnectionListener connectionListener);

  void subscribe(String username, PushChannelEvent event, PushChannelEventHandler eventHandler);

  void unsubscribe(String username, PushChannelEvent event);
}
