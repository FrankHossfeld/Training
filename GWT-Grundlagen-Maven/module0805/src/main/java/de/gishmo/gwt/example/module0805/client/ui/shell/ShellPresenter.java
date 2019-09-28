package de.gishmo.gwt.example.module0805.client.ui.shell;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import de.gishmo.gwt.example.module0805.client.ClientContext;
import de.gishmo.gwt.example.module0805.client.events.SetNavigation;
import de.gishmo.gwt.example.module0805.client.events.SetStatus;

public class ShellPresenter
  implements IShellView.Presenter,
             AcceptsOneWidget,
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
  
  @Override
  public void setWidget(IsWidget widget) {
    if (widget != null) {
      view.setCenter(widget.asWidget());
    }
  }

//------------------------------------------------------------------------------

  private void bind() {
    clientContext.getEventBus().addHandler(SetNavigation.TYPE,
                                           event -> view.setNavigation(event.getWidget()));

    clientContext.getEventBus().addHandler(SetStatus.TYPE,
                                           event -> view.setStatus(event.getStatus()));
  }

}
