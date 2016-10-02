package de.gishmo.module0812.gwt.client;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import de.gishmo.module0812.gwt.client.model.ClientContext;
import de.gishmo.module0812.gwt.client.model.ClientContextProvider;

public class Module0810GinModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(ClientContext.class).
      toProvider(ClientContextProvider.class).
      in(Singleton.class);
   }
}
