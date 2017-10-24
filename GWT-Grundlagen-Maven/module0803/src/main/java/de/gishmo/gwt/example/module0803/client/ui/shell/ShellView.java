package de.gishmo.gwt.example.module0803.client.ui.shell;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.*;
import de.gishmo.gwt.example.module0803.client.resources.ApplicationConstants;
import de.gishmo.gwt.example.module0803.client.resources.ApplicationCss;
import de.gishmo.gwt.example.module0803.client.resources.ImageResources;
import de.gishmo.gwt.example.module0803.client.widgets.ReverseComposite;

public class ShellView
  extends ReverseComposite<IShellView.Presenter>
  implements IShellView {

  private DockLayoutPanel shell;
  private ApplicationCss  style;
  
  private Widget widget;

//------------------------------------------------------------------------------

  public ShellView(ApplicationCss style) {
    super();
    
    this.style = style;
    
    createView();
  }

//------------------------------------------------------------------------------

  @Override
  public void setCenter(Widget widget) {
    if (this.widget != null) {
      this.widget.removeFromParent();
    }
    shell.add(widget);
    this.widget = widget;
  }

  @Override
  public void setNavigation(Widget widget) {
    shell.addWest(widget, 212);
  }

//------------------------------------------------------------------------------

  private void createView() {
    shell = new DockLayoutPanel(Unit.PX);
    shell.setSize("100%", "100%");
    
    Widget header = createNorth();
    shell.addNorth(header, 128);
    
    Widget footer = createSouth();
    shell.addSouth(footer, 42);
    
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
    FlowPanel panel = new FlowPanel();
    panel.addStyleName(style.footerPanel());
    
    Label label = new Label(ApplicationConstants.CONSTANTS.footerText());
    label.addStyleName(style.footerLabel());
    panel.add(label);
    
    return panel;
  }
}
