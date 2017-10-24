package de.gishmo.gwt.example.module0803.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class ShowSearch
  extends GwtEvent<ShowSearch.ShowSearchHandler> {

  public static Type<ShowSearch.ShowSearchHandler> TYPE = new Type<ShowSearch.ShowSearchHandler>();
  
  public ShowSearch() {
    super();
  }

  @Override
  public Type<ShowSearch.ShowSearchHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(ShowSearch.ShowSearchHandler handler) {
    handler.onShowSearch(this);
  }

  
  public interface ShowSearchHandler
    extends EventHandler {
    
    void onShowSearch(ShowSearch event);
  
  }
}
