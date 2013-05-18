package com.clouway.notifyme.client;

import com.clouway.notifyme.shared.SpecialMessage;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.Date;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
@RemoteServiceRelativePath("MessageService")
public interface MessageService extends RemoteService {

  void sendMessage(String sender, String message, Date createdOn);

  void sendSpecialMessage(SpecialMessage specialMessage);
}
