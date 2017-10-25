package de.gishmo.gwt.example.module0404.shared.exceptions;

import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class PersonNotFoundException
  extends DispatchException
  implements IsSerializable {

  /* for serialization only */
  @SuppressWarnings("unused")
  private PersonNotFoundException() {
  }
  
  public PersonNotFoundException(String message) {
    super(message);
  }
}
