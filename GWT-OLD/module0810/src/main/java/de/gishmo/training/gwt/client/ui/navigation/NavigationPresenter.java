package de.gishmo.training.gwt.client.ui.navigation;

import javax.inject.Inject;

import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

import de.gishmo.training.gwt.client.Module0810EventBus;
import de.gishmo.training.gwt.client.model.ClientContext;

@Presenter(view = INavigationView.class)
public class NavigationPresenter
  extends BasePresenter<INavigationView, Module0810EventBus>
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
