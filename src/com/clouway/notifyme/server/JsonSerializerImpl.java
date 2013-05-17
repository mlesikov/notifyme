package com.clouway.notifyme.server;

import com.clouway.notifyme.shared.*;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.google.web.bindery.autobean.vm.AutoBeanFactorySource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public class JsonSerializerImpl implements JsonSerializer {

  private Map<Class, PushChannelEventBuilder> eventBuilders = new HashMap<Class, PushChannelEventBuilder>();

  public JsonSerializerImpl() {
    eventBuilders.put(ChatMessageEvent.class, new ChatMessageEventBuilder());
  }

  public String serialize(PushChannelEvent event) {


    PushChannelEventBuilder pushChannelEventBuilder = eventBuilders.get(event.getClass());

    return pushChannelEventBuilder.builder(event);
  }

  interface PushChannelEventBuilder<T extends PushChannelEvent> {
    String builder(T t);
  }

  class ChatMessageEventBuilder implements PushChannelEventBuilder<ChatMessageEvent> {

    @Override
    public String builder(ChatMessageEvent event) {

      ChatMessageFactory chatMessageFactory = AutoBeanFactorySource.create(ChatMessageFactory.class);
      ChatMessageEventAutoBean chatMessage = chatMessageFactory.chatMessage().as();
      chatMessage.setMessage(event.getMessage());

      AutoBean<ChatMessageEventAutoBean> chatMessageAutoBean = AutoBeanUtils.getAutoBean(chatMessage);
      return AutoBeanCodex.encode(chatMessageAutoBean).getPayload();
    }
  }
}
