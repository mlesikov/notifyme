package com.clouway.notifyme.shared;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public interface ChatMessageFactory extends AutoBeanFactory {

  AutoBean<ChatMessage> chatMessage();
}
