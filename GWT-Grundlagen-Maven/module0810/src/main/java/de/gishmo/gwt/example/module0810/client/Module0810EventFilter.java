package de.gishmo.gwt.example.module0810.client;

import com.mvp4g.client.event.EventFilter;

public class Module0810EventFilter
  implements EventFilter<Module0810EventBus> {

  public boolean filterEvent(String eventType, 
                             Object[] params, 
                             Module0810EventBus eventBus)  {
    eventBus.setFilteringEnabledForNextOne(false);
//    eventBus.displayMessage( "Main Event " + 
//                             eventType + 
//                             " has been filtered." );
    
    return true;
  }
}
