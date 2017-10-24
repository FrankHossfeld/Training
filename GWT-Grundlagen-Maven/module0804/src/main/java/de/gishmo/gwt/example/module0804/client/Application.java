package de.gishmo.gwt.example.module0804.client;

import com.google.gwt.user.client.ui.RootLayoutPanel;
import de.gishmo.gwt.example.module0804.client.events.ShowSearch;
import de.gishmo.gwt.example.module0804.client.ui.detail.DetailPresenter;
import de.gishmo.gwt.example.module0804.client.ui.list.ListPresenter;
import de.gishmo.gwt.example.module0804.client.ui.navigation.NavigationPresenter;
import de.gishmo.gwt.example.module0804.client.ui.search.SearchPresenter;
import de.gishmo.gwt.example.module0804.client.ui.shell.ShellPresenter;

public class Application {

  private ShellPresenter shellPresenter;

  /* ClientContext */
  private ClientContext clientContext;

//------------------------------------------------------------------------------

  public Application() {
    super();
    
    /* create ClientContext */
    clientContext = new ClientContext();
    
    /* create Presenters */
    shellPresenter = new ShellPresenter(clientContext);

    new NavigationPresenter(clientContext);
    new DetailPresenter(clientContext);
    new ListPresenter(clientContext);
    new SearchPresenter(clientContext);
  }

//------------------------------------------------------------------------------

  public void run() {
    /* let's start! */
    RootLayoutPanel.get()
                   .add(shellPresenter.asWidget());
    /* show search-page by default */
    clientContext.getEventBus()
                 .fireEvent(new ShowSearch());
  }
}
