package com.clouway.notifyme.client;

import com.clouway.notifyme.shared.PushChannelEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Async interface for {@link PushService}. This class is not used directly
 * by client code.
 */
public interface PushServiceAsync {

  void receiveEvent(AsyncCallback<PushChannelEvent> async);
}