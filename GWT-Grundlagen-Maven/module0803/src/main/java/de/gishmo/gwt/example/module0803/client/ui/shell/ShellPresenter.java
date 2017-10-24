package de.gishmo.gwt.example.module0803.client.ui.shell;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import de.gishmo.gwt.example.module0803.client.ClientContext;
import de.gishmo.gwt.example.module0803.client.events.SetCenter;
import de.gishmo.gwt.example.module0803.client.events.SetNavigation;

public class ShellPresenter
  implements IShellView.Presenter,
             IsWidget{

  private ClientContext clientContext;
  private IShellView    view;
  
//------------------------------------------------------------------------------

  public ShellPresenter(ClientContext clientContext) {
    this.clientContext = clientContext;
    
    view = new ShellView(this.clientContext.getStyle());
    view.setPresenter(this);
    
    bind();
  }

//------------------------------------------------------------------------------

  @Override
  public Widget asWidget() {
    return view.asWidget();
  }

//------------------------------------------------------------------------------

  public void setNavigation(Widget widget) {
    view.setNavigation(widget);
  }

//------------------------------------------------------------------------------

  private void bind() {
    clientContext.getEventBus().addHandler(SetNavigation.TYPE,
                                           new SetNavigation.SetNavigationHandler() {
      @Override
      public void onSetNavigation(SetNavigation event) {
        view.setNavigation(event.getWidget());
      }
    });

    clientContext.getEventBus().addHandler(SetCenter.TYPE,
                                           new SetCenter.SetCenterHandler() {
      @Override
      public void onSetCenter(SetCenter event) {
        view.setCenter(event.getWidget());
      }
    });
  }
}
