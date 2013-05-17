package com.clouway.notifyme.client;

import com.clouway.notifyme.shared.PushChannelEvent;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("push_service")
public interface PushService extends RemoteService {

  /**
   * Utility/Convenience class. Use PushService.App.getInstance() to access
   * static instance of PushServiceAsync
   */
  public static class App {
    private static final PushServiceAsync ourInstance = (PushServiceAsync) GWT
        .create(PushService.class);

    public static PushServiceAsync getInstance() {
      return ourInstance;
    }
  }
  /**
   * A dummy method ensuring that Message and all its subclasses
   * are client serializable.
   */
  PushChannelEvent receiveMessage();
}