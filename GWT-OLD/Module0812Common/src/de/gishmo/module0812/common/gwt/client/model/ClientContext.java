package de.gishmo.module0812.common.gwt.client.model;

import de.gishmo.module0812.domain.dto.shared.search.PersonSearch;

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
