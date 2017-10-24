package de.gishmo.gwt.example.module0803.client.ui.search;

import de.gishmo.gwt.example.module0803.client.ClientContext;
import de.gishmo.gwt.example.module0803.client.events.SearchPersons;
import de.gishmo.gwt.example.module0803.client.events.SetCenter;
import de.gishmo.gwt.example.module0803.client.events.ShowSearch;

public class SearchPresenter
  implements ISearchView.Presenter {

  private ClientContext clientContext;
  private ISearchView   view;
  
//------------------------------------------------------------------------------

  public SearchPresenter(ClientContext clientContext) {
    this.clientContext = clientContext;
    
    view = new SearchView(this.clientContext.getStyle());
    view.setPresenter(this);
    
    bind();
  }

//------------------------------------------------------------------------------

  @Override
  public void doClickSearchButton(String searchName,
                                  String searchCity) {
    clientContext.getEventBus().fireEvent(new SearchPersons(searchName,
                                                            searchCity));
  }

//------------------------------------------------------------------------------

  private void bind() {
    clientContext.getEventBus().addHandler(ShowSearch.TYPE,
                                           new ShowSearch.ShowSearchHandler() {
      @Override
      public void onShowSearch(ShowSearch event) {
        clientContext.getEventBus().fireEvent(new SetCenter(view.asWidget()));
      }
    });
  }
}
