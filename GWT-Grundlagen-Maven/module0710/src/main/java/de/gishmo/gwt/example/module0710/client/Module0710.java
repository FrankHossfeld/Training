package de.gishmo.gwt.example.module0710.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.user.client.ui.*;
import de.gishmo.gwt.example.module0710.client.resources.ApplicationConstants;
import de.gishmo.gwt.example.module0710.client.resources.ApplicationCss;
import de.gishmo.gwt.example.module0710.client.resources.ImageResources;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Module0710 implements EntryPoint {
  
  public interface Resources
    extends ClientBundle {
    
    @Source("resources/ApplicationCss.css")
    ApplicationCss style();
    
  }

  
  private ApplicationCss style;
  private DockLayoutPanel shell;
  

  /**
   * 
   * This is the entry point method.
   */
  public void onModuleLoad() {
    Resources resources = GWT.create(Resources.class);
    this.style = resources.style();
    this.style.ensureInjected();

    createShell();
    
    RootLayoutPanel.get().add(shell);
  }

  private void createShell() {
    shell = new DockLayoutPanel(Unit.PX);
    shell.setSize("100%", "100%");
    
    Widget header = createNorth();
    shell.addNorth(header, 128);
    
    Widget footer = createSouth();
    shell.addSouth(footer, 42);

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
