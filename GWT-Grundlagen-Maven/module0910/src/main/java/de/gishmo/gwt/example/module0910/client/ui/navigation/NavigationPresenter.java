package de.gishmo.gwt.example.module0910.client.ui.navigation;

import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;
import de.gishmo.gwt.example.module0910.client.model.ClientContext;
import de.gishmo.gwt.example.module0910.client.module0910EventBus;

import javax.inject.Inject;

@Presenter(view = INavigationView.class)
public class NavigationPresenter
  extends BasePresenter<INavigationView, module0910EventBus>
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
