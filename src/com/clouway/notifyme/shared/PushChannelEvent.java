package com.clouway.notifyme.shared;

import java.io.Serializable;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public interface PushChannelEvent extends Serializable {

  String getEventName();
}
