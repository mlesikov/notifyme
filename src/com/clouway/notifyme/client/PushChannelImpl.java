package com.clouway.notifyme.client;

import com.clouway.notifyme.shared.ChatMessage;
import com.clouway.notifyme.shared.ChatMessageFactory;
import com.clouway.notifyme.shared.PushChannelData;
import com.clouway.notifyme.shared.PushChannelEvent;
import com.clouway.notifyme.shared.PushChannelEventHandler;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public class PushChannelImpl implements PushChannel {

  private final ConnectionServiceAsync connectionServiceAsync;
  private final SubscriptionServiceAsync subscriptionServiceAsync;

  private Map<String, PushChannelEventHandler> eventHandlers = new HashMap<String, PushChannelEventHandler>();
  private Map<String, Class<? extends AutoBeanFactory>> eventFactory = new HashMap<String, Class<? extends AutoBeanFactory>>();
  private Map<String, Class<?>> eventToClass = new HashMap<String, Class<?>>();

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
        eventFactory.put(event.getEventName(), ChatMessageFactory.class);
        eventToClass.put(event.getEventName(), ChatMessage.class);
      }
    });
  }

  private native void openChannel(String channelToken, PushChannelImpl pushChannelAPI) /*-{

      var channel = new $wnd.goog.appengine.Channel(channelToken);
      var socket = channel.open();

      socket.onmessage = function (event) {
          pushChannelAPI.@com.clouway.notifyme.client.PushChannelImpl::onReceivedMessage(Ljava/lang/String;)(event.data);
      }

  }-*/;

  private void onReceivedMessage(String json) {

    PushChannelData pushChannelData = JsonUtils.safeEval(json);

    if (eventHandlers.containsKey(pushChannelData.getEventName())) {

      AutoBean<?> decode = AutoBeanCodex.decode(GWT.<AutoBeanFactory>create(eventFactory.get(pushChannelData.getEventName())), eventToClass.get(pushChannelData.getEventName()), json);
      Object object = decode.as();

      eventHandlers.get(pushChannelData.getEventName()).onMessage(object);
    }
  }
}
