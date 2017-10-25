package de.gishmo.gwt.example.module0901.client.ui.navigation;

import de.gishmo.gwt.example.module0503.shared.dto.PersonSearch;
import de.gishmo.gwt.example.module0901.client.ClientContext;
import de.gishmo.gwt.example.module0901.client.events.SearchPersons;
import de.gishmo.gwt.example.module0901.client.events.SetNavigation;
import de.gishmo.gwt.example.module0901.client.ui.list.ListPlace;
import de.gishmo.gwt.example.module0901.client.ui.search.SearchPlace;

public class NavigationPresenter
  implements INavigationView.Presenter {

  private INavigationView view;
  private ClientContext   clientContext;
  
  private PersonSearch search;
  
//------------------------------------------------------------------------------

  public NavigationPresenter(ClientContext clientContext) {
    this.clientContext = clientContext;
    
    view = new NavigationView(clientContext.getStyle());
    view.setPresenter(this);
    
    bind();
  }

//------------------------------------------------------------------------------

  @Override
  public void doShowList() {
    clientContext.getPlaceController().goTo(createListPlace());
  }

  @Override
  public void doShowSearch() {
    clientContext.getPlaceController().goTo(createSearchPlace());
  }


//------------------------------------------------------------------------------

  private void bind() {
    clientContext.getEventBus().addHandler(SearchPersons.TYPE,
                                           new SearchPersons.SearchPersonsHandler() {
      @Override
      public void onSearchPersons(SearchPersons event) {
        search = event.getSearch();
      }
    });
    
    clientContext.getEventBus().fireEvent(new SetNavigation(view.asWidget()));
  }

  private ListPlace createListPlace() {
    if (search == null) {
      return new ListPlace("", "");
    } else {
      return new ListPlace(search.getName(), search.getCity());
    }
  }

  private SearchPlace createSearchPlace() {
    if (search == null) {
      return new SearchPlace("", "");
    } else {
      return new SearchPlace(search.getName(), search.getCity());
    }
  }
}
