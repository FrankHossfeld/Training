package de.gishmo.gwt.example.module0901.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import de.gishmo.gwt.example.module0503.shared.dto.PersonSearch;

public class SearchPersons
  extends GwtEvent<SearchPersons.SearchPersonsHandler> {

  public static Type<SearchPersons.SearchPersonsHandler> TYPE = new Type<SearchPersons.SearchPersonsHandler>();

  private PersonSearch search;
  
  public SearchPersons(PersonSearch search) {
    super();
    this.search = search;
  }

  public PersonSearch getSearch() {
    return search;
  }

  @Override
  public Type<SearchPersons.SearchPersonsHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(SearchPersons.SearchPersonsHandler handler) {
    handler.onSearchPersons(this);
  }

  
  public interface SearchPersonsHandler
    extends EventHandler {
    
    void onSearchPersons(SearchPersons event);
  
  }
}
