package de.gishmo.gwt.example.module0909.client.ui.navigation;

import javax.inject.Inject;

import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

import de.gishmo.gwt.example.module0909.client.Module0909EventBus;
import de.gishmo.gwt.example.module0909.client.model.ClientContext;

@Presenter(view = INavigationView.class)
public class NavigationPresenter
  extends BasePresenter<INavigationView, Module0909EventBus>
  implements INavigationView.Presenter {

  @Inject
  private ClientContext clientContext;

  public NavigationPresenter() {
  }

  @Override
  public void bind() {
    eventBus.setNavigation(view.asWidget());
  }

  @Override
  public void doShowList() {
    if (clientContext.getPersonSearch() != null) {
      eventBus.gotoList(clientContext.getPersonSearch()
                                     .getName(),
                        clientContext.getPersonSearch()
                                     .getCity());
    } else {
      eventBus.gotoSearch("",
                          "");
    }
  }

  @Override
  public void doShowSearch() {
    eventBus.gotoSearch(clientContext.getPersonSearch()
                                     .getName(),
                        clientContext.getPersonSearch()
                                     .getCity());
  }
}
