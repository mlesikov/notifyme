package com.clouway.notifyme.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public class ChatRoomViewImpl extends Composite implements ChatRoomView {

  interface ChatRoomViewImplUiBinder extends UiBinder<Widget, ChatRoomViewImpl> {
  }

  private static ChatRoomViewImplUiBinder uiBinder = GWT.create(ChatRoomViewImplUiBinder.class);

  private Presenter presenter;

  @UiField
  Button signInButton;

  @UiField
  TextBox usernameBox;

  @UiField
  HTMLPanel chatPanel;

  @UiField
  HTMLPanel signInPanel;

  public ChatRoomViewImpl() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  @UiHandler("signInButton")
  public void onSignInButtonClick(ClickEvent event) {
    presenter.signIn(usernameBox.getText());
  }

  public void showChatRoom() {
    signInPanel.setVisible(false);
    chatPanel.setVisible(true);
  }
}