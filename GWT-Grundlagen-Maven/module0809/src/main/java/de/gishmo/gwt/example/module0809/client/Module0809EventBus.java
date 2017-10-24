package de.gishmo.gwt.example.module0809.client;

import com.google.gwt.user.client.ui.Widget;
import com.mvp4g.client.annotation.*;
import com.mvp4g.client.event.EventBus;
import de.gishmo.gwt.example.module0809.client.history.DefaultHistoryConverter;
import de.gishmo.gwt.example.module0806.client.ui.list.ListPresenter;
import de.gishmo.gwt.example.module0806.client.ui.navigation.NavigationPresenter;
import de.gishmo.gwt.example.module0806.client.ui.search.SearchPresenter;
import de.gishmo.gwt.example.module0806.client.ui.shell.ShellPresenter;

@Events(startPresenter = ShellPresenter.class,
        historyOnStart = true,
        ginModules = Module0809GinModule.class)
@Debug(logLevel = Debug.LogLevel.DETAILED,
       logger = Module0809Logger.class)
@Filters(filterClasses = Module0809EventFilter.class,
         filterForward = false)
public interface Module0809EventBus extends EventBus {

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
