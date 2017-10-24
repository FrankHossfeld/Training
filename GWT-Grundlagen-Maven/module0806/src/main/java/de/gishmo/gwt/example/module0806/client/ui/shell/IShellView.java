package de.gishmo.gwt.example.module0809.client.ui.shell;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import de.gishmo.gwt.example.module0806.client.widgets.ReverseView;

public interface IShellView
  extends ReverseView<IShellView.Presenter>,
          IsWidget {
  
  void setCenter(Widget asWidget);

  void setNavigation(Widget widget);
  
  void setStatus(String status);

  public interface Presenter {

  }
}
