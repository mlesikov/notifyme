package com.clouway.notifyme.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

public class NotifyMe implements EntryPoint {

  public void onModuleLoad() {

    ConnectionServiceAsync connectionServiceAsync = GWT.create(ConnectionService.class);

    PushChannel pushChannel = new PushChannelImpl(connectionServiceAsync);

    ChatRoomViewImpl chatRoomView = new ChatRoomViewImpl();
    ChatRoom chatRoom = new ChatRoom(pushChannel, chatRoomView);
    chatRoomView.setPresenter(chatRoom);

    RootPanel.get().add(chatRoomView);
  }
}
