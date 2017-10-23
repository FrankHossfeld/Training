package de.gishmo.gwt.example.module050202.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import de.gishmo.gwt.example.module050201.client.PersonService;
import de.gishmo.gwt.example.module050201.client.PersonServiceAsync;
import de.gishmo.gwt.example.module050201.shared.dto.Person;
import de.gishmo.gwt.example.module050201.shared.exception.PersonNotFoundException;

import java.util.List;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Module050202 implements EntryPoint {
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
  private final PersonServiceAsync personService = GWT.create(PersonService.class);

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
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
        
        personService.get(id, new AsyncCallback<Person>() {
          
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
            } catch (Exception e) {
              serverResponseLabel.setHTML(SERVER_ERROR);
            } catch (Throwable e) {
              serverResponseLabel.setHTML(SERVER_ERROR);
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
        personService.getAll(new AsyncCallback<List<Person>>() {
          @Override
          public void onFailure(Throwable caught) {
            dialogBox.setText("Remote Procedure Call - Failure");
            serverResponseLabel.addStyleName("serverResponseLabelError");
            // best way to test an exception
            try {
              throw caught;
            } catch (Exception e) {
              serverResponseLabel.setHTML(SERVER_ERROR);
            } catch (Throwable e) {
              serverResponseLabel.setHTML(SERVER_ERROR);
            }
            dialogBox.center();
            closeButton.setFocus(true);
          }

          @Override
          public void onSuccess(List<Person> result) {
            dialogBox.setText("Remote Procedure Call");
            serverResponseLabel.removeStyleName("serverResponseLabelError");
            String html = "";
            for (Person person : result) {
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
