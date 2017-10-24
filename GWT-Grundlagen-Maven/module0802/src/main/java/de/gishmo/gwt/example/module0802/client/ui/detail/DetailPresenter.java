package de.gishmo.gwt.example.module0802.client.ui.detail;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import de.gishmo.gwt.example.module0503.shared.dto.Person;
import de.gishmo.gwt.example.module0802.client.ClientContext;


public class DetailPresenter
  implements IDetailView.Presenter,
             IsWidget{

  private ClientContext clientContext;
  private IDetailView view;
   
  private Person person;
 
//------------------------------------------------------------------------------

  public DetailPresenter(ClientContext clientContext) {
    this.clientContext = clientContext;
    
    view = new DetailView(this.clientContext.getStyle());
    view.setPresenter(this);
    
  }

//------------------------------------------------------------------------------

  @Override
  public Widget asWidget() {
    return view.asWidget();
  }

  @Override
  public void doRevert() {
    clientContext.getShellPresenter().setCenter(ClientContext.RESULT_LIST);
  }

  @Override
  public void doUpdate() {
    this.person = view.flush(this.person);
    
    clientContext.getPersonService().update(person, 
                   new AsyncCallback<Void>() {
                    @Override
                    public void onFailure(Throwable caught) {
                      Window.alert("PANIC!!!!");
                    }
                    @Override
                    public void onSuccess(Void result) {
                      clientContext.getShellPresenter().setCenter(ClientContext.RESULT_LIST);
                    }
                  });
  }

//------------------------------------------------------------------------------

  public void setPersonId(long id) {
    clientContext.getPersonService().get(id, 
                                         new AsyncCallback<Person>() {
                                          @Override
                                          public void onFailure(Throwable caught) {
                                            Window.alert("PANIC!!!!");
                                          }
                                          @Override
                                          public void onSuccess(Person result) {
                                            person = result;
                                            view.edit(result);
                                          }
    });
  }
}