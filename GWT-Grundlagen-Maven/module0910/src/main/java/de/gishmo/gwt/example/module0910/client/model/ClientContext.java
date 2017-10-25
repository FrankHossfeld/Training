package de.gishmo.gwt.example.module0910.client.model;

import de.gishmo.gwt.example.module0503.shared.dto.PersonSearch;

public class ClientContext {

  /* searchs */
  private PersonSearch personSearch;

  public ClientContext() {
    this.personSearch = new PersonSearch();
  }

  public PersonSearch getPersonSearch() {
    return personSearch;
  }

  public void setPersonSearch(PersonSearch personSearch) {
    this.personSearch = personSearch;
  }
}
