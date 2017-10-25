package de.gishmo.gwt.example.module0810.client.ui.detail;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.history.NavigationConfirmationInterface;
import com.mvp4g.client.history.NavigationEventCommand;
import com.mvp4g.client.presenter.BasePresenter;
import de.gishmo.gwt.example.module0503.client.PersonServiceAsync;
import de.gishmo.gwt.example.module0503.shared.dto.Person;
import de.gishmo.gwt.example.module0810.client.Module0810EventBus;
import de.gishmo.gwt.example.module0810.client.model.ClientContext;
import de.gishmo.gwt.example.module0810.client.resources.ApplicationConstants;

import javax.inject.Inject;


@Presenter(view = IDetailView.class)
public class DetailPresenter
  extends BasePresenter<IDetailView, Module0810EventBus>
  implements IDetailView.Presenter,
             NavigationConfirmationInterface {

  @Inject
  private ClientContext clientContext;
  
  @Inject
  private PersonServiceAsync service;

  public DetailPresenter() {
  }

  @Override
  public void doRevert() {
    eventBus.gotoList(clientContext.getPersonSearch().getName(),
                      clientContext.getPersonSearch().getCity());
  }

  @Override
  public void doUpdate(Person person) {
    service.update(person, 
                   new AsyncCallback<Void>() {
                    @Override
                    public void onFailure(Throwable caught) {
                      Window.alert("PANIC!!!!");
                    }
                    @Override
                    public void onSuccess(Void result) {
                      if (clientContext.getPersonSearch() == null) {
                        eventBus.gotoSearch("", "");
                      } else {
                        eventBus.gotoList(clientContext.getPersonSearch().getName(),
                                          clientContext.getPersonSearch().getCity());
                      }
                    }
                  });
  }
  
  public void onGotoDetail(long id) {
    eventBus.setNavigationConfirmation(this);
    service.get(id, 
                new AsyncCallback<Person>() {
                  @Override
                  public void onFailure(Throwable caught) {
                    Window.alert("PANIC!!!!");
                  }
                  @Override
                  public void onSuccess(Person result) {
                    view.setUpData(result);
                    eventBus.setContent(view.asWidget());
                    eventBus.setStatus(ApplicationConstants.CONSTANTS.statusDetail());
                  }
    });
  }

  @Override
  public void confirm(NavigationEventCommand event) {
    if (view.isDirty()) {
      if (Window.confirm("Wollen Sie wirklich Ihre Aendeurngen verwerfen?")) {
        event.fireEvent();
      }
    } else {
      event.fireEvent();
    }
  }
}