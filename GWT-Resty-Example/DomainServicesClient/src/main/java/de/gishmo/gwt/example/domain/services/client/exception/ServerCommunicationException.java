package de.gishmo.gwt.example.domain.services.client.exception;

@SuppressWarnings("serial")
public class ServerCommunicationException
  extends Exception {

  public ServerCommunicationException() {
  }

  public ServerCommunicationException(String s) {
    super(s);
  }
}
