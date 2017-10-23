package de.gishmo.module0812.submodule.gwt.client;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import de.gishmo.module0812.common.gwt.client.model.ClientContext;
import de.gishmo.module0812.common.gwt.client.model.ClientContextProvider;

public class Module0812SubGinModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(ClientContext.class).
      toProvider(ClientContextProvider.class).
      in(Singleton.class);
   }
}
