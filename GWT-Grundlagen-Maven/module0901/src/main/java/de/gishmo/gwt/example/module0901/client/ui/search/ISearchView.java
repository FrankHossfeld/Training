package de.gishmo.gwt.example.module0901.client.ui.search;

import com.google.gwt.user.client.ui.IsWidget;
import de.gishmo.gwt.example.module0503.shared.dto.PersonSearch;
import de.gishmo.gwt.example.module0708.client.widgets.ReverseView;

public interface ISearchView
  extends ReverseView<ISearchView.Presenter>,
          IsWidget {
  
  void setSearch(PersonSearch search);

  public interface Presenter {

    void doClickSearchButton(PersonSearch search);

  }
}
