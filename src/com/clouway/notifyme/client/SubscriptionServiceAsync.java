package com.clouway.notifyme.client;

import com.clouway.notifyme.shared.PushChannelEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public interface SubscriptionServiceAsync {

  void subscribe(String username, PushChannelEvent event, AsyncCallback<Void> async);
}
