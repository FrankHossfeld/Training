package de.gishmo.gwt.example.module0805.client.ui.search;

import com.google.gwt.user.client.ui.IsWidget;
import de.gishmo.gwt.example.module0805.client.widgets.ReverseView;

public interface ISearchView
  extends ReverseView<ISearchView.Presenter>,
          IsWidget {
  
  void setSearch(String searchName, String searchCity);

  public interface Presenter {

    void doClickSearchButton(String searchName, String searchCity);

  }
}
