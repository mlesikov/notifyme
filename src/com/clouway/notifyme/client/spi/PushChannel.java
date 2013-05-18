package com.clouway.notifyme.client.spi;

import com.clouway.notifyme.shared.spi.PushChannelEvent;
import com.clouway.notifyme.shared.spi.PushChannelEventHandler;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public interface PushChannel {

  void connect(String username, ConnectionListener connectionListener);

  void subscribe(String username, PushChannelEvent event, PushChannelEventHandler eventHandler);

  void unsubscribe(String username, PushChannelEvent event);
}
