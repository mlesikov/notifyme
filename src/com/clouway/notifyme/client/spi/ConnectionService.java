package com.clouway.notifyme.client.spi;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ConnectionService")
public interface ConnectionService extends RemoteService {

  String connect(String username);
}
