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

  @Inject
  public PushServiceImpl(SubscriptionsRepository subscriptionsRepository) {
    this.subscriptionsRepository = subscriptionsRepository;
  }

  public void pushEvent(PushChannelEvent event) {

    List<String> subscribedUsers = subscriptionsRepository.getSubscribedUsers(event);

    ChannelService channelService = ChannelServiceFactory.getChannelService();

    for (String subscribedUser : subscribedUsers) {
      channelService.sendMessage(new ChannelMessage(subscribedUser, event.getEventName()));
    }
  }
}
