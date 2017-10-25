package de.gishmo.gwt.example.module0909.client.ui.shell;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ResizeLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

import de.gishmo.gwt.example.module0909.client.resources.ApplicationConstants;
import de.gishmo.gwt.example.module0909.client.resources.ApplicationCss;
import de.gishmo.gwt.example.module0909.client.resources.CssProvider;
import de.gishmo.gwt.example.module0909.client.resources.ImageResources;
import de.gishmo.gwt.example.module0909.client.widgets.ReverseComposite;

public class ShellView
  extends ReverseComposite<IShellView.Presenter>
  implements IShellView {

  private ResizeLayoutPanel shell;
  private DockLayoutPanel   panel;
  private ApplicationCss    style;
  private Label             status;
  private ResizeLayoutPanel footerPanel;

  private Widget widget;

  //------------------------------------------------------------------------------

  public ShellView() {
    super();
    createView();
  }

  //------------------------------------------------------------------------------

  @Override
  public void setCenter(IsWidget widget) {
    if (this.widget != null) {
      this.widget.removeFromParent();
    }
    panel.add(widget);
    this.widget = widget.asWidget();
  }

  @Override
  public void setNavigation(IsWidget widget) {
    panel.addWest(widget,
                  212);
  }

  //  public void onLoad() {
  //    super.onLoad();
  //    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
  //      @Override
  //      public void execute() {
  //        forceLayout();
  //      }
  //    });
  //  }

  //------------------------------------------------------------------------------

  public void createView() {
    GWT.debugger();
    this.style = CssProvider.get()
                            .getStyle();
    GWT.debugger();
    shell = new ResizeLayoutPanel();
    shell.setSize("100%",
                  "100%");
    //    shell.addResizeHandler(new ResizeHandler() {
    //      @Override
    //      public void onResize(ResizeEvent event) {
    //        forceLayout();
    //      }
    //    });

    panel = new DockLayoutPanel(Unit.PX);
    panel.setSize("100%",
                  "100%");
    shell.add(panel);

    Widget header = createNorth();
    panel.addNorth(header,
                   128);

    Widget footer = createSouth();
    panel.addSouth(footer,
                   42);

    initWidget(shell);
  }

  private Widget createNorth() {
    FlowPanel panel = new FlowPanel();
    panel.addStyleName(style.headerPanel());

    Image image = new Image(ImageResources.INSTANCE.gwtLogo());
    image.addStyleName(style.header());
    panel.add(image);

    return panel;
  }

  private Widget createSouth() {
    footerPanel = new ResizeLayoutPanel();
    footerPanel.addStyleName(style.footerPanel());

    FlowPanel panel = new FlowPanel();
    footerPanel.add(panel);

    FlowPanel left = new FlowPanel();
    left.addStyleName(style.footerLeft());
    panel.add(left);

    FlowPanel right = new FlowPanel();
    right.addStyleName(style.footerRight());
    panel.add(right);

    Label label = new Label(ApplicationConstants.CONSTANTS.footerText());
    label.addStyleName(style.footerLabel());
    left.add(label);

    status = new Label("GWT-Schulung");
    status.addStyleName(style.footerStatus());
    right.add(status);

    return footerPanel;
  }

  //  private void forceLayout() {
  //    if (shell.isAttached()) {
  //      Widget parent = getParent();
  //      if (parent != null) {
  //        int parentWidth = parent.getOffsetWidth();
  //
  //        footerPanel.setWidth(Integer.toString(parentWidth) + "px");
  //      }
  //    }
  //  }
}
