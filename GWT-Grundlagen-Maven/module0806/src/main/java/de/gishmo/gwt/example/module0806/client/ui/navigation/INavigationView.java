package de.gishmo.gwt.example.module0809.client.ui.navigation;

import com.google.gwt.user.client.ui.IsWidget;
import de.gishmo.gwt.example.module0809.client.widgets.ReverseView;

public interface INavigationView
  extends ReverseView<INavigationView.Presenter>,
          IsWidget {

  public interface Presenter {
    
    void doShowList();

    void doShowSearch();

  }
}
