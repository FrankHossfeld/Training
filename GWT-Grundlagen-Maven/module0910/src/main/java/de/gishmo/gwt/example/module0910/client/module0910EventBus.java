package de.gishmo.gwt.example.module0910.client;

import com.google.gwt.user.client.ui.Widget;
import com.mvp4g.client.annotation.*;
import com.mvp4g.client.event.EventBus;
import de.gishmo.gwt.example.module0910.client.history.DefaultHistoryConverter;
import de.gishmo.gwt.example.module0910.client.ui.detail.DetailPresenter;
import de.gishmo.gwt.example.module0910.client.ui.list.ListPresenter;
import de.gishmo.gwt.example.module0910.client.ui.navigation.NavigationPresenter;
import de.gishmo.gwt.example.module0910.client.ui.search.SearchPresenter;
import de.gishmo.gwt.example.module0910.client.ui.shell.ShellPresenter;

@Events(startPresenter = ShellPresenter.class, historyOnStart = true, ginModules = module0910GinModule.class)
@Debug(logLevel = Debug.LogLevel.DETAILED, logger = module0910Logger.class)
@Filters(filterClasses = module0910EventFilter.class, filterForward = false)
public interface module0910EventBus
  extends EventBus {

  @Start
  @Event(bind = {NavigationPresenter.class}, handlers = ShellPresenter.class)
  public void start();

  @Event(handlers = ShellPresenter.class)
  void setNavigation(Widget widget);

  @Event(handlers = ShellPresenter.class)
  void setContent(Widget widget);

  @Event(handlers = ShellPresenter.class)
  void setStatus(String status);

  @Event(handlers = {DetailPresenter.class}, historyConverter = DefaultHistoryConverter.class, navigationEvent = true)
  void gotoDetail(long id);

  @Event(handlers = {ListPresenter.class}, historyConverter = DefaultHistoryConverter.class, navigationEvent = true)
  void gotoList(String searchName,
                String searchOrt);

  @InitHistory
  @Event(handlers = {SearchPresenter.class}, historyConverter = DefaultHistoryConverter.class, navigationEvent = true)
  void initHistory();

  @Event(handlers = {SearchPresenter.class}, historyConverter = DefaultHistoryConverter.class)
  void gotoSearch(String searchName,
                  String searchOrt);
}
