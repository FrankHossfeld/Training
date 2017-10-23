package de.gishmo.module0812.submodule.gwt.client;

import com.mvp4g.client.event.EventFilter;

public class Module0812SubModuleEventFilter
  implements EventFilter<Module0812SubModuleEventBus> {

  public boolean filterEvent(String eventType, 
                             Object[] params, 
                             Module0812SubModuleEventBus eventBus)  {
    eventBus.setFilteringEnabledForNextOne(false);
//    eventBus.displayMessage( "Main Event " + 
//                             eventType + 
//                             " has been filtered." );
    
    return true;
  }
}
