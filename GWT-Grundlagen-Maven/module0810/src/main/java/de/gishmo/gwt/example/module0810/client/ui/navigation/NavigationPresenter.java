package de.gishmo.gwt.example.module0810.client.ui.navigation;

import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;
import de.gishmo.gwt.example.module0810.client.Module0810EventBus;
import de.gishmo.gwt.example.module0810.client.model.ClientContext;

import javax.inject.Inject;


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
      eventBus.gotoList(clientContext.getPersonSearch().getName(),
                          clientContext.getPersonSearch().getCity());
    } else {
      eventBus.gotoSearch("", "");
    }
  }

  @Override
  public void doShowSearch() {
    eventBus.gotoSearch(clientContext.getPersonSearch().getName(),
                        clientContext.getPersonSearch().getCity());
  }
}
