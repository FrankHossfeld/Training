package de.gishmo.gwt.example.module0909.client;

import com.mvp4g.client.event.EventFilter;

public class Module0909EventFilter
  implements EventFilter<Module0909EventBus> {

  public boolean filterEvent(String eventType,
                             Object[] params,
                             Module0909EventBus eventBus) {
    eventBus.setFilteringEnabledForNextOne(false);
    //    eventBus.displayMessage( "Main Event " +
    //                             eventType +
    //                             " has been filtered." );

    return true;
  }
}
