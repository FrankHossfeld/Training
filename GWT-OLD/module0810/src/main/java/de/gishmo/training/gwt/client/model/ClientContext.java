package de.gishmo.training.gwt.client.model;

import de.gishmo.module0503.shared.dto.PersonSearch;

public class ClientContext {

  /* searchs */
  private PersonSearch personSearch;

  {
    this.personSearch = new PersonSearch();
  }

  public ClientContext() {
  }

  public PersonSearch getPersonSearch() {
    return personSearch;
  }

  public void setPersonSearch(PersonSearch personSearch) {
    this.personSearch = personSearch;
  }
}
