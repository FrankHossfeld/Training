package de.gishmo.module0812.gwt.client.ui.list;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

import de.gishmo.module0812.domain.dto.shared.model.Person;
import de.gishmo.module0812.domain.dto.shared.search.PersonSearch;
import de.gishmo.module0812.domain.dto.shared.transport.response.PersonSearchResponse;
import de.gishmo.module0812.domain.service.client.Services;
import de.gishmo.module0812.domain.service.client.utils.ResponseHandler;
import de.gishmo.module0812.gwt.client.Module0812EventBus;
import de.gishmo.module0812.gwt.client.resource.ApplicationConstants;
import de.gishmo.module0812.gwt.client.resource.ApplicationMessages;

@Presenter(view = IListView.class)
public class ListPresenter extends BasePresenter<IListView, Module0812EventBus> implements IListView.Presenter {

  public ListPresenter() {
    super();
  }

  @Override
  public void doUpdate(final Person object) {
    eventBus.gotoDetail(object.getId());
  }

  public void onGotoList(final String searchName, final String searchCity) {
    Services.get().getPersonService().search(new PersonSearch(searchName, searchCity),
                                             new MethodCallback<PersonSearchResponse>() {
                                               @Override
                                               public void onFailure(final Method method, final Throwable exception) {
                                                 Window.alert("PANIC!!! Technischer Fehler auf Server " + exception
                                                     .toString());
                                               }
                                               @Override
                                               public void onSuccess(final Method method,
                                                                     final PersonSearchResponse response) {
                                                 ResponseHandler.builder().callingClass(ListPresenter.class)
                                                     .callingMethod("onGotoList")
                                                     .responseMethod(method)
                                                     .response(response)
                                                     .executeIfStatuscodeIsOk(new Command() {
                                                       @Override
                                                       public void execute() {
                                                         view.resetTable();
                                                         view.setData(response.getPersonList());
                                                         eventBus.setContent(view.asWidget());
                                                         final int anzahlGefundene = response.getPersonList().size();
                                                         if (anzahlGefundene == 0) {
                                                           eventBus.setStatus(ApplicationConstants.CONSTANTS
                                                               .statusListZero());
                                                         } else if (anzahlGefundene == 1) {
                                                           eventBus.setStatus(ApplicationConstants.CONSTANTS
                                                               .statusListOne());
                                                         } else {
                                                           eventBus.setStatus(ApplicationMessages.MESSAGES
                                                               .statusListMany(anzahlGefundene));
                                                         }
                                                       }
                                                     }).build().handle();
                                               }
                                             });
  }
}
