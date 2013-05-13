package com.clouway.notifyme.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public interface MessageServiceAsync {

  void sendMessage(String message, AsyncCallback<Void> async);
}
