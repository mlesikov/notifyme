package com.clouway.notifyme.client;

import com.clouway.notifyme.shared.PushChannelEvent;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
@RemoteServiceRelativePath("SubscriptionService")
public interface SubscriptionService extends RemoteService {

  void subscribe(String username, PushChannelEvent event);
}
