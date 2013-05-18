package com.clouway.notifyme.client.spi;

import com.clouway.notifyme.shared.spi.PushChannelEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public interface SubscriptionServiceAsync {

  void subscribe(String username, PushChannelEvent event, AsyncCallback<Void> async);

  void unsubscribe(String username, PushChannelEvent event, AsyncCallback<Void> async);
}
