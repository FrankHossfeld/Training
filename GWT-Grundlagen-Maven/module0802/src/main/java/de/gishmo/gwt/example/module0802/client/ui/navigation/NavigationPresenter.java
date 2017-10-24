package de.gishmo.gwt.example.module0802.client.ui.navigation;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import de.gishmo.gwt.example.module0802.client.ClientContext;

public class NavigationPresenter
  implements INavigationView.Presenter,
             IsWidget{

  private INavigationView view;
  private ClientContext   clientContext;
  
//------------------------------------------------------------------------------

  public NavigationPresenter(ClientContext clientContext) {
    this.clientContext = clientContext;
    
    view = new NavigationView(clientContext.getStyle());
    view.setPresenter(this);
  }

//------------------------------------------------------------------------------

  @Override
  public Widget asWidget() {
    return view.asWidget();
  }

  @Override
  public void doSetCenter(String newCenter) {
    clientContext.getShellPresenter().setCenter(newCenter);
  }
}
