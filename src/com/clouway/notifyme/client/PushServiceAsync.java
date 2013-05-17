package com.clouway.notifyme.client;

import com.clouway.notifyme.shared.PushChannelEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PushServiceAsync {

  void receiveEvent(AsyncCallback<PushChannelEvent> async);
}