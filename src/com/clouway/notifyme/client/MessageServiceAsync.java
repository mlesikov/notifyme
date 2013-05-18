package com.clouway.notifyme.client;

import com.clouway.notifyme.shared.SpecialMessage;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.Date;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public interface MessageServiceAsync {

  void sendMessage(String sender, String message, Date createdOn, AsyncCallback<Void> async);


  void sendSpecialMessage(SpecialMessage specialMessage, AsyncCallback<Void> async);
}
