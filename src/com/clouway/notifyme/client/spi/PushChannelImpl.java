package com.clouway.notifyme.client.spi;

import com.clouway.notifyme.shared.spi.PushChannelEvent;
import com.clouway.notifyme.shared.spi.PushChannelEventHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamFactory;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.inject.Inject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public class PushChannelImpl implements PushChannel {

  private final ConnectionServiceAsync connectionServiceAsync;
  private final SubscriptionServiceAsync subscriptionServiceAsync;
  private final PushServiceAsync pushServiceAsync;

  private Map<String, PushChannelEventHandler> eventHandlers = new HashMap<String, PushChannelEventHandler>();

  @Inject
  public PushChannelImpl(ConnectionServiceAsync connectionServiceAsync, SubscriptionServiceAsync subscriptionServiceAsync, PushServiceAsync pushServiceAsync) {
    this.connectionServiceAsync = connectionServiceAsync;
    this.subscriptionServiceAsync = subscriptionServiceAsync;
    this.pushServiceAsync = pushServiceAsync;
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

  public void unsubscribe(String username, PushChannelEvent event) {

    subscriptionServiceAsync.unsubscribe(username, event, new AsyncCallback<Void>() {
      @Override
      public void onFailure(Throwable caught) {
      }

      @Override
      public void onSuccess(Void result) {
        Window.alert("Successfully Unsubscribed");
      }
    });
  }

  private native void openChannel(String channelToken, PushChannelImpl pushChannelAPI) /*-{

      var channel = new $wnd.goog.appengine.Channel(channelToken);
      var socket = channel.open();

      socket.onmessage = function (event) {
          pushChannelAPI.@com.clouway.notifyme.client.spi.PushChannelImpl::onReceivedMessage(Ljava/lang/String;)(event.data);
      }

  }-*/;

  private void onReceivedMessage(String json) {

    SerializationStreamFactory pushServiceStreamFactory = (SerializationStreamFactory) pushServiceAsync;

    try {
      SerializationStreamReader reader = pushServiceStreamFactory.createStreamReader(json);
      PushChannelEvent event = (PushChannelEvent) reader.readObject();

      if (eventHandlers.containsKey(event.getEventName())) {
        eventHandlers.get(event.getEventName()).onEvent(event);
      }

    } catch (SerializationException e) {
      throw new RuntimeException("Unable to deserialize " + json, e);
    }
  }
}
