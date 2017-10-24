package de.gishmo.gwt.example.module0804.client.ui.detail;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import de.gishmo.gwt.example.module0503.shared.dto.Person;
import de.gishmo.gwt.example.module0804.client.ClientContext;
import de.gishmo.gwt.example.module0804.client.events.SetCenter;
import de.gishmo.gwt.example.module0804.client.events.ShowList;
import de.gishmo.gwt.example.module0804.client.events.ShowPerson;

public class DetailPresenter
  implements IDetailView.Presenter {

  private ClientContext clientContext;
  private IDetailView   view;

//------------------------------------------------------------------------------

  public DetailPresenter(ClientContext clientContext) {
    this.clientContext = clientContext;

    view = new DetailView(this.clientContext.getStyle());
    view.setPresenter(this);

    bind();
  }

//------------------------------------------------------------------------------

  private void bind() {
    clientContext.getEventBus()
                 .addHandler(ShowPerson.TYPE,
                             new ShowPerson.ShowPersonHandler() {
                               @Override
                               public void onShowPerson(ShowPerson event) {
                                 getPerson(event.getId());
                                 clientContext.getEventBus()
                                              .fireEvent(new SetCenter(view.asWidget()));
                               }
                             });
  }

  private void getPerson(long id) {
    clientContext.getPersonService()
                 .get(id,
                      new AsyncCallback<Person>() {
                        @Override
                        public void onFailure(Throwable caught) {
                          Window.alert("PANIC!!!!");
                        }

                        @Override
                        public void onSuccess(Person result) {
                          view.setUpData(result);
                        }
                      });
  }

//------------------------------------------------------------------------------

  @Override
  public void doRevert() {
    clientContext.getEventBus()
                 .fireEvent(new ShowList());
  }

  @Override
  public void doUpdate(Person person) {
    clientContext.getPersonService()
                 .update(person,
                         new AsyncCallback<Void>() {
                           @Override
                           public void onFailure(Throwable caught) {
                             Window.alert("PANIC!!!!");
                           }

                           @Override
                           public void onSuccess(Void result) {
                             clientContext.getEventBus()
                                          .fireEvent(new ShowList());
                           }
                         });
  }
}