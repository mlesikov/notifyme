package com.clouway.notifyme.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;

@RemoteServiceRelativePath("ConnectionService")
public interface ConnectionService extends RemoteService {

  String connect(String username);
}
