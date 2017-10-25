package de.gishmo.gwt.example.module0404.shared.results;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;
import de.gishmo.gwt.example.module0404.shared.dto.Person;

public class GetAllPersonsResult
  implements Result {

  private List<Person> persons;
  
  /* for serialization only */
  @SuppressWarnings("unused")
  private GetAllPersonsResult() {
  }

  public GetAllPersonsResult(List<Person> persons) {
    this.persons = persons;
  }

  public List<Person> getPersons() {
    return persons;
  }
}
