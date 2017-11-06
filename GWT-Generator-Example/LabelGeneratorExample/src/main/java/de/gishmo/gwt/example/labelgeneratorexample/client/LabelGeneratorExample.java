package de.gishmo.gwt.example.labelgeneratorexample.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import de.gishmo.gwt.example.labelgenerator.client.LabelProvider;
import de.gishmo.gwt.example.labelgenerator.client.SimpleLabelSupportDriver;
import de.gishmo.gwt.example.labelgenerator.client.annotations.LabelSupport;
import de.gishmo.gwt.example.labelgenerator.client.widget.MyLabel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class LabelGeneratorExample
  implements EntryPoint,
             LabelProvider {

  @LabelSupport("searchViewName")
  MyLabel mxLabel01;

  @LabelSupport("searchViewCity")
  MyLabel mxLabel02;

  @LabelSupport("labelKey03")
  MyLabel mxLabel03;

  @LabelSupport("searchViewName")
  MyLabel mxLabel04;

  private LabelDriver labelDriver;

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    RootPanel.get()
             .add(mxLabel01 = new MyLabel());
    RootPanel.get()
             .add(mxLabel02 = new MyLabel());
    RootPanel.get()
             .add(mxLabel03 = new MyLabel());
    RootPanel.get()
             .add(mxLabel04 = new MyLabel());

//    mxLabel04.setLabel(LabelFactory.get().getLabel("searchViewName"));

    labelDriver = GWT.create(LabelDriver.class);
    labelDriver.initializeAndLoad(this);
  }

  interface LabelDriver
    extends SimpleLabelSupportDriver<LabelGeneratorExample> {
  }
}
