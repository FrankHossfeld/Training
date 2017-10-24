package de.gishmo.gwt.example.module0809.client.model;

import de.gishmo.gwt.example.module0503.shared.dto.PersonSearch;

public class ClientContext {
  
  {
    this.personSearch = new PersonSearch();
  }

  /* searchs */
  private PersonSearch personSearch;
 
  public ClientContext() {
  }

  public PersonSearch getPersonSearch() {
    return personSearch;
  }

  public void setPersonSearch(PersonSearch personSearch) {
    this.personSearch = personSearch;
  }
}
