package de.gishmo.gwt.example.module0909.client.ui.shell;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

import de.gishmo.gwt.example.module0909.client.Module0909EventBus;

@Presenter(view = IShellView.class)
public class ShellPresenter
  extends BasePresenter<IShellView, Module0909EventBus>
  implements IShellView.Presenter {

  //------------------------------------------------------------------------------

  public ShellPresenter() {
  }

  //------------------------------------------------------------------------------

  public void onStart() {
    //    eventBus.createNavigation();
  }

  public void onSetCenter(Widget widget) {
    view.setCenter(widget);
  }

  public void onSetNavigation(Widget widget) {
    GWT.debugger();
    view.setNavigation(widget);
  }
}
