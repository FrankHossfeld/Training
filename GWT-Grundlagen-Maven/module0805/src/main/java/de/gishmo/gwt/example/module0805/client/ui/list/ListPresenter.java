package de.gishmo.gwt.example.module0805.client.ui.list;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import de.gishmo.gwt.example.module0503.shared.dto.Person;
import de.gishmo.gwt.example.module0503.shared.dto.PersonSearch;
import de.gishmo.gwt.example.module0805.client.ClientContext;
import de.gishmo.gwt.example.module0805.client.events.SearchPersons;
import de.gishmo.gwt.example.module0805.client.events.SetStatus;
import de.gishmo.gwt.example.module0805.client.resources.ApplicationConstants;
import de.gishmo.gwt.example.module0805.client.resources.ApplicationMessages;
import de.gishmo.gwt.example.module0805.client.widgets.PresentsWidgets;

import java.util.List;

// TODO Detail
public class ListPresenter
  implements IListView.Presenter,
             PresentsWidgets {

  private ClientContext clientContext;
  private IListView     view;

  private ListPlace place;

//------------------------------------------------------------------------------

  public ListPresenter(ClientContext clientContext,
                       ListPlace place) {
    this.clientContext = clientContext;
    this.place = place;

    view = new ListView(this.clientContext.getStyle());
    view.setPresenter(this);

    bind();
  }

//------------------------------------------------------------------------------

  private void bind() {
  }

  @Override
  public Widget asWidget() {
    return view.asWidget();
  }

  @Override
  public String mayStop() {
    return null; // always happy to stop
  }

  @Override
  public void start() {
    clientContext.getEventBus()
                 .fireEvent(new SearchPersons(new PersonSearch(place.getSearchName(),
                                                               place.getSearchCity())));
    clientContext.setPersonSearch(new PersonSearch(place.getSearchName(),
                                                   place.getSearchCity()));

    searchPersons();
  }

//------------------------------------------------------------------------------

  @Override
  public void stop() {
  }

//------------------------------------------------------------------------------

  private void searchPersons() {
    if (place != null) {
      if ((place.getSearchName() != null && place.getSearchName()
                                                 .length() > 0
          ) ||
          (place.getSearchCity() != null && place.getSearchCity()
                                                 .length() > 0
          )) {

        clientContext.getPersonService()
                     .get(new PersonSearch(place.getSearchName(),
                                           place.getSearchCity()),
                          new AsyncCallback<List<Person>>() {
                            @Override
                            public void onFailure(Throwable caught) {
                              Window.alert("PANIC!!!");
                            }

                            @Override
                            public void onSuccess(List<Person> result) {
                              view.resetTable();
                              view.setData(result);
                              if (result.size() == 0) {
                                clientContext.getEventBus()
                                             .fireEvent(new SetStatus(ApplicationConstants.CONSTANTS.statusListZero()));
                              } else if (result.size() == 1) {
                                clientContext.getEventBus()
                                             .fireEvent(new SetStatus(ApplicationConstants.CONSTANTS.statusListOne()));
                              } else {
                                clientContext.getEventBus()
                                             .fireEvent(new SetStatus(ApplicationMessages.MESSAGES.statusListMany(result.size())));
                              }
                            }
                          });
      } else {
        view.resetTable();
      }
    } else {
      view.resetTable();
    }
  }

  @Override
  public void doUpdate(Person object) {
    // TODO ausgewählte Person anzeigen
  }
}
