package de.gishmo.gwt.example.module0803.client.ui.navigation;

import com.google.gwt.user.client.ui.IsWidget;
import de.gishmo.gwt.example.module0803.client.widgets.ReverseView;

public interface INavigationView
  extends ReverseView<INavigationView.Presenter>,
          IsWidget {

  public interface Presenter {
    
    void doShowList();

    void doShowSearch();

  }
}
