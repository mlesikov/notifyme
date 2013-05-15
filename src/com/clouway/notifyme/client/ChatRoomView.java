package com.clouway.notifyme.client;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public interface ChatRoomView {

  public interface Presenter {
    void signIn(String username);

    void sendMessage(String message);
  }

  void setPresenter(Presenter presenter);

  void showChatRoom();

  void clearMessageBox();

  void displayMessage(String message);
}
