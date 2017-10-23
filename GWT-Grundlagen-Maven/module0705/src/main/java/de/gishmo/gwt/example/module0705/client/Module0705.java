package de.gishmo.gwt.example.module0705.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import de.gishmo.gwt.example.module0705.client.widgets.TextField;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Module0705
  implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    FlowPanel vp = new FlowPanel();
    RootLayoutPanel.get().add(vp);
    
    vp.add(new TextField());
    vp.add(new TextField("Test"));   
  }
}
