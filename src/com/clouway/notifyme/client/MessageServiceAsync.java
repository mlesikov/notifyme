package com.clouway.notifyme.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.Date;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public interface MessageServiceAsync {

  void sendMessage(String sender, String message, Date createdOn, AsyncCallback<Void> async);
}
