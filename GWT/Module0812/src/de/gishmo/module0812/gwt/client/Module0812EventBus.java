package de.gishmo.module0812.gwt.client;

import com.google.gwt.user.client.ui.Widget;
import com.mvp4g.client.annotation.Debug;
import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;
import com.mvp4g.client.annotation.Filters;
import com.mvp4g.client.annotation.InitHistory;
import com.mvp4g.client.annotation.Start;
import com.mvp4g.client.event.EventBus;

import de.gishmo.module0812.gwt.client.history.DefaultHistoryConverter;
import de.gishmo.module0812.gwt.client.ui.detail.DetailPresenter;
import de.gishmo.module0812.gwt.client.ui.list.ListPresenter;
import de.gishmo.module0812.gwt.client.ui.navigation.NavigationPresenter;
import de.gishmo.module0812.gwt.client.ui.search.SearchPresenter;
import de.gishmo.module0812.gwt.client.ui.shell.ShellPresenter;

@Events(startPresenter = ShellPresenter.class,
        historyOnStart = true,
        ginModules = Module0810GinModule.class)
@Debug(logLevel = Debug.LogLevel.DETAILED,
       logger = Module0812Logger.class)
@Filters(filterClasses = Module0812EventFilter.class,
         filterForward = false)
public interface Module0812EventBus extends EventBus {

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
  
  @Event(handlers = {DetailPresenter.class},
         historyConverter = DefaultHistoryConverter.class,
         navigationEvent = true)
  void gotoDetail(long id);
  
  @Event(handlers = {ListPresenter.class},
         historyConverter = DefaultHistoryConverter.class,
         navigationEvent = true)
  void gotoList(String searchName, String searchOrt);
  
  @InitHistory
  @Event(handlers = {SearchPresenter.class},
         historyConverter = DefaultHistoryConverter.class,
         navigationEvent = true)
  void initHistory();

  @Event(handlers = {SearchPresenter.class},
         historyConverter = DefaultHistoryConverter.class)
  void gotoSearch(String searchName, String searchOrt);
}
