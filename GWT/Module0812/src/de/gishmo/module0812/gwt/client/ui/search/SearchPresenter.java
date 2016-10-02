package de.gishmo.module0812.gwt.client.ui.search;

import javax.inject.Inject;

import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

import de.gishmo.module0812.domain.dto.shared.search.PersonSearch;
import de.gishmo.module0812.gwt.client.Module0812EventBus;
import de.gishmo.module0812.gwt.client.model.ClientContext;

@Presenter(view = ISearchView.class)
public class SearchPresenter extends BasePresenter<ISearchView, Module0812EventBus>
implements ISearchView.Presenter {

   @Inject
   private ClientContext clientContext;

   public SearchPresenter() {
      super();
   }

   public void onInitHistory() {
      onGotoSearch("", "");;
   }

   public void onGotoSearch(final String searchName,
                            final String searchCity) {
      view.setSearch(searchName, searchCity);
      eventBus.setContent(view.asWidget());
   }

   @Override
   public void doClickSearchButton(final String searchName,
                                   final String searchCity) {
      // fuer NavigationPresenter speichern ...
      clientContext.setPersonSearch(new PersonSearch(searchName, searchCity));
      eventBus.gotoList(searchName, searchCity);
   }

   @Override
   public void bind() {
   }
}
