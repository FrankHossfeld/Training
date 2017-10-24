package de.gishmo.gwt.example.module0809.client.ui.list;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;
import de.gishmo.gwt.example.module0503.client.PersonServiceAsync;
import de.gishmo.gwt.example.module0503.shared.dto.Person;
import de.gishmo.gwt.example.module0503.shared.dto.PersonSearch;
import de.gishmo.gwt.example.module0809.client.Module0809EventBus;
import de.gishmo.gwt.example.module0809.client.resources.ApplicationConstants;
import de.gishmo.gwt.example.module0809.client.resources.ApplicationMessages;

import javax.inject.Inject;
import java.util.List;

@Presenter(view = IListView.class)
public class ListPresenter
  extends BasePresenter<IListView, Module0809EventBus>
  implements IListView.Presenter {
  
  @Inject
  private PersonServiceAsync service;

  public ListPresenter() {
    super();
  }
 
  @Override
  public void doUpdate(Person object) {
    // TODO Aufruf detail
  }

  public void onGotoList(String searchName,
                         String searchCity) {
    service.get(new PersonSearch(searchName,
                                 searchCity),
                new AsyncCallback<List<Person>>() {
      @Override
      public void onSuccess(List<Person> result) {
        view.resetTable();
        view.setData(result);
        eventBus.setContent(view.asWidget());
        if (result.size() == 0) {
          eventBus.setStatus(ApplicationConstants.CONSTANTS.statusListZero());
        } else if (result.size() == 1) {
          eventBus.setStatus(ApplicationConstants.CONSTANTS.statusListOne());
        } else {
          eventBus.setStatus(ApplicationMessages.MESSAGES.statusListMany(result.size()));
        }
      }
      
      @Override
      public void onFailure(Throwable caught) {
        Window.alert("PANIC!!!");
      }
    });
  }
}
