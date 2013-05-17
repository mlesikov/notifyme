package com.clouway.notifyme.server;

import com.clouway.notifyme.shared.PushChannelEvent;
import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.inject.Inject;

import java.util.List;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public class PushServiceImpl implements PushService {

  private final SubscriptionsRepository subscriptionsRepository;
  private JsonSerializer jsonSerializer;

  @Inject
  public PushServiceImpl(SubscriptionsRepository subscriptionsRepository) {
    this.subscriptionsRepository = subscriptionsRepository;
    jsonSerializer = new JsonSerializerImpl();
  }

  public void pushEvent(PushChannelEvent event) {

    String eventAsJson = jsonSerializer.serialize(event);

    String message = event.getEventName() + "|" + eventAsJson;

    List<String> subscribedUsers = subscriptionsRepository.getSubscribedUsers(event);

    ChannelService channelService = ChannelServiceFactory.getChannelService();

    for (String subscribedUser : subscribedUsers) {
      channelService.sendMessage(new ChannelMessage(subscribedUser, message));
    }
  }
}





//ChatMessageFactory chatMessageFactory = AutoBeanFactorySource.create(ChatMessageFactory.class);
//    ChatMessage chatMessage = chatMessageFactory.chatMessage().as();
//    chatMessage.setMessage(message);
//    chatMessage.setEventName("ChatMessageEvent");
//
//    AutoBean<ChatMessage> chatMessageAutoBean = AutoBeanUtils.getAutoBean(chatMessage);
//    String jsonData = AutoBeanCodex.encode(chatMessageAutoBean).getPayload();