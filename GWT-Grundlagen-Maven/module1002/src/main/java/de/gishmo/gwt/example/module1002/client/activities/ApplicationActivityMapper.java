package de.gishmo.gwt.example.module1002.client.activities;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import de.gishmo.gwt.example.module1002.client.ClientContext;
import de.gishmo.gwt.example.module1002.client.ui.detail.DetailPlace;
import de.gishmo.gwt.example.module1002.client.ui.detail.DetailPresenter;
import de.gishmo.gwt.example.module1002.client.ui.list.ListPlace;
import de.gishmo.gwt.example.module1002.client.ui.list.ListPresenter;
import de.gishmo.gwt.example.module1002.client.ui.search.SearchPlace;
import de.gishmo.gwt.example.module1002.client.ui.search.SearchPresenter;

public class ApplicationActivityMapper
  implements ActivityMapper {
  

  private static Logger logger = Logger.getLogger(ApplicationActivityMapper.class.getName());
  
  private ClientContext clientContext;

//------------------------------------------------------------------------------

  public ApplicationActivityMapper(ClientContext clientContext) {
    super();
    this.clientContext = clientContext;
  }

//------------------------------------------------------------------------------

  @Override
  public Activity getActivity(Place place) {
    if (place instanceof DetailPlace) {
      this.logGotoPlace(DetailPlace.class.getName());
      // The list of tasks.
      return this.createDetailActivity((DetailPlace) place);
    }
    
    if (place instanceof ListPlace) {
      this.logGotoPlace(ListPlace.class.getName());
      // The list of tasks.
      return this.createListActivity((ListPlace) place);
    }
    
    if (place instanceof SearchPlace) {
      this.logGotoPlace(SearchPlace.class.getName());
      // The list of tasks.
      return this.createSearchActivity((SearchPlace) place);
    }
    
    return null;
  }

//------------------------------------------------------------------------------
  
  private AbstractActivity createDetailActivity(final DetailPlace place) {
    // The list of tasks.
    return new AbstractActivity() {

      private DetailPresenter presenter;

      @Override
      public void start(AcceptsOneWidget panel, EventBus eventBus) {
        logStart("DetailActivity");
        presenter = new DetailPresenter(clientContext, place);
        presenter.start();
        panel.setWidget(presenter);
      }

      @Override
      public String mayStop() {
        logMayStop("CustomerSearchActivity");
        return presenter.mayStop();
      }

      @Override
      public void onStop() {
        logStop("CustomerSearchActivity");
        presenter.stop();
      }
   };
  }

  private AbstractActivity createListActivity(final ListPlace place) {
    // The list of tasks.
    return new AbstractActivity() {

      private ListPresenter presenter;

      @Override
      public void start(AcceptsOneWidget panel, EventBus eventBus) {
        logStart("ListActivity");
        presenter = new ListPresenter(clientContext, place);
        presenter.start();
        panel.setWidget(presenter);
      }

      @Override
      public String mayStop() {
        logMayStop("CustomerSearchActivity");
        return presenter.mayStop();
      }

      @Override
      public void onStop() {
        logStop("CustomerSearchActivity");
        presenter.stop();
      }
   };
  }

  private AbstractActivity createSearchActivity(final SearchPlace place) {
    // The list of tasks.
    return new AbstractActivity() {

      private SearchPresenter presenter;

      @Override
      public void start(AcceptsOneWidget panel, EventBus eventBus) {
        logStart("SearchActivity");
        presenter = new SearchPresenter(clientContext, place);
        presenter.start();
        panel.setWidget(presenter);
      }

      @Override
      public String mayStop() {
        logMayStop("CustomerSearchActivity");
        return presenter.mayStop();
      }

      @Override
      public void onStop() {
        logStop("CustomerSearchActivity");
        presenter.stop();
      }
   };
  }

//------------------------------------------------------------------------------

  private void logGotoPlace(String place) {
    this.logInfo("AppActivityMapper: goto place -> " + place);
  }

  private void logMayStop(String activityName) {
    this.logInfo("AppActivityMapper: may stop activity -> " + activityName);
  }

  private void logStart(String activityName) {
    this.logInfo("AppActivityMapper: start activity -> " + activityName);
  }

  private void logStop(String activityName) {
    this.logInfo("AppActivityMapper: stopActivity -> " + activityName);
  }

  private void logInfo(String message) {
    logger.log(Level.INFO, message);
  }
}
