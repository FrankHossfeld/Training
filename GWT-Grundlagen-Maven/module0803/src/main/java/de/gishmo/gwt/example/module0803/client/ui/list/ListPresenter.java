package de.gishmo.gwt.example.module0803.client.ui.list;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import de.gishmo.gwt.example.module0503.shared.dto.Person;
import de.gishmo.gwt.example.module0503.shared.dto.PersonSearch;
import de.gishmo.gwt.example.module0803.client.ClientContext;
import de.gishmo.gwt.example.module0803.client.events.SearchPersons;
import de.gishmo.gwt.example.module0803.client.events.SetCenter;
import de.gishmo.gwt.example.module0803.client.events.ShowList;

import java.util.List;

public class ListPresenter
  implements IListView.Presenter {

  private ClientContext clientContext;
  private IListView     view;
  
  private PersonSearch search;
  
//------------------------------------------------------------------------------

  public ListPresenter(ClientContext clientContext) {
    this.clientContext = clientContext;
    
    view = new ListView(this.clientContext.getStyle());
    view.setPresenter(this);
    
    bind();
  }

//------------------------------------------------------------------------------
 
  @Override
  public void doUpdate(Person object) {
    // TODO Fire Event showPerson(id) to show the DetailForm
  }

//------------------------------------------------------------------------------

  private void bind() {
    clientContext.getEventBus().addHandler(ShowList.TYPE,
                                           event -> {
                                             searchPersons();
                                             clientContext.getEventBus().fireEvent(new SetCenter(view.asWidget()));
                                           });
    
    clientContext.getEventBus().addHandler(SearchPersons.TYPE,
                                           event -> {
                                             search = new PersonSearch(event.getSearchName(), event.getSearchCity());
                                             searchPersons();
                                             clientContext.getEventBus().fireEvent(new SetCenter(view.asWidget()));
                                           });
  }
  
  private void searchPersons() {
    if (search != null) {
      if ((search.getName() != null && search.getName().length() > 0) ||
          (search.getCity() != null && search.getCity().length() > 0)) {
  
        clientContext.getPersonService().get(search,
                                             new AsyncCallback<List<Person>>() {
          @Override
          public void onSuccess(List<Person> result) {
            view.resetTable();
            view.setData(result);
          }
          @Override
          public void onFailure(Throwable caught) {
            Window.alert("PANIC!!!");
          }
        });
      } else {
        view.resetTable();
      }
    } else {
      view.resetTable();
    }
  }
}
