package de.gishmo.gwt.example.module0404.shared.results;

import net.customware.gwt.dispatch.shared.Result;
import de.gishmo.gwt.example.module0404.shared.dto.Person;

public class GetPersonResult
  implements Result {

  private Person person;
  
  /* for serialization only */
  @SuppressWarnings("unused")
  private GetPersonResult() {
  }

  public GetPersonResult(Person person) {
    this.person = person;
  }

  public Person getPerson() {
    return person;
  }
}
