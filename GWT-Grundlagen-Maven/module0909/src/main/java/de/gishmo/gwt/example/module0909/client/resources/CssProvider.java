package de.gishmo.gwt.example.module0909.client.resources;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;

public class CssProvider {

  private static CssProvider instance;
  /* application */
  private ApplicationCss style;

  private CssProvider() {
    /* create Resources */
    Resources resources = GWT.create(Resources.class);
    this.style = resources.style();
    this.style.ensureInjected();
  }

  public static CssProvider get() {
    if (instance == null) {
      instance = new CssProvider();
    }
    return instance;
  }

  public ApplicationCss getStyle() {
    return style;
  }

  //------------------------------------------------------------------------------

  public interface Resources
    extends ClientBundle {

    @Source("ApplicationCss.css")
    ApplicationCss style();

  }
}
