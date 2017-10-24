package de.gishmo.gwt.example.module0809.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class SetStatus
  extends GwtEvent<SetStatus.SetStatusHandler> {

  public static Type<SetStatus.SetStatusHandler> TYPE = new Type<SetStatus.SetStatusHandler>();

  private String status;
  
  public SetStatus(String status) {
    super();
    
    this.status = status;
  }

  public String getStatus() {
    return status;
  }

  @Override
  public Type<SetStatus.SetStatusHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(SetStatus.SetStatusHandler handler) {
    handler.onSetStatus(this);
  }

  
  public interface SetStatusHandler
    extends EventHandler {
    
    void onSetStatus(SetStatus event);
  
  }
}
