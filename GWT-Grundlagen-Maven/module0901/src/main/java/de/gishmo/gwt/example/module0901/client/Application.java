package de.gishmo.gwt.example.module0901.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.event.shared.UmbrellaException;

import de.gishmo.gwt.example.module0901.client.activities.ApplicationActivityMapper;
import de.gishmo.gwt.example.module0901.client.activities.ApplicationPlaceHistoryMapper;
import de.gishmo.gwt.example.module0901.client.ui.navigation.NavigationPresenter;
import de.gishmo.gwt.example.module0901.client.ui.search.SearchPlace;
import de.gishmo.gwt.example.module0901.client.ui.shell.ShellPresenter;

public class Application {

  /* logger */
  private static final Logger log = Logger.getLogger(Application.class.getName());

  private ClientContext clientContext;

  private ShellPresenter shell;

  private PlaceController placeController;
  //
  //  /**
  //   * The top of our UI.
  //   */
  //  private final Module090304AppShell shell;

  private ActivityManager activityManager;

  private ApplicationPlaceHistoryMapper historyMapper;

  private PlaceHistoryHandler historyHandler;

  //------------------------------------------------------------------------------

  public Application() {
    EventBus eventBus = new SimpleEventBus();
    placeController = new PlaceController(eventBus);
    
    /* create client context */
    clientContext = new ClientContext(eventBus,
                                      placeController);

    activityManager = new ActivityManager(new ApplicationActivityMapper(clientContext),
                                          clientContext.getEventBus());

    historyMapper = GWT.create(ApplicationPlaceHistoryMapper.class);
    historyHandler = new PlaceHistoryHandler(historyMapper);

    /* presenters */
    shell = new ShellPresenter(clientContext);
    new NavigationPresenter(clientContext);
  }

  //------------------------------------------------------------------------------

  public void run(HasWidgets.ForIsWidget parentView) {
    activityManager.setDisplay(shell);

    parentView.add(shell);

    GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
      @Override
      public void onUncaughtException(Throwable e) {
        while (e instanceof UmbrellaException) {
          e = ((UmbrellaException) e).getCauses()
                                     .iterator()
                                     .next();
        }

        String message = e.getMessage();
        if (message == null) {
          message = e.toString();
        }
        log.log(Level.SEVERE,
                "Uncaught exception",
                e);
        Window.alert("An unexpected error occurred: " + message);
      }
    });

    initBrowserHistory(new SearchPlace("",
                                       ""));
  }

  /**
   * Initialize browser history / bookmarking.
   */
  private void initBrowserHistory(SearchPlace defaultPlace) {

    historyHandler.register(placeController,
                            clientContext.getEventBus(),
                            defaultPlace);

    /*
     * Go to the place represented in the URL. This is what makes bookmarks
     * work.
     */
    historyHandler.handleCurrentHistory();
  }
}
