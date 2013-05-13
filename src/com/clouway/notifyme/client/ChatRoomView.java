package com.clouway.notifyme.client;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public interface ChatRoomView {

  public interface Presenter {
    void signIn(String username);
  }

  void setPresenter(Presenter presenter);

  void showChatRoom();
}
