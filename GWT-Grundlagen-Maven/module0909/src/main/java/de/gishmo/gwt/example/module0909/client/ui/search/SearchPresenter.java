package de.gishmo.gwt.example.module0909.client.ui.search;

import javax.inject.Inject;

import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

import de.gishmo.gwt.example.module0909.client.ClientContext;
import de.gishmo.gwt.example.module0909.client.Module0909EventBus;

@Presenter(view = ISearchView.class)
public class SearchPresenter
  extends BasePresenter<ISearchView, Module0909EventBus>
  implements ISearchView.Presenter {

  @Inject
  private ClientContext clientContext;

  public SearchPresenter() {
  }

  @Override
  public void doClickSearchButton(String searchName,
                                  String searchCity) {
    clientContext.setSearchCity(searchCity);
    clientContext.setSearchName(searchName);
    eventBus.gotoList(searchName,
                      searchCity);
  }

  public void bind() {
    eventBus.setCenter(view.asWidget());
  }

  public void onGotoSearch() {
    eventBus.setCenter(view.asWidget());
  }
}
