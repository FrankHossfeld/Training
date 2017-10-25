package de.gishmo.gwt.example.module0909.client.ui.list;

import java.util.List;

import javax.inject.Inject;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

import de.gishmo.gwt.example.module0503.client.PersonServiceAsync;
import de.gishmo.gwt.example.module0503.shared.dto.Person;
import de.gishmo.gwt.example.module0503.shared.dto.PersonSearch;
import de.gishmo.gwt.example.module0909.client.ClientContext;
import de.gishmo.gwt.example.module0909.client.Module0909EventBus;

@Presenter(view = IListView.class)
public class ListPresenter
  extends BasePresenter<IListView, Module0909EventBus>
  implements IListView.Presenter {

  @Inject
  private PersonServiceAsync service;

  @Inject
  private ClientContext clientContext;

  public ListPresenter() {
  }

  public void onGotoList(String searchName,
                         String searchCity) {
    service.get(new PersonSearch(searchName,
                                 searchCity),
                new AsyncCallback<List<Person>>() {
                  @Override
                  public void onFailure(Throwable caught) {
                    Window.alert("Panic");
                  }

                  @Override
                  public void onSuccess(List<Person> result) {
                    view.setData(result);
                  }
                });

    eventBus.setCenter(view.asWidget());
  }

  @Override
  public void doUpdate(Person object) {

  }
}
