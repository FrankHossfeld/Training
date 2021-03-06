package de.gishmo.gwt.example.module0909.client;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import de.gishmo.gwt.example.module0909.client.model.ClientContext;
import de.gishmo.gwt.example.module0909.client.model.ClientContextProvider;

public class Module0909GinModule
  extends AbstractGinModule {

  @Override
  protected void configure() {
    bind(ClientContext.class).
                               toProvider(ClientContextProvider.class)
                             .
                               in(Singleton.class);
  }
}
