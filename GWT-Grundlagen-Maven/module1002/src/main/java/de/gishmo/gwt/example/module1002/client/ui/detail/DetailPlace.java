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
package de.gishmo.gwt.example.module1002.client.ui.detail;

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
