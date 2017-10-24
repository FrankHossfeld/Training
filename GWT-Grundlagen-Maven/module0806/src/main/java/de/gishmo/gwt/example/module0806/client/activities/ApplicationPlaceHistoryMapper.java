package de.gishmo.gwt.example.module0806.client.activities;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import de.gishmo.gwt.example.module0806.client.ui.detail.DetailPlace;
import de.gishmo.gwt.example.module0806.client.ui.list.ListPlace;
import de.gishmo.gwt.example.module0806.client.ui.search.SearchPlace;

/**
 * This interface is the hub of your application's navigation system. It links
 * the {@link com.google.gwt.place.shared.Place Place}s your user navigates to
 * with the browser history system &mdash; that is, it makes the browser's back
 * and forth buttons work for you, and also makes each spot in your app
 * bookmarkable.
 * <p/>
 * Its implementation is code generated based on the @WithTokenizers
 * annotation.
 */
@WithTokenizers({SearchPlace.Tokenizer.class,
                 ListPlace.Tokenizer.class,
                 DetailPlace.Tokenizer.class})
public interface ApplicationPlaceHistoryMapper
  extends PlaceHistoryMapper {
}
