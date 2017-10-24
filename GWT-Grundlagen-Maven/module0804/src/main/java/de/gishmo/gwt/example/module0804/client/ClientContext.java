package de.gishmo.gwt.example.module0804.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.resources.client.ClientBundle;
import de.gishmo.gwt.example.module0503.client.PersonService;
import de.gishmo.gwt.example.module0503.client.PersonServiceAsync;
import de.gishmo.gwt.example.module0804.client.resources.ApplicationCss;

public class ClientContext {

  private PersonServiceAsync service = GWT.create(PersonService.class);
  /* EventBus */
  private SimpleEventBus eventBus;
  /* application */
  private ApplicationCss style;

  public ClientContext() {
    /* create Resources */
    Resources resources = GWT.create(Resources.class);
    this.style = resources.style();
    this.style.ensureInjected();

    /* create eventBus */
    eventBus = new SimpleEventBus();
  }

//------------------------------------------------------------------------------

  public ApplicationCss getStyle() {
    return style;
  }

//------------------------------------------------------------------------------

  public SimpleEventBus getEventBus() {
    return eventBus;
  }

  public PersonServiceAsync getPersonService() {
    return service;
  }

  public interface Resources
    extends ClientBundle {

    @Source("resources/ApplicationCss.css")
    ApplicationCss style();

  }
}
