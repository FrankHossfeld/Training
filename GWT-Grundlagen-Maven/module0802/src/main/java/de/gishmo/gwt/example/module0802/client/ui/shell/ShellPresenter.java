package de.gishmo.gwt.example.module0802.client.ui.shell;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import de.gishmo.gwt.example.module0802.client.ClientContext;

public class ShellPresenter
  implements IShellView.Presenter,
             IsWidget{

  private ClientContext clientContext;
  private IShellView view;
  
//------------------------------------------------------------------------------

  public ShellPresenter(ClientContext clientContext) {
    this.clientContext = clientContext;
    
    view = new ShellView(this.clientContext.getStyle());
    view.setPresenter(this);
  }

//------------------------------------------------------------------------------

  @Override
  public Widget asWidget() {
    return view.asWidget();
  }

//------------------------------------------------------------------------------

  public void setCenter(String newCenter) {
    if (ClientContext.SEARCH_FORM.equals(newCenter)) {
      view.setCenter(clientContext.getSearchPresenter().asWidget());
    } else if (ClientContext.RESULT_LIST.equals(newCenter)) {
      view.setCenter(clientContext.getListPresenter().asWidget());
    } else if (ClientContext.DETAIL_FORM.equals(newCenter)) {
      view.setCenter(clientContext.getDetailPresenter().asWidget());
    }
  }

  public void setNavigation(Widget widget) {
    view.setNavigation(widget);
  }
}
