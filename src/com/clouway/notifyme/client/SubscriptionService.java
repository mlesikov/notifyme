package com.clouway.notifyme.client;

import com.clouway.notifyme.shared.PushChannelEvent;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
@RemoteServiceRelativePath("SubscriptionService")
public interface SubscriptionService extends RemoteService {

  void subscribe(String username, PushChannelEvent event);

  void unsubscribe(String username, PushChannelEvent event);
}
