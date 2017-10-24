package de.gishmo.gwt.example.module0806.client.ui.search;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class SearchPlace
  extends Place {

  private String searchName;
  private String searchCity;

//------------------------------------------------------------------------------


  public SearchPlace(String searchName, String searchCity) {
    super();
    this.searchName = searchName;
    this.searchCity = searchCity;
  }

//------------------------------------------------------------------------------

  public String getSearchName() {
    return searchName;
  }

  public String getSearchCity() {
    return searchCity;
  }

  public void setSearchName(String searchName) {
    this.searchName = searchName;
  }

  public void setSearchCity(String searchCity) {
    this.searchCity = searchCity;
  }

//------------------------------------------------------------------------------

  @Prefix("se")
  public static class Tokenizer
    implements PlaceTokenizer<SearchPlace> {

    public SearchPlace getPlace(String token) {
      try {
//        GWT.log(token);
//        GWT.log(Integer.toString(token.indexOf("-!B0-")));
        String searchName = token.substring(0, token.indexOf("-!B0-"));
        String searchCity = token.substring(token.indexOf("-!B0-") + 5);
//        GWT.log(searchName);
//        GWT.log(searchCity);
        return new SearchPlace(searchName, searchCity);
      } catch (Exception e) {
        return new SearchPlace("", "");
      }
    }

    public String getToken(SearchPlace place) {
      return place.getSearchName() + "-!B0-" + place.getSearchCity();
    }
  }
}
