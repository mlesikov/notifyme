package com.clouway.notifyme.server;

import com.clouway.notifyme.shared.ChatMessageEvent;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.clouway.notifyme.client.MessageService;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.Date;

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

  public void sendMessage(String sender, String message, Date createdOn) {
    pushService.pushEvent(new ChatMessageEvent(sender, message, createdOn));
  }
}