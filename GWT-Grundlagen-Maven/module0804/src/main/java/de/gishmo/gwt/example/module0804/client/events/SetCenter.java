package de.gishmo.gwt.example.module0804.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Widget;

public class SetCenter
  extends GwtEvent<SetCenter.SetCenterHandler> {

  public static Type<SetCenter.SetCenterHandler> TYPE = new Type<SetCenter.SetCenterHandler>();

  private Widget widget;

  public SetCenter(Widget widget) {
    super();

    this.widget = widget;
  }

  public Widget getWidget() {
    return widget;
  }

  @Override
  public Type<SetCenter.SetCenterHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(SetCenter.SetCenterHandler handler) {
    handler.onSetCenter(this);
  }


  public interface SetCenterHandler
    extends EventHandler {

    void onSetCenter(SetCenter event);

  }
}
