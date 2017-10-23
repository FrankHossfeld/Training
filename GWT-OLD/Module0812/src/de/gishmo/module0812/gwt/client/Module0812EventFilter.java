package de.gishmo.module0812.gwt.client;

import com.mvp4g.client.event.EventFilter;

public class Module0812EventFilter
  implements EventFilter<Module0812EventBus> {

  public boolean filterEvent(String eventType, 
                             Object[] params, 
                             Module0812EventBus eventBus)  {
    eventBus.setFilteringEnabledForNextOne(false);
//    eventBus.displayMessage( "Main Event " + 
//                             eventType + 
//                             " has been filtered." );
    
    return true;
  }
}
