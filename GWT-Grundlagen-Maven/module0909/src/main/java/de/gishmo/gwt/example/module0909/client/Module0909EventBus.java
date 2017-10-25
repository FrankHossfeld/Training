package de.gishmo.gwt.example.module0909.client;

import com.google.gwt.user.client.ui.Widget;
import com.mvp4g.client.annotation.Debug;
import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;
import com.mvp4g.client.annotation.Start;
import com.mvp4g.client.event.EventBus;

import de.gishmo.gwt.example.module0909.client.ui.list.ListPresenter;
import de.gishmo.gwt.example.module0909.client.ui.navigation.NavigationPresenter;
import de.gishmo.gwt.example.module0909.client.ui.search.SearchPresenter;
import de.gishmo.gwt.example.module0909.client.ui.shell.ShellPresenter;

@Events(startPresenter = ShellPresenter.class, historyOnStart = true, ginModules = Module0909GinModule.class)
@Debug(logLevel = Debug.LogLevel.DETAILED)
public interface Module0909EventBus
  extends EventBus {

  @Start
  @Event(bind = { NavigationPresenter.class, SearchPresenter.class })
  void start();

  @Event(handlers = ShellPresenter.class)
  void setNavigation(Widget widget);

  @Event(handlers = ShellPresenter.class)
  void setCenter(Widget widget);

  @Event(handlers = SearchPresenter.class)
  void gotoSearch();

  @Event(handlers = ListPresenter.class)
  void gotoList(String searcName,
                String searchCity);

}
