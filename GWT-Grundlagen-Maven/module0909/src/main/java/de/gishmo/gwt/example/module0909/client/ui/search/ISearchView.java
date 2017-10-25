package de.gishmo.gwt.example.module0909.client.ui.search;

import com.google.gwt.user.client.ui.IsWidget;
import com.mvp4g.client.view.ReverseViewInterface;

public interface ISearchView
  extends ReverseViewInterface<ISearchView.Presenter>,
          IsWidget {

  void setSearch(String searchName,
                 String searchCity);

  interface Presenter {

    void doClickSearchButton(String searchName,
                             String searchCity);

  }
}
