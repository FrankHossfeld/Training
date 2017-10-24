package de.gishmo.gwt.example.module0801.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Module0801
  implements EntryPoint {

  /* ClientContext */
  private ClientContext clientContext;

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    clientContext = new ClientContext();

    clientContext.getShellPresenter()
                 .setNavigation(clientContext.getNavigationPresenter()
                                             .asWidget());

    RootLayoutPanel.get()
                   .add(clientContext.getShellPresenter()
                                     .asWidget());

    clientContext.getShellPresenter()
                 .setCenter(ClientContext.SEARCH_FORM);
  }
}
