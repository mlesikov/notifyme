package com.clouway.notifyme.client;

import com.clouway.notifyme.shared.ChatMessageEvent;
import com.clouway.notifyme.shared.PushChannelEventHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.Date;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public class ChatRoom implements ChatRoomView.Presenter {

  private final PushChannel pushChannel;
  private final MessageServiceAsync messageServiceAsync;
  private final ChatRoomView display;

  private String sender = "";

  public ChatRoom(PushChannel pushChannel, MessageServiceAsync messageServiceAsync, ChatRoomView display) {
    this.pushChannel = pushChannel;
    this.messageServiceAsync = messageServiceAsync;
    this.display = display;
  }

  public void signIn(final String username) {

    pushChannel.connect(username, new ConnectionListener() {

      public void onConnect() {
        display.showChatRoom();
        sender = username;
      }
    });

    pushChannel.subscribe(username, new ChatMessageEvent(), new PushChannelEventHandler<ChatMessageEvent>() {

      public void onMessage(ChatMessageEvent chatMessageEvent) {

        DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("(H:mm:s)");
        String createdOn = dateTimeFormat.format(chatMessageEvent.getCreatedOn());
        String sender = chatMessageEvent.getSender();
        String message = chatMessageEvent.getMessage();

        display.displayMessage(createdOn + " " + sender + ": " + message);
      }
    });
  }

  public void sendMessage(String message) {

    messageServiceAsync.sendMessage(sender, message, new Date(), new AsyncCallback<Void>() {

      public void onFailure(Throwable caught) {
      }

      public void onSuccess(Void result) {
        display.clearMessageBox();
      }
    });
  }
}
