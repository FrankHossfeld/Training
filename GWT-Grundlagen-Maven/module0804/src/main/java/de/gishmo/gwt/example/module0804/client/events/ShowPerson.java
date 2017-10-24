package de.gishmo.gwt.example.module0804.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class ShowPerson
  extends GwtEvent<ShowPerson.ShowPersonHandler> {

  public static Type<ShowPerson.ShowPersonHandler> TYPE = new Type<ShowPerson.ShowPersonHandler>();

  private long id;

  public ShowPerson(long id) {
    super();
    this.id = id;
  }

  public long getId() {
    return id;
  }

  @Override
  public Type<ShowPerson.ShowPersonHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(ShowPerson.ShowPersonHandler handler) {
    handler.onShowPerson(this);
  }


  public interface ShowPersonHandler
    extends EventHandler {

    void onShowPerson(ShowPerson event);

  }
}
