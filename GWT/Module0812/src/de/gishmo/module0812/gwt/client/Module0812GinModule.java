package de.gishmo.module0812.gwt.client;

import com.google.gwt.inject.client.AbstractGinModule;

import de.gishmo.module0812.common.gwt.client.model.ClientContext;
import de.gishmo.module0812.common.gwt.client.model.ClientContextProvider;

public class Module0812GinModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(ClientContext.class).
      toProvider(ClientContextProvider.class);
   }
}
