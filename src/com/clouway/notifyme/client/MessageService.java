package com.clouway.notifyme.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
@RemoteServiceRelativePath("MessageService")
public interface MessageService extends RemoteService {

  void sendMessage(String message);
}
