package de.gishmo.gwt.example.module0706.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import de.gishmo.gwt.example.module0707.client.widgets.TextField;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Module0706
  implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    FlowPanel vp = new FlowPanel();
    vp.getElement()
      .getStyle()
      .setWidth(100,
                Unit.PCT);
    vp.getElement()
      .getStyle()
      .setWidth(100,
                Unit.PCT);
    RootLayoutPanel.get()
                   .add(vp);

    vp.add(new TextField());
    vp.add(new TextField("Test"));
  }
}
