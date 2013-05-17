package com.clouway.notifyme.server;

import com.clouway.notifyme.shared.PushChannelEvent;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public interface JsonSerializer {
  String serialize(PushChannelEvent event);
}
