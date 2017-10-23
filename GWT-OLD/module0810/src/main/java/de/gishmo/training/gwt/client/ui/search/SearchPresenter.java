package de.gishmo.training.gwt.client.ui.search;

import javax.inject.Inject;

import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

import de.gishmo.module0503.shared.dto.PersonSearch;
import de.gishmo.training.gwt.client.Module0810EventBus;
import de.gishmo.training.gwt.client.model.ClientContext;

@Presenter(view = ISearchView.class)
public class SearchPresenter
  extends BasePresenter<ISearchView, Module0810EventBus>
  implements ISearchView.Presenter {

  @Inject
  private ClientContext clientContext;

  public SearchPresenter() {
    super();
  }

  public void onInitHistory() {
    onGotoSearch("",
                 "");
    ;
  }

  public void onGotoSearch(String searchName,
                           String searchCity) {
    view.setSearch(searchName,
                   searchCity);
    eventBus.setContent(view.asWidget());
  }

  @Override
  public void doClickSearchButton(String searchName,
                                  String searchCity) {
    // fuer NavigatiponPresenter speichern ...
    clientContext.setPersonSearch(new PersonSearch(searchName,
                                                   searchCity));
    eventBus.gotoList(searchName,
                      searchCity);
  }

  @Override
  public void bind() {
  }
}
