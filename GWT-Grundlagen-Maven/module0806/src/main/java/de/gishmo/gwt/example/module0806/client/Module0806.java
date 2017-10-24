package de.gishmo.gwt.example.module0806.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Module0806
  implements EntryPoint {
  
  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    Application application = new Application();
    application.run(RootLayoutPanel.get());
  }
}
