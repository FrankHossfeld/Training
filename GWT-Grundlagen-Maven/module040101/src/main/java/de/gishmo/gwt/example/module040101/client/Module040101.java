package de.gishmo.gwt.example.module040101.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.gishmo.gwt.example.module040101.client.services.PersonService;
import de.gishmo.gwt.example.module040101.client.services.PersonServiceAsync;
import de.gishmo.gwt.example.module040101.shared.dto.Person;
import de.gishmo.gwt.example.module040101.shared.exception.PersonNotFoundException;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Module040101 implements EntryPoint {
  /**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
  private static final String SERVER_ERROR = "An error occurred while "
                                             + "attempting to contact the server. Please check your network "
                                             + "connection and try again.";

  /**
   * Create a remote service proxy to talk to the server-side Greeting service.
   */
  private final static PersonServiceAsync service = GWT.create(PersonService.class);

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    final Button sendButton = new Button("Send");
    final Button getAllButton = new Button("get all");
    getAllButton.addClickHandler(e -> callGetAll());
    final TextBox idField = new TextBox();
    GWT.debugger();
    idField.setText("Please enter id");
    final Label errorLabel = new Label();

    // We can add style names to widgets
    sendButton.addStyleName("sendButton");

    // Add the nameField and sendButton to the RootPanel
    // Use RootPanel.get() to get the entire body element
    RootPanel.get("nameFieldContainer").add(idField);
    RootPanel.get("sendButtonContainer").add(sendButton);
    RootPanel.get("getAllButtonContainer").add(getAllButton);
    RootPanel.get("errorLabelContainer").add(errorLabel);

    // Focus the cursor on the name field when the app loads
    idField.setFocus(true);
    idField.selectAll();

    // Create the popup dialog box
    final DialogBox dialogBox = new DialogBox();
    dialogBox.setText("Remote Procedure Call");
    dialogBox.setAnimationEnabled(true);
    final Button closeButton = new Button("Close");
    // We can set the id of a widget by accessing its Element
    closeButton.getElement().setId("closeButton");
    final Label textToServerLabel = new Label();
    final HTML serverResponseLabel = new HTML();
    VerticalPanel dialogVPanel = new VerticalPanel();
    dialogVPanel.addStyleName("dialogVPanel");
    dialogVPanel.add(new HTML("<b>Sending id to the server:</b>"));
    dialogVPanel.add(textToServerLabel);
    dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
    dialogVPanel.add(serverResponseLabel);
    dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
    dialogVPanel.add(closeButton);
    dialogBox.setWidget(dialogVPanel);

    // Add a handler to close the DialogBox
    closeButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        dialogBox.hide();
        sendButton.setEnabled(true);
        sendButton.setFocus(true);
      }
    });

    // Create a handler for the sendButton and nameField
    class MyHandler implements ClickHandler, KeyUpHandler {
      /**
       * Fired when the user clicks on the sendButton.
       */
      public void onClick(ClickEvent event) {
        sendNameToServer();
      }

      /**
       * Fired when the user types in the nameField.
       */
      public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
          sendNameToServer();
        }
      }

      /**
       * Send the name from the nameField to the server and wait for a response.
       */
      private void sendNameToServer() {
        // First, we validate the input.
        errorLabel.setText("");
        String textToServer = idField.getText();
        if (textToServer == null  || textToServer.trim().length() == 0) {
          errorLabel.setText("Please enter an ID");
          return;
        }
        long id = 0;
        try {
          id = Long.parseLong(textToServer);
        } catch (NumberFormatException e) {
          errorLabel.setText("Please enter a numeric value");
          return;
        }

        // Then, we send the input to the server.
        sendButton.setEnabled(false);
        textToServerLabel.setText(textToServer);
        serverResponseLabel.setText("");

        service.get(id, new AsyncCallback<Person>() {

          @Override
          public void onSuccess(Person result) {
            dialogBox.setText("Remote Procedure Call");
            serverResponseLabel.removeStyleName("serverResponseLabelError");
            serverResponseLabel.setHTML("The name of the person you requested is: " + result.getFirstName() + " " + result.getName());
            dialogBox.center();
            closeButton.setFocus(true);
          }

          @Override
          public void onFailure(Throwable caught) {
            dialogBox.setText("Remote Procedure Call - Failure");
            serverResponseLabel.addStyleName("serverResponseLabelError");
            // best way to test an exception
            try {
              throw caught;
            } catch (PersonNotFoundException e) {
              serverResponseLabel.setHTML(((PersonNotFoundException) caught).getMessage());
            } catch (Throwable e) {
              serverResponseLabel.setHTML(SERVER_ERROR);
            }
            dialogBox.center();
            closeButton.setFocus(true);
          }
        });
      }
    }

    // Add a handler to send the name to the server
    MyHandler handler = new MyHandler();
    sendButton.addClickHandler(handler);
    idField.addKeyUpHandler(handler);
  }

  private void callGetAll() {
    // TODO Server call
  }
}
