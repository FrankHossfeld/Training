package de.gishmo.gwt.example.module1001.client.resources;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.Messages;

public interface ApplicationMessages
  extends Messages {

  ApplicationMessages MESSAGES = GWT.create(ApplicationMessages.class);
 
  String statusListMany(int size);

}
