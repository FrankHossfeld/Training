package de.gishmo.gwt.example.module0809.client.ui.navigation;

import com.google.gwt.user.client.ui.IsWidget;
import com.mvp4g.client.view.ReverseViewInterface;

public interface INavigationView
  extends ReverseViewInterface<INavigationView.Presenter>,
          IsWidget {

  public interface Presenter {
    
    void doShowList();

    void doShowSearch();

  }
}
