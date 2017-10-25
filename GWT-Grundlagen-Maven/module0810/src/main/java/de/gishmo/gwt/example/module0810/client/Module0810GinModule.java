package de.gishmo.gwt.example.module0810.client;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import de.gishmo.gwt.example.module0810.client.model.ClientContext;
import de.gishmo.gwt.example.module0810.client.model.ClientContextProvider;

public class Module0810GinModule
  extends AbstractGinModule {
  
  @Override
  protected void configure() {
    bind(ClientContext.class).
      toProvider(ClientContextProvider.class).
      in(Singleton.class);   
  }
}
