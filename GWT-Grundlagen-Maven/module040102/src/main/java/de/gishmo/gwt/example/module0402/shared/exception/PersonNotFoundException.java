package de.gishmo.gwt.example.module0402.shared.exception;

import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class PersonNotFoundException
  extends Exception
  implements IsSerializable {

  /* for serialization only */
  @SuppressWarnings("unused")
  private PersonNotFoundException() {
  }

  public PersonNotFoundException(String message) {
    super(message);
  }
}
