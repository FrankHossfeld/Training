package de.gishmo.gwt.example.module0909.client.ui.search;

import com.google.gwt.user.client.ui.IsWidget;
import com.mvp4g.client.view.ReverseViewInterface;

import de.gishmo.gwt.example.module0503.shared.dto.PersonSearch;

public interface ISearchView
  extends ReverseViewInterface<ISearchView.Presenter>,
          IsWidget {

  void setSearch(PersonSearch search);

  public interface Presenter {

    void doClickSearchButton(PersonSearch search);

  }
}
