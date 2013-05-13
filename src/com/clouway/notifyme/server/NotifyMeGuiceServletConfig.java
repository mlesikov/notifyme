package com.clouway.notifyme.server;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public class NotifyMeGuiceServletConfig extends GuiceServletContextListener {

  protected Injector getInjector() {
    return Guice.createInjector(new ServletModule() {

      @Override
      protected void configureServlets() {

        serve("/NotifyMe/ConnectionService").with(ConnectionServiceImpl.class);

        serve("/NotifyMe/SubscriptionService").with(SubscriptionServiceImpl.class);
      }
    });
  }
}
