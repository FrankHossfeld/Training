package de.gishmo.gwt.example.module0910.client;

import com.mvp4g.client.event.EventFilter;

public class module0910EventFilter
  implements EventFilter<module0910EventBus> {

  public boolean filterEvent(String eventType,
                             Object[] params,
                             module0910EventBus eventBus) {
    eventBus.setFilteringEnabledForNextOne(false);
    //    eventBus.displayMessage( "Main Event " +
    //                             eventType +
    //                             " has been filtered." );

    return true;
  }
}
