package com.clouway.notifyme.server;

import com.clouway.notifyme.shared.PushChannelEvent;

import java.util.List;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public interface SubscriptionsRepository {

  void subscribe(String username, PushChannelEvent event);

  List<String> getSubscribedUsers(PushChannelEvent event);
}
