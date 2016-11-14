package de.gishmo.module0812.gwt.client.ui.navigation;

import javax.inject.Inject;

import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

import de.gishmo.module0812.common.gwt.client.model.ClientContext;
import de.gishmo.module0812.gwt.client.Module0812EventBus;

@Presenter(view = INavigationView.class)
public class NavigationPresenter extends BasePresenter<INavigationView, Module0812EventBus>
implements INavigationView.Presenter {

   @Inject
   private ClientContext clientContext;

   public NavigationPresenter() {}

   @Override
   public void bind() {
      eventBus.setNavigation(view.asWidget());
   }

   @Override
   public void doShowList() {
      if (clientContext.getPersonSearch() != null) {
         eventBus.gotoList(clientContext.getPersonSearch().getName(),
                           clientContext.getPersonSearch().getCity());
      }
      else {
         eventBus.gotoSearch("", "");
      }
   }

   @Override
   public void doShowSearch() {
      eventBus.gotoSearch(clientContext.getPersonSearch().getName(),
                          clientContext.getPersonSearch().getCity());
   }
}
