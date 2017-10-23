package de.gishmo.module0812.submodule.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;

public class MyEntryPoint implements EntryPoint {

  @Override
  public void onModuleLoad() {
    GWT.log("EntryPoint of sub modiule has been called");
  }
}
