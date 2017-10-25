package de.gishmo.gwt.example.module0909.client;

import javax.inject.Provider;

public class ClientContextProvider
  implements Provider<ClientContext> {

  public static ClientContext clientContext = new ClientContext();

  //- Getter- und Setter --------------------------------------------------------

  @Override
  public ClientContext get() {
    return clientContext;
  }

  public void set(ClientContext clientContext) {
    ClientContextProvider.clientContext = clientContext;
  }
}
