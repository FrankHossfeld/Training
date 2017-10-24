package de.gishmo.gwt.example.module0803.client.ui.navigation;

import de.gishmo.gwt.example.module0803.client.ClientContext;
import de.gishmo.gwt.example.module0803.client.events.SetNavigation;
import de.gishmo.gwt.example.module0803.client.events.ShowList;
import de.gishmo.gwt.example.module0803.client.events.ShowSearch;

public class NavigationPresenter
  implements INavigationView.Presenter {

  private INavigationView view;
  private ClientContext   clientContext;
  
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
    clientContext.getEventBus().fireEvent(new ShowList());
  }

  @Override
  public void doShowSearch() {
    clientContext.getEventBus().fireEvent(new ShowSearch());
  }


//------------------------------------------------------------------------------

  private void bind() {
    clientContext.getEventBus().fireEvent(new SetNavigation(view.asWidget()));
  }
}
