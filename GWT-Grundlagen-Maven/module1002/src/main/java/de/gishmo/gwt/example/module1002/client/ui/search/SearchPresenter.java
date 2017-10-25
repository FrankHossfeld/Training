package de.gishmo.gwt.example.module1002.client.ui.search;

import com.google.gwt.user.client.ui.Widget;

import de.gishmo.gwt.example.module0503.shared.dto.PersonSearch;
import de.gishmo.gwt.example.module0708.client.widgets.PresentsWidgets;
import de.gishmo.gwt.example.module1002.client.ClientContext;
import de.gishmo.gwt.example.module1002.client.events.SetStatus;
import de.gishmo.gwt.example.module1002.client.resources.ApplicationConstants;
import de.gishmo.gwt.example.module1002.client.ui.list.ListPlace;

public class SearchPresenter
  implements ISearchView.Presenter,
             PresentsWidgets {

  private ClientContext clientContext;
  private ISearchView view;
  private SearchPlace place;

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
      view.setSearch(new PersonSearch(place.getSearchName(), place.getSearchCity()));
    }
    clientContext.getEventBus().fireEvent(new SetStatus(ApplicationConstants.CONSTANTS.statusSearch()));
  }

  @Override
  public void stop() {
  }

//------------------------------------------------------------------------------

  @Override
  public void doClickSearchButton(PersonSearch search) {
    clientContext.getPlaceController().goTo(new ListPlace(search.getName(), search.getCity()));
  }

//------------------------------------------------------------------------------

  private void bind() {
  }
}
