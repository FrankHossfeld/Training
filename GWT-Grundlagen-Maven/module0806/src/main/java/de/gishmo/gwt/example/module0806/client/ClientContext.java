package de.gishmo.gwt.example.module0806.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.resources.client.ClientBundle;
import com.google.web.bindery.event.shared.EventBus;
import de.gishmo.gwt.example.module0503.client.PersonService;
import de.gishmo.gwt.example.module0503.client.PersonServiceAsync;
import de.gishmo.gwt.example.module0503.shared.dto.PersonSearch;
import de.gishmo.gwt.example.module0806.client.resources.ApplicationCss;

public class ClientContext {

  private PersonServiceAsync service = GWT.create(PersonService.class);
  
  public interface Resources
    extends ClientBundle {
    
    @Source("resources/ApplicationCss.css")
    ApplicationCss style();
    
  }
  
  /* EventBus */
  private EventBus        eventBus;
  /* place controller */
  private PlaceController placeController;
  /* application */
  private ApplicationCss  style;
  /* searchs */
  private PersonSearch    personSearch;
  
//------------------------------------------------------------------------------
  
  public ClientContext(EventBus eventBus,
                       PlaceController placeController) {
    /* create Resources */
    Resources resources = GWT.create(Resources.class);
    this.style = resources.style();
    this.style.ensureInjected();

    this.eventBus = eventBus;
    this.placeController = placeController;
  }

//------------------------------------------------------------------------------

  public ApplicationCss getStyle() {
    return style;
  }

  public EventBus getEventBus() {
    return eventBus;
  }

  public PlaceController getPlaceController() {
    return placeController;
  }

  public PersonServiceAsync getPersonService() {
    return service;
  }

  public PersonSearch getPersonSearch() {
    return personSearch;
  }

  public void setPersonSearch(PersonSearch personSearch) {
    this.personSearch = personSearch;
  }
}
