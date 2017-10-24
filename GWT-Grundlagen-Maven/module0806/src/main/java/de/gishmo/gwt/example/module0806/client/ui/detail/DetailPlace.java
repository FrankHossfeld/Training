package de.gishmo.gwt.example.module0809.client.ui.detail;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class DetailPlace
  extends Place {

  private long id;

//------------------------------------------------------------------------------


  public DetailPlace(long id) {
    super();
    this.id = id;
  }

//------------------------------------------------------------------------------

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

//------------------------------------------------------------------------------

  @Prefix("de")
  public static class Tokenizer
    implements PlaceTokenizer<DetailPlace> {

    public DetailPlace getPlace(String token) {
      try {
        Long id = Long.parseLong(token);
        return new DetailPlace(id);
      } catch (Exception e) {
        return new DetailPlace(0);
      }
    }

    public String getToken(DetailPlace place) {
      return Long.toString(place.getId());
    }
  }
}
