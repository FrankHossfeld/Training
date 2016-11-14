package de.gishmo.module0812.submodule.gwt.client.resources;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.Constants;

public interface DetailConstants
  extends Constants {

  DetailConstants CONSTANTS = GWT.create(DetailConstants.class);

  String detailHeadline();
  
  String detailFirstName();
  
  String detailName();
  
  String detailStreet();
  
  String detailZip();
  
  String detailCity();

  String saveButton();
  
  String revertButton();

  String statusDetail();
  
}
