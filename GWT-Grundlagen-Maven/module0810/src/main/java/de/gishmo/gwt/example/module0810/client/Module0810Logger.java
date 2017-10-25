package de.gishmo.gwt.example.module0810.client;

import com.google.gwt.core.client.GWT;
import com.mvp4g.client.event.Mvp4gLogger;

public class Module0810Logger implements Mvp4gLogger {

  public void log(String message, int depth) {
    String indent = "";
    for ( int i = 0; i < depth; ++i ) {
      indent += "....";
    }
    GWT.log( indent + "Module0810Logger: " + message );
  }
}
