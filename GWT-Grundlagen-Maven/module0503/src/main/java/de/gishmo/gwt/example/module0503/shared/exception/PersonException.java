package de.gishmo.gwt.example.module0503.shared.exception;

import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class PersonException
  extends Exception
  implements IsSerializable {

  /* for serialization only */
  @SuppressWarnings("unused")
  private PersonException() {
  }

  public PersonException(String message) {
    super(message);
  }
}
