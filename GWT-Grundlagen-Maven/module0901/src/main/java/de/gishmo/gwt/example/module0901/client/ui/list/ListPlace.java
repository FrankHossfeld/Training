package de.gishmo.gwt.example.module0901.client.ui.list;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class ListPlace
  extends Place {

  private String searchName;
  private String searchCity;

  //------------------------------------------------------------------------------

  public ListPlace(String searchName,
                   String searchCity) {
    super();
    this.searchName = searchName;
    this.searchCity = searchCity;
  }

  //------------------------------------------------------------------------------

  public String getSearchName() {
    return searchName;
  }

  public void setSearchName(String searchName) {
    this.searchName = searchName;
  }

  public String getSearchCity() {
    return searchCity;
  }

  public void setSearchCity(String searchCity) {
    this.searchCity = searchCity;
  }

  //------------------------------------------------------------------------------

  @Prefix("li")
  public static class Tokenizer
    implements PlaceTokenizer<ListPlace> {

    public ListPlace getPlace(String token) {
      try {
        String searchName = token.substring(0,
                                            token.indexOf("-!a0-"));
        String searchCity = token.substring(token.indexOf("-!a0-") + 5);
        //        GWT.log(searchName);
        //        GWT.log(searchCity);
        return new ListPlace(searchName,
                             searchCity);
      } catch (Exception e) {
        return new ListPlace("",
                             "");
      }
    }

    public String getToken(ListPlace place) {
      return place.getSearchName() + "-!a0-" + place.getSearchCity();
    }
  }
}
