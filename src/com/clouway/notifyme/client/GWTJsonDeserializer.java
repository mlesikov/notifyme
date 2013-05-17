package com.clouway.notifyme.client;

import com.clouway.notifyme.shared.PushChannelEvent;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public interface GWTJsonDeserializer {
  PushChannelEvent deserialize(String eventClassName, String eventDataJson);
}
