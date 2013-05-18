package com.clouway.notifyme.client.spi;

import com.clouway.notifyme.shared.spi.PushChannelEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Async interface for {@link PushService}. This class is not used directly
 * by client code.
 */
public interface PushServiceAsync {

  void receiveEvent(AsyncCallback<PushChannelEvent> async);
}