package de.gishmo.training.gwt.client;

import com.google.gwt.user.client.ui.Widget;
import com.mvp4g.client.annotation.Debug;
import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;
import com.mvp4g.client.annotation.Filters;
import com.mvp4g.client.annotation.InitHistory;
import com.mvp4g.client.annotation.Start;
import com.mvp4g.client.event.EventBus;

import de.gishmo.training.gwt.client.history.DefaultHistoryConverter;
import de.gishmo.training.gwt.client.ui.detail.DetailPresenter;
import de.gishmo.training.gwt.client.ui.list.ListPresenter;
import de.gishmo.training.gwt.client.ui.navigation.NavigationPresenter;
import de.gishmo.training.gwt.client.ui.search.SearchPresenter;
import de.gishmo.training.gwt.client.ui.shell.ShellPresenter;

@Events(startPresenter = ShellPresenter.class,
        historyOnStart = true,
        ginModules = Module0810GinModule.class)
@Debug(logLevel = Debug.LogLevel.DETAILED,
       logger = Module0810Logger.class)
@Filters(filterClasses = Module0810EventFilter.class,
         filterForward = false)
public interface Module0810EventBus
  extends EventBus {

  @Start
  @Event(bind = { NavigationPresenter.class },
         handlers = ShellPresenter.class)
  public void start();

  @Event(handlers = ShellPresenter.class)
  void setNavigation(Widget widget);

  @Event(handlers = ShellPresenter.class)
  void setContent(Widget widget);

  @Event(handlers = ShellPresenter.class)
  void setStatus(String status);

  @Event(handlers = { DetailPresenter.class },
         historyConverter = DefaultHistoryConverter.class,
         navigationEvent = true)
  void gotoDetail(long id);

  @Event(handlers = { ListPresenter.class },
         historyConverter = DefaultHistoryConverter.class,
         navigationEvent = true)
  void gotoList(String searchName,
                String searchOrt);

  @InitHistory
  @Event(handlers = { SearchPresenter.class },
         historyConverter = DefaultHistoryConverter.class,
         navigationEvent = true)
  void initHistory();

  @Event(handlers = { SearchPresenter.class },
         historyConverter = DefaultHistoryConverter.class)
  void gotoSearch(String searchName,
                  String searchOrt);
}
