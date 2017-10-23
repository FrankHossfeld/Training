package de.gishmo.module0812.gwt.client.resource;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.Constants;

public interface ApplicationConstants
  extends Constants {

  ApplicationConstants CONSTANTS = GWT.create(ApplicationConstants.class);
  
  String footerText();
  
  String searchFormButton();
  
  String listFormButton();

  String searchButton();

  String searchHeadline();
  
  String searchName();

  String searchCity();

  String resetButton();
  
  String resultHeadline();
  
  String resultText();
  
  String columnName();
  
  String columnStreet();
  
  String columnPlz();
  
  String columnCity();
  
  String statusSearch();
  
  String statusListZero();
  
  String statusListOne();
    
  String statusDetail();

  String detailMessage();
  
}
