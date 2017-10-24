package de.gishmo.gwt.example.module0806.client.resources;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;

public class ApplicationStyleFactory {
  
  public interface Resources
  extends ClientBundle {
    
    @Source("ApplicationCss.css")
    ApplicationCss style();
    
  }

  private static ApplicationStyleFactory factory;
  private ApplicationCss style;

  private ApplicationStyleFactory() {
    /* create Resources */
    Resources resources = GWT.create(Resources.class);
    this.style = resources.style();
    this.style.ensureInjected();
  }
  
  public static ApplicationStyleFactory get() {
    if (factory == null) {
      factory = new ApplicationStyleFactory();
    }
    return factory;
  }
  

  public ApplicationCss getStyle() {
    return style;
  }
}
