package com.clouway.notifyme.server;

import com.clouway.notifyme.shared.ChatMessage;
import com.clouway.notifyme.shared.ChatMessageEvent;
import com.clouway.notifyme.shared.ChatMessageFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.clouway.notifyme.client.MessageService;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.google.web.bindery.autobean.vm.AutoBeanFactorySource;

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

    ChatMessageFactory chatMessageFactory = AutoBeanFactorySource.create(ChatMessageFactory.class);
    ChatMessage chatMessage = chatMessageFactory.chatMessage().as();
    chatMessage.setMessage(message);
    chatMessage.setEventName("ChatMessageEvent");

    AutoBean<ChatMessage> chatMessageAutoBean = AutoBeanUtils.getAutoBean(chatMessage);
    String jsonData = AutoBeanCodex.encode(chatMessageAutoBean).getPayload();

    pushService.pushEvent(new ChatMessageEvent(), jsonData);
  }
}