package com.clouway.notifyme.client;

import com.clouway.notifyme.shared.ChatMessageEventAutoBean;
import com.clouway.notifyme.shared.ChatMessageFactory;
import com.clouway.notifyme.shared.PushChannelEvent;
import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public class GWTJsonDeserializerImpl implements GWTJsonDeserializer {

  private Map<String, AutoBeanFactory> eventFactory = new HashMap<String, AutoBeanFactory>();
  private Map<String, Class<? extends PushChannelEvent>> eventDataClass = new HashMap<String, Class<? extends PushChannelEvent>>();

  public GWTJsonDeserializerImpl() {
    eventFactory.put("ChatMessageEvent", (AutoBeanFactory) GWT.create(ChatMessageFactory.class));
    eventDataClass.put("ChatMessageEvent", ChatMessageEventAutoBean.class);
  }

  @Override
  public PushChannelEvent deserialize(String eventClassName, String eventDataJson) {

    AutoBeanFactory autoBeanFactory = eventFactory.get(eventClassName);

    PushChannelEvent pushChannelEvent = AutoBeanCodex.decode(autoBeanFactory, eventDataClass.get(eventClassName), eventDataJson).as();

    return pushChannelEvent;
  }
}
