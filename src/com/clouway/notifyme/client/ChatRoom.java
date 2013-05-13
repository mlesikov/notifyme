package com.clouway.notifyme.client;

import com.clouway.notifyme.shared.ChatMessageEvent;
import com.clouway.notifyme.shared.PushChannelEvent;
import com.clouway.notifyme.shared.PushChannelEventHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public class ChatRoom implements ChatRoomView.Presenter {

  private final PushChannel pushChannel;
  private final MessageServiceAsync messageServiceAsync;
  private final ChatRoomView display;

  public ChatRoom(PushChannel pushChannel, MessageServiceAsync messageServiceAsync, ChatRoomView display) {
    this.pushChannel = pushChannel;
    this.messageServiceAsync = messageServiceAsync;
    this.display = display;
  }

  public void signIn(String username) {

    pushChannel.connect(username, new ConnectionListener() {

      public void onConnect() {
        display.showChatRoom();
      }
    });

    pushChannel.subscribe(username, new ChatMessageEvent(), new PushChannelEventHandler() {

      public void onMessage(PushChannelEvent event) {
        Window.alert("Received message...");
      }
    });
  }

  public void sendMessage(String message) {

    messageServiceAsync.sendMessage(message, new AsyncCallback<Void>() {

      public void onFailure(Throwable caught) {
      }

      public void onSuccess(Void result) {
        display.clearMessageBox();
      }
    });
  }
}
