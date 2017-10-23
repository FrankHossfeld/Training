package de.gishmo.gwt.example.module0402.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.http.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import de.gishmo.gwt.example.module0402.shared.dto.Address;
import de.gishmo.gwt.example.module0402.shared.dto.Person;
import de.gishmo.gwt.example.module0402.shared.dto.PersonList;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Module0402 implements EntryPoint {
  
  interface MyBeanFactory 
    extends AutoBeanFactory {
    AutoBean<Address> address();
    AutoBean<Person> person();
    AutoBean<PersonList> result();
  }
  
  private MyBeanFactory myBeanFactory = GWT.create(MyBeanFactory.class);
  
  
  /**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
  private static final String SERVER_ERROR = "An error occurred while "
      + "attempting to contact the server. Please check your network "
      + "connection and try again.";

  private static final String JSON_URL = GWT.getModuleBaseURL() + "module0402?";

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
        // URL
        String url = JSON_URL + "fc=01&id=" + Long.toString(id);
        url = URL.encode(url);
  
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
        try {
          @SuppressWarnings("unused")
          Request request = builder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable exception) {
              dialogBox.setText("Remote Procedure Call - Failure");
              serverResponseLabel.addStyleName("serverResponseLabelError");
              // best way to test an exception
              try {
                throw exception;
              } catch (Exception e) {
                serverResponseLabel.setHTML(SERVER_ERROR);
              } catch (Throwable e) {
                serverResponseLabel.setHTML(SERVER_ERROR);
              }
              dialogBox.center();
              closeButton.setFocus(true);
            }
    
            public void onResponseReceived(Request request, Response response) {
              if (200 == response.getStatusCode()) {
                GWT.log(response.getText());
                dialogBox.setText("Remote Procedure Call");
                serverResponseLabel.removeStyleName("serverResponseLabelError");
                Person person = deserializePersonFromJSON(response.getText());
                serverResponseLabel.setHTML("The name of the person you requested is: " + person.getFirstName() + " " + person.getName());
              } else {
                Window.alert("Couldn't retrieve JSON (" + response.getStatusText() + ")");
              }
              dialogBox.center();
              closeButton.setFocus(true);
           }
          });
        } catch (RequestException e) {
          Window.alert("Couldn't retrieve JSON");
        }
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
        // URL
        String url = JSON_URL + "fc=02";
        url = URL.encode(url);
  
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
        try {
          @SuppressWarnings("unused")
          Request request = builder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable exception) {
              dialogBox.setText("Remote Procedure Call - Failure");
              serverResponseLabel.addStyleName("serverResponseLabelError");
              // best way to test an exception
              try {
                throw exception;
              } catch (Exception e) {
                serverResponseLabel.setHTML(SERVER_ERROR);
              } catch (Throwable e) {
                serverResponseLabel.setHTML(SERVER_ERROR);
              }
              dialogBox.center();
              closeButton.setFocus(true);
            }
    
            public void onResponseReceived(Request request, Response response) {
              if (200 == response.getStatusCode()) {
                GWT.log(response.getText());
                dialogBox.setText("Remote Procedure Call");
                serverResponseLabel.removeStyleName("serverResponseLabelError");
                String html = "";
                PersonList result = deserializePersonListFromJSON(response.getText());
                for (Person person : result.getPersons()) {
                  html += person.getFirstName() + " " + person.getName() + "<br>";
                }
                serverResponseLabel.setHTML(html);
              } else {
                Window.alert("Couldn't retrieve JSON (" + response.getStatusText() + ")");
              }
              dialogBox.center();
              closeButton.setFocus(true);
            }
          });
        } catch (RequestException e) {
          Window.alert("Couldn't retrieve JSON");
        }
      }
    }

    // Add a handler to send the name to the server
    MyHandler01 handler01 = new MyHandler01();
    sendButton.addClickHandler(handler01);
    MyHandler02 handler02 = new MyHandler02();
    sendAllButton.addClickHandler(handler02);
  }

//  private Person createPerson() {
//    AutoBean<Person> person = myBeanFactory.person();
//    return person.as();
//  }
  
  private Person deserializePersonFromJSON(String json) {
    AutoBean<Person> bean = AutoBeanCodex.decode(myBeanFactory, Person.class, json); 
    return bean.as();
  }
  
  private PersonList deserializePersonListFromJSON(String json) {
    AutoBean<PersonList> bean = AutoBeanCodex.decode(myBeanFactory, PersonList.class, json); 
    return bean.as();
  }
  
//  private String serializeFromJSON(Person person) {
//    AutoBean<Person> bean = AutoBeanUtils.getAutoBean(person);
//    return AutoBeanCodex.encode(bean).getPayload();
//  }
}
