package de.gishmo.module0812.submodule.gwt.client.ui.detail;

import javax.inject.Inject;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.history.NavigationConfirmationInterface;
import com.mvp4g.client.history.NavigationEventCommand;
import com.mvp4g.client.presenter.BasePresenter;

import de.gishmo.module0812.common.gwt.client.model.ClientContext;
import de.gishmo.module0812.domain.dto.shared.model.Person;
import de.gishmo.module0812.domain.dto.shared.transport.response.PersonGetResponse;
import de.gishmo.module0812.domain.dto.shared.transport.response.PersonUpdateResponse;
import de.gishmo.module0812.domain.service.client.Services;
import de.gishmo.module0812.domain.service.client.utils.ResponseHandler;
import de.gishmo.module0812.submodule.gwt.client.Module0812SubModuleEventBus;
import de.gishmo.module0812.submodule.gwt.client.resources.DetailConstants;

@Presenter(view = IDetailView.class)
public class DetailPresenter extends BasePresenter<IDetailView, Module0812SubModuleEventBus>
    implements IDetailView.Presenter, NavigationConfirmationInterface {

  @Inject
  private ClientContext clientContext;

  public DetailPresenter() {
  }

  @Override
  public void doRevert() {
    eventBus.gotoList(clientContext.getPersonSearch().getName(), clientContext.getPersonSearch().getCity());
  }

  @Override
  public void doUpdate(final Person person) {
    Services.get().getPersonService().update(person, new MethodCallback<PersonUpdateResponse>() {
      @Override
      public void onFailure(final Method method, final Throwable exception) {
        Window.alert("Fehler beim Speichern der geänderten Personendaten!");
      }

      @Override
      public void onSuccess(final Method method, PersonUpdateResponse response) {
        ResponseHandler.builder().callingClass(DetailPresenter.class)
        .callingMethod("doUpdate")
        .response(response)
        .responseMethod(method)
        .executeIfStatuscodeIsOk(new Command() {
          @Override
          public void execute() {
            if (clientContext.getPersonSearch() == null) {
              eventBus.gotoSearch("", "");
            } else {
              eventBus.gotoList(clientContext.getPersonSearch().getName(), clientContext.getPersonSearch().getCity());
            }
          }
        }).build().handle();
      }
    });
  }

  public void onGotoDetail(final long id) {
    eventBus.setNavigationConfirmation(this);
      Services.get().getPersonService().get(Long.toString(id), new MethodCallback<PersonGetResponse>() {
        @Override
        public void onFailure(final Method method, final Throwable exception) {
          Window.alert("PANIC!!!! Technischer Fehler auf Server");

        }

        @Override
        public void onSuccess(final Method method, final PersonGetResponse response) {
          ResponseHandler.builder().callingClass(DetailPresenter.class)
          .callingMethod("onGotoDetail")
          .response(response)
          .responseMethod(method)
          .executeIfStatuscodeIsOk(new Command() {
            @Override
            public void execute() {
              view.setUpData(response.getPerson());
              eventBus.setContent(view.asWidget());
              eventBus.setStatus(DetailConstants.CONSTANTS.statusDetail());
            }
          }).build().handle();
        }
      });
  }

  @Override
  public void confirm(final NavigationEventCommand event) {
    if (view.isDirty()) {
      if (Window.confirm("Wollen Sie wirklich Ihre Änderungen verwerfen?")) {
        event.fireEvent();
      }
    } else {
      event.fireEvent();
    }
  }
}