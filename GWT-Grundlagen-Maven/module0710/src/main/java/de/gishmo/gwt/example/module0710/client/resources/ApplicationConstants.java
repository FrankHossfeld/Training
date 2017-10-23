package de.gishmo.gwt.example.module0710.client.resources;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.Constants;

public interface ApplicationConstants
  extends Constants {

  ApplicationConstants CONSTANTS = GWT.create(ApplicationConstants.class);
  
  String footerText();
  
}
