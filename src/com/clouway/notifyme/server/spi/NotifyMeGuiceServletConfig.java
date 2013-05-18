package com.clouway.notifyme.server.spi;

import com.clouway.notifyme.server.MessageServiceImpl;
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

        serve("/NotifyMe/MessageService").with(MessageServiceImpl.class);

        bind(SubscriptionsRepository.class).to(SubscriptionsRepositoryImpl.class).in(Singleton.class);

        bind(PushService.class).to(PushServiceImpl.class).in(Singleton.class);
      }
    });
  }
}
