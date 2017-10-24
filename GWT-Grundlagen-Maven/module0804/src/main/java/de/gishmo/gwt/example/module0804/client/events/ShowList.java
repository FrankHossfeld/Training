package de.gishmo.gwt.example.module0804.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class ShowList
  extends GwtEvent<ShowList.ShowListHandler> {

  public static Type<ShowList.ShowListHandler> TYPE = new Type<ShowList.ShowListHandler>();

  public ShowList() {
    super();
  }

  @Override
  public Type<ShowList.ShowListHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(ShowList.ShowListHandler handler) {
    handler.onShowList(this);
  }


  public interface ShowListHandler
    extends EventHandler {

    void onShowList(ShowList event);

  }
}
