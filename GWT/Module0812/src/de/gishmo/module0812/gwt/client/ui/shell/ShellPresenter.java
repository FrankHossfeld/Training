package de.gishmo.module0812.gwt.client.ui.shell;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

import de.gishmo.module0812.gwt.client.Module0812EventBus;
import de.gishmo.module0812.gwt.client.model.ClientContext;

@Presenter(view = IShellView.class)
public class ShellPresenter
  extends BasePresenter<IShellView,
                        Module0812EventBus>
  implements IShellView.Presenter {

  @SuppressWarnings("unused")
  @Inject
  private ClientContext clientContext;

  public void bind() {
    Element loadingIndicator = DOM.getElementById("loading");
    if (loadingIndicator != null) {
      loadingIndicator.removeFromParent();
    }
  }
  
  public ShellPresenter() {
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
