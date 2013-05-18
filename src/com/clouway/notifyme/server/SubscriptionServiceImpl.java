package com.clouway.notifyme.server;

import com.clouway.notifyme.shared.PushChannelEvent;
import com.clouway.notifyme.client.SubscriptionService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
@Singleton
public class SubscriptionServiceImpl extends RemoteServiceServlet implements SubscriptionService {

  private final SubscriptionsRepository subscriptionsRepository;

  @Inject
  public SubscriptionServiceImpl(SubscriptionsRepository subscriptionsRepository) {
    this.subscriptionsRepository = subscriptionsRepository;
  }

  public void subscribe(String username, PushChannelEvent event) {
    subscriptionsRepository.subscribe(username, event);
  }

  @Override
  public void unsubscribe(String username, PushChannelEvent event) {
    subscriptionsRepository.unsubscribe(username, event);
  }
}