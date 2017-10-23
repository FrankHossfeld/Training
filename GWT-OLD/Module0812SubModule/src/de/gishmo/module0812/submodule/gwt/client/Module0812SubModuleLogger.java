package de.gishmo.module0812.submodule.gwt.client;

import com.google.gwt.core.client.GWT;
import com.mvp4g.client.event.Mvp4gLogger;

public class Module0812SubModuleLogger implements Mvp4gLogger {

  public void log(String message, int depth) {
    String indent = "";
    for ( int i = 0; i < depth; ++i ) {
      indent += "....";
    }
    GWT.log( indent + "Module0812SubModuleLogger: " + message );
  }
}
