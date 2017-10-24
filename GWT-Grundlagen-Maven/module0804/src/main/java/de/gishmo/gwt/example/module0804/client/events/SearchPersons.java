package de.gishmo.gwt.example.module0804.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class SearchPersons
  extends GwtEvent<SearchPersons.SearchPersonsHandler> {

  public static Type<SearchPersons.SearchPersonsHandler> TYPE = new Type<SearchPersons.SearchPersonsHandler>();

  private String searchName;
  private String searchCity;

  public SearchPersons(String searchName,
                       String searchCity) {
    super();
    this.searchName = searchName;
    this.searchCity = searchCity;
  }

  public String getSearchName() {
    return searchName;
  }

  public String getSearchCity() {
    return searchCity;
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
