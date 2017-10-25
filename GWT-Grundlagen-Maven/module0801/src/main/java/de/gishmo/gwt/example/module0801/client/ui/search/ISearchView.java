package de.gishmo.gwt.example.module0801.client.ui.search;

import com.google.gwt.user.client.ui.IsWidget;
import de.gishmo.gwt.example.module0801.client.widgets.ReverseView;

public interface ISearchView
  extends ReverseView<ISearchView.Presenter>,
          IsWidget {

  interface Presenter {

    void doClickSearchButton(String searchName,
                             String searchCity);

  }
}
