package com.clouway.notifyme.client;

import com.clouway.notifyme.shared.ChatMessageEvent;
import com.clouway.notifyme.shared.PushChannelEventHandler;
import com.google.gwt.user.client.Window;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public class ChatRoom implements ChatRoomView.Presenter {

  private final PushChannel pushChannel;
  private final ChatRoomView display;

  public ChatRoom(PushChannel pushChannel, ChatRoomView display) {
    this.pushChannel = pushChannel;
    this.display = display;
  }

  public void signIn(String username) {

    pushChannel.connect(username, new ConnectionListener() {

      public void onConnect() {
        display.showChatRoom();
      }
    });

    pushChannel.subscribe(username, new ChatMessageEvent(), new PushChannelEventHandler() {

      public void onMessage(ChatMessageEvent event) {
        Window.alert("Received event: " + event.getEventName());
      }
    });
  }
}
