package de.gishmo.module0812.submodule.gwt.client;

import com.google.gwt.user.client.ui.Widget;
import com.mvp4g.client.annotation.Debug;
import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;
import com.mvp4g.client.annotation.Filters;
import com.mvp4g.client.event.EventBus;
import com.mvp4g.client.presenter.NoStartPresenter;

import de.gishmo.module0812.submodule.gwt.client.history.DefaultDetailHistoryConverter;
import de.gishmo.module0812.submodule.gwt.client.ui.detail.DetailPresenter;

@Events(startPresenter = NoStartPresenter.class,
        module = Module0812SubModule.class,
        ginModules = Module0812SubGinModule.class,
        historyOnStart = false)
@Debug(logLevel = Debug.LogLevel.DETAILED,
       logger = Module0812SubModuleLogger.class)
@Filters(filterClasses = Module0812SubModuleEventFilter.class,
         filterForward = false)
public interface Module0812SubModuleEventBus extends EventBus {

  @Event(forwardToParent = true)
  void setContent(Widget widget);

  @Event(forwardToParent = true)
  void setStatus(String status);
  
  @Event(handlers = {DetailPresenter.class},
         historyConverter = DefaultDetailHistoryConverter.class,
         navigationEvent = true)
  void gotoDetail(long id);
  
  @Event(forwardToParent = true)
  void gotoList(String searchName, String searchOrt);

  @Event(forwardToParent = true)
  void gotoSearch(String searchName, String searchOrt);
}



