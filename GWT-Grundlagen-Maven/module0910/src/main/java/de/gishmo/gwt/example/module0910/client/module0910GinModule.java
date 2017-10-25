package de.gishmo.gwt.example.module0910.client;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import de.gishmo.gwt.example.module0910.client.model.ClientContext;
import de.gishmo.gwt.example.module0910.client.model.ClientContextProvider;

public class module0910GinModule
  extends AbstractGinModule {

  @Override
  protected void configure() {
    bind(ClientContext.class).
                               toProvider(ClientContextProvider.class)
                             .
                               in(Singleton.class);
  }
}
