package com.clouway.notifyme.client;

import com.clouway.notifyme.client.spi.ConnectionListener;
import com.clouway.notifyme.client.spi.PushChannel;
import com.clouway.notifyme.shared.ChatMessageEvent;
import com.clouway.notifyme.shared.spi.PushChannelEventHandler;
import com.clouway.notifyme.shared.SpecialMessage;
import com.clouway.notifyme.shared.SpecialMessageEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.Date;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public class ChatRoom implements ChatRoomView.Presenter {

  private final PushChannel pushChannel;
  private final MessageServiceAsync messageService;
  private final ChatRoomView display;

  private String sender = "";

  public ChatRoom(PushChannel pushChannel, MessageServiceAsync messageService, ChatRoomView display) {
    this.pushChannel = pushChannel;
    this.messageService = messageService;
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

      public void onEvent(ChatMessageEvent chatMessageEvent) {

        DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("(HH:mm:ss)");
        String createdOn = dateTimeFormat.format(chatMessageEvent.getCreatedOn());
        String sender = chatMessageEvent.getSender();
        String message = chatMessageEvent.getMessage();

        display.displayMessage(createdOn + " " + sender + ": " + message);
      }
    });
  }

  public void sendMessage(String message) {

    messageService.sendMessage(sender, message, new Date(), new AsyncCallback<Void>() {

      public void onFailure(Throwable caught) {
      }

      public void onSuccess(Void result) {
        display.clearMessageBox();
      }
    });
  }

  @Override
  public void subscribeForSpecialMessage(String username) {

    pushChannel.subscribe(username, new SpecialMessageEvent(), new PushChannelEventHandler<SpecialMessageEvent>() {

      public void onEvent(SpecialMessageEvent message) {
        display.displayMessage("Special Message: " + message.getSpecialMessage().getMessage());
      }
    });
  }

  @Override
  public void unsubscribeFromSpecialMessage(String username) {

    pushChannel.unsubscribe(username, new SpecialMessageEvent());
  }

  @Override
  public void sendSpecialMessage(String specialMessage) {

    messageService.sendSpecialMessage(new SpecialMessage(specialMessage), new AsyncCallback<Void>() {
      @Override
      public void onFailure(Throwable caught) {
      }

      @Override
      public void onSuccess(Void result) {
        display.clearMessageBox();
      }
    });
  }
}
