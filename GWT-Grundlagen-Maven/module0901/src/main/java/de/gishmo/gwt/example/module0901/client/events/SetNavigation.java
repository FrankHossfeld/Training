package de.gishmo.gwt.example.module0901.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Widget;

public class SetNavigation
  extends GwtEvent<SetNavigation.SetNavigationHandler> {

  public static Type<SetNavigation.SetNavigationHandler> TYPE = new Type<SetNavigation.SetNavigationHandler>();

  private Widget widget;
  
  public SetNavigation(Widget widget) {
    super();
    
    this.widget = widget;
  }

  public Widget getWidget() {
    return widget;
  }

  @Override
  public Type<SetNavigation.SetNavigationHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(SetNavigation.SetNavigationHandler handler) {
    handler.onSetNavigation(this);
  }

  
  public interface SetNavigationHandler
    extends EventHandler {
    
    void onSetNavigation(SetNavigation event);
  
  }
}
