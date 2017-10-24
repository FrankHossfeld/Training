package de.gishmo.gwt.example.module0809.client.ui.search;

import com.google.gwt.user.client.ui.Widget;
import de.gishmo.gwt.example.module0809.client.ClientContext;
import de.gishmo.gwt.example.module0809.client.events.SetStatus;
import de.gishmo.gwt.example.module0809.client.resources.ApplicationConstants;
import de.gishmo.gwt.example.module0809.client.ui.list.ListPlace;
import de.gishmo.gwt.example.module0809.client.widgets.PresentsWidgets;

public class SearchPresenter
  implements ISearchView.Presenter,
             PresentsWidgets {

  private ClientContext clientContext;
  private ISearchView   view;
  private SearchPlace   place;

//------------------------------------------------------------------------------

  public SearchPresenter(ClientContext clientContext,
                         SearchPlace place) {
    this.clientContext = clientContext;
    this.place = place;

    view = new SearchView(this.clientContext.getStyle());
    view.setPresenter(this);
    
    bind();
  }

//------------------------------------------------------------------------------

  @Override
  public Widget asWidget() {
    return view.asWidget();
  }

  @Override
  public String mayStop() {
    return null; // always happy to stop
  }

  @Override
  public void start() {
    if (place != null) {
      view.setSearch(place.getSearchName(), place.getSearchCity());
    }
    clientContext.getEventBus().fireEvent(new SetStatus(ApplicationConstants.CONSTANTS.statusSearch()));
  }

  @Override
  public void stop() {
  }

//------------------------------------------------------------------------------

  @Override
  public void doClickSearchButton(String searchName,
                                  String searchCity) {
    clientContext.getPlaceController().goTo(new ListPlace(searchName, searchCity));
  }

//------------------------------------------------------------------------------

  private void bind() {
  }
}
