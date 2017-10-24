package de.gishmo.gwt.example.module0802.client.ui.search;

import com.google.gwt.user.client.ui.IsWidget;
import de.gishmo.gwt.example.module0802.client.widgets.ReverseView;

public interface ISearchView
  extends ReverseView<ISearchView.Presenter>,
          IsWidget {

  public interface Presenter {

    void doClickSearchButton(String searchName, String searchCity);

  }
}
