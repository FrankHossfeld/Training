package de.gishmo.gwt.example.module0802.client.ui.list;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import de.gishmo.gwt.example.module0503.shared.dto.Person;
import de.gishmo.gwt.example.module0503.shared.dto.PersonSearch;
import de.gishmo.gwt.example.module0802.client.ClientContext;

import java.util.List;

public class ListPresenter
  implements IListView.Presenter,
             IsWidget{

  private ClientContext clientContext;
  private IListView view;
  
//------------------------------------------------------------------------------

  public ListPresenter(ClientContext clientContext) {
    this.clientContext = clientContext;
    
    view = new ListView(this.clientContext.getStyle());
    view.setPresenter(this);
    
  }

//------------------------------------------------------------------------------

  @Override
  public Widget asWidget() {
    PersonSearch search = clientContext.getPersonSearch();
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
    
    
    
    return view.asWidget();
  }

  @Override
  public void doUpdate(Person object) {
    clientContext.getShellPresenter().setCenter(ClientContext.DETAIL_FORM);
    clientContext.getDetailPresenter().setPersonId(object.getId());
  }
}
