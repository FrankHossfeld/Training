package de.gishmo.gwt.example.module0909.client.ui.navigation;

import javax.inject.Inject;

import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

import de.gishmo.gwt.example.module0909.client.ClientContext;
import de.gishmo.gwt.example.module0909.client.Module0909EventBus;

@Presenter(view = INavigationView.class)
public class NavigationPresenter
  extends BasePresenter<INavigationView, Module0909EventBus>
  implements INavigationView.Presenter {

  @Inject
  private ClientContext clientContext;

  //------------------------------------------------------------------------------

  public NavigationPresenter() {
  }

  //------------------------------------------------------------------------------

  @Override
  public void doShowList() {
    eventBus.gotoList(clientContext.getSearchName(),
                      clientContext.getSearchCity());
  }

  @Override
  public void doShowSearch() {
    eventBus.gotoSearch();
  }

  //------------------------------------------------------------------------------
  //
  //  public void onSelectNavigation(String selectedNavigation) {
  //
  //  }

  public void bind() {
    eventBus.setNavigation(view.asWidget());
  }
}
