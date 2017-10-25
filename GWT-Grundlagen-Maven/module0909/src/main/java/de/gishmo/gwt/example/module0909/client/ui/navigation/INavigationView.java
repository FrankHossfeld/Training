package de.gishmo.gwt.example.module0909.client.ui.navigation;

import com.google.gwt.user.client.ui.IsWidget;
import com.mvp4g.client.view.ReverseViewInterface;

public interface INavigationView
  extends ReverseViewInterface<INavigationView.Presenter>,
          IsWidget {

  void createView();

  public interface Presenter {

    void doShowList();

    void doShowSearch();

  }
}
