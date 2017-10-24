package de.gishmo.gwt.example.module0809.client;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import de.gishmo.gwt.example.module0809.client.model.ClientContext;
import de.gishmo.gwt.example.module0809.client.model.ClientContextProvider;

public class Module0809GinModule
  extends AbstractGinModule {
  
  @Override
  protected void configure() {
    bind(ClientContext.class).
      toProvider(ClientContextProvider.class).
      in(Singleton.class);   
  }
}
