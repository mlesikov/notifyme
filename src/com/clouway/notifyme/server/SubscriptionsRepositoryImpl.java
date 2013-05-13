package com.clouway.notifyme.server;

import com.clouway.notifyme.shared.PushChannelEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public class SubscriptionsRepositoryImpl implements SubscriptionsRepository {

  private Map<String, List<String>> events = new HashMap<String, List<String>>();

  public void subscribe(String username, PushChannelEvent event) {

    if (events.containsKey(event.getEventName())) {

      List<String> subscribedUsers = events.get(event.getEventName());
      subscribedUsers.add(username);

      events.put(event.getEventName(), subscribedUsers);
    } else {

      List<String> subscribedUsers = new ArrayList<String>();
      subscribedUsers.add(username);

      events.put(event.getEventName(), subscribedUsers);
    }
  }

  @Override
  public List<String> getSubscribedUsers(PushChannelEvent event) {

    List<String> subscribedUsers = events.get(event.getEventName());

    return subscribedUsers;
  }
}
