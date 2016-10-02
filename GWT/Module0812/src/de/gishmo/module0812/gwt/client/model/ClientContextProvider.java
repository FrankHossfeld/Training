package de.gishmo.module0812.gwt.client.model;

import com.google.inject.Provider;


public class ClientContextProvider implements Provider<ClientContext> {
  
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
