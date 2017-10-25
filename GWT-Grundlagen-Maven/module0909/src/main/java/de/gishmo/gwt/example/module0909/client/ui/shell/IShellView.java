package de.gishmo.gwt.example.module0909.client.ui.shell;

import com.google.gwt.user.client.ui.IsWidget;
import com.mvp4g.client.view.ReverseViewInterface;

public interface IShellView
  extends ReverseViewInterface<IShellView.Presenter>,
          IsWidget {

  void setCenter(IsWidget asWidget);

  void setNavigation(IsWidget widget);

  public interface Presenter {

  }
}
