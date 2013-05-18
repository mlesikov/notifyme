package com.clouway.notifyme.client.spi;

import com.clouway.notifyme.shared.spi.PushChannelEvent;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * This interface is odd in that the client doesn't actually make calls
 * through this interface to the server. Instead the server uses server-side
 * push to send GWT RPC encoded data to the client via an alternate
 * transport. The definition of this interface helps to ensure that all
 * the correct de-serialization code is generated for the client. A call to
 * GWT.create on this service must be made to ensure the de-serialization
 * code is actually generated.
 */
@RemoteServiceRelativePath("push_service")
public interface PushService extends RemoteService {

  /**
   * A dummy method ensuring that PushChannelEvent and all its subclasses
   * are client serializable.
   */
  PushChannelEvent receiveEvent();
}