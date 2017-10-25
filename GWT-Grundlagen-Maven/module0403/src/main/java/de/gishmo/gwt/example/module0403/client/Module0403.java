package de.gishmo.gwt.example.module0403.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import de.gishmo.gwt.example.module0403.shared.PersonProxy;

import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Module0403 implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    // create and initialize the requestfactory
    final PersonRequestFactory personFactory = GWT.create(PersonRequestFactory.class);
    personFactory.initialize(new SimpleEventBus());


    final Button sendButton = new Button("Send");
    final TextBox idField = new TextBox();
    idField.setText("Please enter id");
    final Label errorLabel = new Label();
    final Button sendAllButton = new Button("Request all persons");

    // We can add style names to widgets
    sendAllButton.addStyleName("sendButton");

    // Add the nameField and sendButton to the RootPanel
    // Use RootPanel.get() to get the entire body element
    RootPanel.get("nameFieldContainer").add(idField);
    RootPanel.get("sendButtonContainer").add(sendButton);
    RootPanel.get("errorLabelContainer").add(errorLabel);
    RootPanel.get("sendAllButtonContainer").add(sendAllButton);

    // Create the popup dialog box
    final DialogBox dialogBox = new DialogBox();
    dialogBox.setText("Remote Procedure Call");
    dialogBox.setAnimationEnabled(true);
    final Button closeButton = new Button("Close");
    // We can set the id of a widget by accessing its Element
    closeButton.getElement().setId("closeButton");
    final HTML serverResponseLabel = new HTML();
    final Label textToServerLabel = new Label();
    VerticalPanel dialogVPanel = new VerticalPanel();
    dialogVPanel.addStyleName("dialogVPanel");
    dialogVPanel.add(new HTML("<b>Sending request to the server.</b>"));
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
        sendAllButton.setEnabled(true);
      }
    });

    // Create a handler for the sendButton and nameField
    class MyHandler01 implements ClickHandler, KeyUpHandler {
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
        final String textToServer = idField.getText();
        if (textToServer == null  || textToServer.trim().length() == 0) {
          errorLabel.setText("Please enter an ID");
          return;
        }
        // check if id is a valid number 
        try {
          Long.parseLong(textToServer);
        } catch (NumberFormatException e) {
          errorLabel.setText("Please enter a numeric value");
          return;
        }

        // Then, we send the input to the server.
        sendButton.setEnabled(false);
        textToServerLabel.setText(textToServer);
        serverResponseLabel.setText("");
 
        
        personFactory.personRequest().findById(textToServer).fire(new Receiver<PersonProxy>() {
          @Override
          public void onSuccess(PersonProxy response) {
            dialogBox.setText("Remote Procedure Call");
            serverResponseLabel.removeStyleName("serverResponseLabelError");
            if (response != null) {
              serverResponseLabel.setHTML("The name of the person you requested is: " + response.getFirstName() + " " + response.getName());
            } else {
              serverResponseLabel.setHTML("no data found for key: " + textToServer);
            }
            dialogBox.center();
            closeButton.setFocus(true);
          }
        });
      }
    }

    // Create a handler for the sendButton and nameField
    class MyHandler02 implements ClickHandler, KeyUpHandler {
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
        // Then, we send the input to the server.
        sendAllButton.setEnabled(false);
        serverResponseLabel.setText("");
        
        personFactory.personRequest().getAll().fire(new Receiver<List<PersonProxy>>() {
          @Override
          public void onSuccess(List<PersonProxy> response) {
            dialogBox.setText("Remote Procedure Call");
            serverResponseLabel.removeStyleName("serverResponseLabelError");
            String html = "";
            for (PersonProxy person : response) {
              html += person.getFirstName() + " " + person.getName() + "<br>";
            }
            serverResponseLabel.setHTML(html);
            dialogBox.center();
            closeButton.setFocus(true);
          }
        });
      }
    }

    // Add a handler to send the name to the server
    MyHandler01 handler01 = new MyHandler01();
    sendButton.addClickHandler(handler01);
    MyHandler02 handler02 = new MyHandler02();
    sendAllButton.addClickHandler(handler02);
  }
}
