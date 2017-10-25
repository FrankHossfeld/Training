package de.gishmo.gwt.example.module0909.client.ui.shell;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

import de.gishmo.gwt.example.module0909.client.Module0909EventBus;
import de.gishmo.gwt.example.module0909.client.model.ClientContext;

@Presenter(view = IShellView.class)
public class ShellPresenter
  extends BasePresenter<IShellView, Module0909EventBus>
  implements IShellView.Presenter {

  @SuppressWarnings("unused")
  @Inject
  private ClientContext clientContext;

  public ShellPresenter() {
  }

  public void bind() {
    Element loadingIndicator = DOM.getElementById("loading");
    if (loadingIndicator != null) {
      loadingIndicator.removeFromParent();
    }
  }

  public void onSetContent(Widget widget) {
    view.setCenter(widget);
  }

  public void onSetNavigation(Widget widget) {
    view.setNavigation(widget);
  }

  public void onStart() {
  }

  public void onSetStatus(String status) {
    view.setStatus(status);
  }

}
