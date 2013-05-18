package com.clouway.notifyme.server.spi;

import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.clouway.notifyme.client.spi.ConnectionService;
import com.google.inject.Singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
@Singleton
public class ConnectionServiceImpl extends RemoteServiceServlet implements ConnectionService {

  private Map<String, String> channelTokens = new HashMap<String, String>();

  public String connect(String username) {

    if (channelTokens.containsKey(username)) {

      return channelTokens.get(username);

    } else {

      String channelToken = ChannelServiceFactory.getChannelService().createChannel(username);
      channelTokens.put(username, channelToken);

      return channelToken;
    }
  }
}