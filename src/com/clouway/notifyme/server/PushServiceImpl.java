package com.clouway.notifyme.server;

import com.clouway.notifyme.shared.PushChannelEvent;
import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.SerializationPolicy;
import com.google.gwt.user.server.rpc.SerializationPolicyLoader;
import com.google.inject.Inject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public class PushServiceImpl implements PushService {

  private static SerializationPolicy serializationPolicy = createPushSerializationPolicy();
  private final Method dummyMethod = getDummyMethod();

  private final SubscriptionsRepository subscriptionsRepository;

  @Inject
  public PushServiceImpl(SubscriptionsRepository subscriptionsRepository) {
    this.subscriptionsRepository = subscriptionsRepository;
  }

  public void pushEvent(PushChannelEvent event) {

    String message = encodeMessage(event);

    List<String> subscribedUsers = subscriptionsRepository.getSubscribedUsers(event);

    ChannelService channelService = ChannelServiceFactory.getChannelService();

    for (String subscribedUser : subscribedUsers) {
      channelService.sendMessage(new ChannelMessage(subscribedUser, message));
    }
  }

  private String encodeMessage(PushChannelEvent event) {
    try {
      return RPC.encodeResponseForSuccess(dummyMethod, event, serializationPolicy);
    } catch (SerializationException e) {
      throw new RuntimeException("Unable to encode a message for push.\n" + event.getEventName(), e);
    }
  }

  private PushChannelEvent dummyMethod() {
    throw new UnsupportedOperationException("This should never be called.");
  }

  private static Method getDummyMethod() {
    try {
      return PushServiceImpl.class.getDeclaredMethod("dummyMethod");
    } catch (NoSuchMethodException e) {
      throw new RuntimeException("Unable to find the dummy RPC method.");
    }
  }

  private static SerializationPolicy createPushSerializationPolicy() {
    // Read all of the SerializationPolicy files in the app and merging them together.

    File[] files = new File("/home/ilazov/workspace/idea/notifyme/out/artifacts/notifyme/NotifyMe").listFiles(new FilenameFilter() {
      public boolean accept(File dir, String name) {
        return name.endsWith(".gwt.rpc");
      }
    });

    List<SerializationPolicy> policies = new ArrayList<SerializationPolicy>();

    for (File f : files) {
      try {
        BufferedInputStream input = new BufferedInputStream(
                new FileInputStream(f));
        policies.add(SerializationPolicyLoader.loadFromStream(input, null));
      } catch (Exception e) {
        throw new RuntimeException(
                "Unable to load a policy file: " + f.getAbsolutePath());
      }
    }

    return new MergedSerializationPolicy(policies);
  }

  private static class MergedSerializationPolicy extends SerializationPolicy {
    List<SerializationPolicy> policies;

    MergedSerializationPolicy(List<SerializationPolicy> policies) {
      this.policies = policies;
    }

    @Override
    public boolean shouldDeserializeFields(Class<?> clazz) {
      for (SerializationPolicy p : policies) {
        if (p.shouldDeserializeFields(clazz)) {
          return true;
        }
      }
      return false;
    }

    @Override
    public boolean shouldSerializeFields(Class<?> clazz) {
      for (SerializationPolicy p : policies) {
        if (p.shouldSerializeFields(clazz)) {
          return true;
        }
      }
      return false;
    }

    @Override
    public void validateDeserialize(Class<?> clazz) throws SerializationException {
      SerializationException se = null;
      for (SerializationPolicy p : policies) {
        try {
          p.validateDeserialize(clazz);
          return;
        } catch (SerializationException e) {
          se = e;
        }
      }
      throw se;
    }

    @Override
    public void validateSerialize(Class<?> clazz) throws SerializationException {
      SerializationException se = null;
      for (SerializationPolicy p : policies) {
        try {
          p.validateSerialize(clazz);
          return;
        } catch (SerializationException e) {
          se = e;
        }
      }
      throw se;
    }
  }
}
