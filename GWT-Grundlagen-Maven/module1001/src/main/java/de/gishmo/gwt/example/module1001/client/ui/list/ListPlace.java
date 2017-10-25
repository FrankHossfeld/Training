/*
 * Copyright 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package de.gishmo.gwt.example.module1001.client.ui.list;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class ListPlace
  extends Place {

  private String searchName;
  private String searchCity;

//------------------------------------------------------------------------------


  public ListPlace(String searchName, String searchCity) {
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

  @Prefix("li")
  public static class Tokenizer
    implements PlaceTokenizer<ListPlace> {

    public ListPlace getPlace(String token) {
      try {
        String searchName = token.substring(0, token.indexOf("-!a0-"));
        String searchCity = token.substring(token.indexOf("-!a0-") + 5);
//        GWT.log(searchName);
//        GWT.log(searchCity);
        return new ListPlace(searchName, searchCity);
      } catch (Exception e) {
        return new ListPlace("", "");
      }
    }

    public String getToken(ListPlace place) {
      return place.getSearchName() + "-!a0-" + place.getSearchCity();
    }
  }
}
