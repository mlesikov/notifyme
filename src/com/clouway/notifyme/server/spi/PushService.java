package com.clouway.notifyme.server.spi;

import com.clouway.notifyme.shared.spi.PushChannelEvent;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public interface PushService {

  void pushEvent(PushChannelEvent event);
}
