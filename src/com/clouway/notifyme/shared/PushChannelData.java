package com.clouway.notifyme.shared;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public class PushChannelData extends JavaScriptObject {

  protected PushChannelData() {
  }

  public final native String getEventName() /*-{
      return this.eventName;
  }-*/;
}
