package com.clouway.notifyme.client;

import com.clouway.notifyme.shared.PushChannelEvent;
import com.clouway.notifyme.shared.PushChannelEventHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public class PushChannelImpl implements PushChannel {

  private final ConnectionServiceAsync connectionServiceAsync;
  private final SubscriptionServiceAsync subscriptionServiceAsync;

  private Map<String, PushChannelEventHandler> eventHandlers = new HashMap<String, PushChannelEventHandler>();

  @Inject
  public PushChannelImpl(ConnectionServiceAsync connectionServiceAsync, SubscriptionServiceAsync subscriptionServiceAsync) {
    this.connectionServiceAsync = connectionServiceAsync;
    this.subscriptionServiceAsync = subscriptionServiceAsync;
  }

  public void connect(String username, final ConnectionListener connectionListener) {

    connectionServiceAsync.connect(username, new AsyncCallback<String>() {

      public void onFailure(Throwable caught) {
      }

      public void onSuccess(String channelToken) {
        openChannel(channelToken, PushChannelImpl.this);
        connectionListener.onConnect();
      }
    });
  }

  public void subscribe(String username, final PushChannelEvent event, final PushChannelEventHandler eventHandler) {

    subscriptionServiceAsync.subscribe(username, event, new AsyncCallback<Void>() {
      public void onFailure(Throwable caught) {
      }

      public void onSuccess(Void result) {
        eventHandlers.put(event.getEventName(), eventHandler);
      }
    });
  }

  private native void openChannel(String channelToken, PushChannelImpl pushChannelAPI) /*-{

      //$wnd.goog.appengine.Socket.POLLING_TIMEOUT_MS = 200;

      var channel = new $wnd.goog.appengine.Channel(channelToken);
      var socket = channel.open();

      socket.onmessage = function (event) {
          pushChannelAPI.@com.clouway.notifyme.client.PushChannelImpl::onReceivedMessage(Ljava/lang/String;)(event.data);
      }

  }-*/;

  private void onReceivedMessage(String json) {
    Window.alert("Message is received. Fire it to the appropriate handler");
  }
}
