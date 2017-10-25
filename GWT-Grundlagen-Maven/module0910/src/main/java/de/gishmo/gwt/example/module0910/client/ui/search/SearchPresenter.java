package de.gishmo.gwt.example.module0910.client.ui.search;

import com.google.gwt.core.client.GWT;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;
import de.gishmo.gwt.example.module0503.shared.dto.PersonSearch;
import de.gishmo.gwt.example.module0910.client.model.ClientContext;
import de.gishmo.gwt.example.module0910.client.module0910EventBus;

import javax.inject.Inject;

@Presenter(view = ISearchView.class)
public class SearchPresenter
  extends BasePresenter<ISearchView, module0910EventBus>
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
    view.setSearch(clientContext.getPersonSearch());
    eventBus.setContent(view.asWidget());
  }

  @Override
  public void doClickSearchButton(PersonSearch search) {
    GWT.debugger();
    // fuer NavigatiponPresenter speichern ...
    clientContext.setPersonSearch(search);
    eventBus.gotoList(search.getName(),
                      search.getCity());
  }

  @Override
  public void bind() {
  }
}
