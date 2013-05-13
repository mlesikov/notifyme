package com.clouway.notifyme.server;

import com.clouway.notifyme.shared.PushChannelEvent;
import com.clouway.notifyme.client.SubscriptionService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Singleton;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
@Singleton
public class SubscriptionServiceImpl extends RemoteServiceServlet implements SubscriptionService {

  public void subscribe(String username, PushChannelEvent event) {

  }
}