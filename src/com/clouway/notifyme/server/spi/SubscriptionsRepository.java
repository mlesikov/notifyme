package com.clouway.notifyme.server.spi;

import com.clouway.notifyme.shared.spi.PushChannelEvent;

import java.util.List;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public interface SubscriptionsRepository {

  void subscribe(String username, PushChannelEvent event);

  List<String> getSubscribedUsers(PushChannelEvent event);

  void unsubscribe(String username, PushChannelEvent event);
}
