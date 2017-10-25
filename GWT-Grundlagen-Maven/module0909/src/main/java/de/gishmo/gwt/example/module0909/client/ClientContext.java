package de.gishmo.gwt.example.module0909.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;

import de.gishmo.gwt.example.module0909.client.resources.ApplicationCss;

public class ClientContext {

  /* application */
  private ApplicationCss style;
  private String searchName;
  private String searchCity;
  public ClientContext() {
    /* create Resources */
    Resources resources = GWT.create(Resources.class);
    this.style = resources.style();
    this.style.ensureInjected();
  }

  //------------------------------------------------------------------------------

  public ApplicationCss getStyle() {
    return style;
  }

  //------------------------------------------------------------------------------

  public String getSearchName() {
    return searchName;
  }

  public void setSearchName(String searchName) {
    this.searchName = searchName;
  }

  public String getSearchCity() {
    return searchCity;
  }

  public void setSearchCity(String searchCity) {
    this.searchCity = searchCity;
  }

  public interface Resources
    extends ClientBundle {

    @Source("resources/ApplicationCss.css")
    ApplicationCss style();

  }
}
