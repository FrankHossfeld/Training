package de.gishmo.gwt.example.module0810.client;

import com.google.gwt.user.client.ui.Widget;
import com.mvp4g.client.annotation.*;
import com.mvp4g.client.event.EventBus;
import de.gishmo.gwt.example.module0810.client.history.DefaultHistoryConverter;
import de.gishmo.gwt.example.module0810.client.ui.detail.DetailPresenter;
import de.gishmo.gwt.example.module0810.client.ui.list.ListPresenter;
import de.gishmo.gwt.example.module0810.client.ui.navigation.NavigationPresenter;
import de.gishmo.gwt.example.module0810.client.ui.search.SearchPresenter;
import de.gishmo.gwt.example.module0810.client.ui.shell.ShellPresenter;

@Events(startPresenter = ShellPresenter.class,
        historyOnStart = true,
        ginModules = Module0810GinModule.class)
@Debug(logLevel = Debug.LogLevel.DETAILED,
       logger = Module0810Logger.class)
@Filters(filterClasses = Module0810EventFilter.class,
         filterForward = false)
public interface Module0810EventBus extends EventBus {

  @Start
  @Event(bind = { NavigationPresenter.class },
         handlers = ShellPresenter.class)
  void start();
 
  @Event(handlers = ShellPresenter.class)
  void setNavigation(Widget widget);

  @Event(handlers = ShellPresenter.class)
  void setContent(Widget widget);

  @Event(handlers = ShellPresenter.class)
  void setStatus(String status);
  
  @Event(handlers = {DetailPresenter.class},
         historyConverter = DefaultHistoryConverter.class,
         name = "R2D2",
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
