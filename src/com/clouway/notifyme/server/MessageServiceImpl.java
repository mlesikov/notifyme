package com.clouway.notifyme.server;

import com.clouway.notifyme.shared.ChatMessageEvent;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.clouway.notifyme.client.MessageService;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
@Singleton
public class MessageServiceImpl extends RemoteServiceServlet implements MessageService {

  private final PushService pushService;

  @Inject
  public MessageServiceImpl(PushService pushService) {
    this.pushService = pushService;
  }

  public void sendMessage(String message) {
    pushService.pushEvent(new ChatMessageEvent(message));
  }
}