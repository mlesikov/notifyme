package com.clouway.notifyme.shared;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public interface PushChannelEventHandler<T> {

  void onMessage(T message);
}
