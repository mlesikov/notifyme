package com.clouway.notifyme.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

public class NotifyMe implements EntryPoint {

  public void onModuleLoad() {

    ConnectionServiceAsync connectionServiceAsync = GWT.create(ConnectionService.class);
    SubscriptionServiceAsync subscriptionServiceAsync = GWT.create(SubscriptionService.class);
    MessageServiceAsync messageServiceAsync = GWT.create(MessageService.class);

    PushChannel pushChannel = new PushChannelImpl(connectionServiceAsync, subscriptionServiceAsync);

    ChatRoomViewImpl chatRoomView = new ChatRoomViewImpl();
    ChatRoom chatRoom = new ChatRoom(pushChannel, messageServiceAsync, chatRoomView);
    chatRoomView.setPresenter(chatRoom);

    RootPanel.get().add(chatRoomView);
  }
}
