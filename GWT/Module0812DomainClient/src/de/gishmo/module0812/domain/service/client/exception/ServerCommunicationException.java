package de.gishmo.module0812.domain.service.client.exception;

@SuppressWarnings("serial")
public class ServerCommunicationException
  extends Exception {

  public ServerCommunicationException() {
  }

  public ServerCommunicationException(String s) {
    super(s);
  }
}
