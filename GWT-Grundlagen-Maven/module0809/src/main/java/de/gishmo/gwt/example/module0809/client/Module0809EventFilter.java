package de.gishmo.gwt.example.module0809.client;

import com.mvp4g.client.event.EventFilter;

public class Module0809EventFilter
  implements EventFilter<Module0809EventBus> {

  public boolean filterEvent(String eventType, 
                             Object[] params, 
                             Module0809EventBus eventBus)  {
    eventBus.setFilteringEnabledForNextOne(false);
//    eventBus.displayMessage( "Main Event " + 
//                             eventType + 
//                             " has been filtered." );
    
    return true;
  }
}
