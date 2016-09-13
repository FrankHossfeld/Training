package de.gishmo.training.gwt.client.model;

import com.google.inject.Provider;

public class ClientContextProvider
  implements Provider<ClientContext> {

  public static ClientContext clientContext = new ClientContext();

  @Override
  public ClientContext get() {
    return clientContext;
  }

  public void set(ClientContext clientContext) {
    ClientContextProvider.clientContext = clientContext;
  }
}
