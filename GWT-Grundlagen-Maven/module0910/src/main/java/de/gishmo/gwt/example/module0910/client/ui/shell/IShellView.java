package de.gishmo.gwt.example.module0910.client.ui.shell;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.mvp4g.client.view.ReverseViewInterface;

public interface IShellView
  extends ReverseViewInterface<IShellView.Presenter>,
          IsWidget {

  void setCenter(Widget asWidget);

  void setNavigation(Widget widget);

  void setStatus(String status);

  public interface Presenter {
  }
}
