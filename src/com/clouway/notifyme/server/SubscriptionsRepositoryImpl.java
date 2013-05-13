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

  public static Map<PushChannelEvent, List<String>> events = new HashMap<PushChannelEvent, List<String>>();

  public void subscribe(String username, PushChannelEvent event) {

    if (events.containsKey(event)) {

      List<String> subscribedUsers = events.get(event);
      subscribedUsers.add(username);

      events.put(event, subscribedUsers);
    } else {

      List<String> subscribedUsers = new ArrayList<String>();
      subscribedUsers.add(username);

      events.put(event, subscribedUsers);
    }
  }
}
