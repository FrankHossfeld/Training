package de.gishmo.gwt.example.module0703.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Module0703
    implements EntryPoint {

  private DialogBox dialogBox;

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    createDialogBox();

    final Button showButton = new Button("show dialogbox");
    // We can add style names to widgets
    showButton.addStyleName("sendButton");

    showButton.addClickHandler(event -> dialogBox.center());

    // Use RootPanel.get() to get the entire body element
    RootPanel.get()
             .add(showButton);

  }

  private void createDialogBox() {
    dialogBox = new DialogBox();
    dialogBox.setText("Kundensuche");
    dialogBox.setAnimationEnabled(true);
    dialogBox.setModal(true);

    // TODO: enter your code here

  }
}
