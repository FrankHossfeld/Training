package de.gishmo.gwt.example.module1002.client.ui.detail;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import de.gishmo.gwt.example.module0503.shared.dto.Person;
import de.gishmo.gwt.example.module0708.client.widgets.PresentsWidgets;
import de.gishmo.gwt.example.module1002.client.ClientContext;
import de.gishmo.gwt.example.module1002.client.events.SetStatus;
import de.gishmo.gwt.example.module1002.client.resources.ApplicationConstants;
import de.gishmo.gwt.example.module1002.client.ui.list.ListPlace;
import de.gishmo.gwt.example.module1002.client.ui.search.SearchPlace;

public class DetailPresenter
  implements IDetailView.Presenter,
             PresentsWidgets {

  private ClientContext clientContext;
  private IDetailView view;

  private DetailPlace place;
    
//------------------------------------------------------------------------------

  public DetailPresenter(ClientContext clientContext,
                         DetailPlace place) {
    this.clientContext = clientContext;
    this.place = place;

    view = new DetailView(this.clientContext.getStyle());
    view.setPresenter(this);
    
    bind();
  }

//------------------------------------------------------------------------------

  @Override
  public Widget asWidget() {
    return view.asWidget();
  }

  @Override
  public String mayStop() {
    if (view.isDirty()) {
      return ApplicationConstants.CONSTANTS.detailMessage(); 
    }
    return null; // happy to stop
  }

  @Override
  public void start() {
    if (place.getId() == 0) {
      createListPlace();
      return;
    }
    getPerson(place.getId());
  }

  @Override
  public void stop() {
  }

//------------------------------------------------------------------------------

  @Override
  public void doRevert() {
    clientContext.getPlaceController().goTo(createListPlace());
  }

  @Override
  public void doUpdate(Person person) {
    clientContext.getPersonService().update(person, 
                   new AsyncCallback<Void>() {
                    @Override
                    public void onFailure(Throwable caught) {
                      Window.alert("PANIC!!!!");
                    }
                    @Override
                    public void onSuccess(Void result) {
                      clientContext.getPlaceController().goTo(createListPlace());
                    }
                  });
  }

//------------------------------------------------------------------------------

  private void bind() {
  }
  
  private Place createListPlace() {
    if (clientContext.getPersonSearch() == null) {
      return new SearchPlace("", "");
    } else {
      return new ListPlace(clientContext.getPersonSearch().getName(), clientContext.getPersonSearch().getCity());
    }
  }
  
  private void getPerson(long id) {
    clientContext.getPersonService().get(id, 
                                         new AsyncCallback<Person>() {
                                          @Override
                                          public void onFailure(Throwable caught) {
                                            Window.alert("PANIC!!!!");
                                          }
                                          @Override
                                          public void onSuccess(Person result) {
                                            view.setUpData(result);
                                            clientContext.getEventBus().fireEvent(new SetStatus(ApplicationConstants.CONSTANTS.statusDetail()));
                                          }
    });
  }
}