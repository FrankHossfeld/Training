package de.gishmo.module0812.gwt.client.resource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface ImageResources
  extends ClientBundle {

  ImageResources INSTANCE = (ImageResources) GWT.create(ImageResources.class);
  
  @Source("Gwt-logo.png")
  ImageResource gwtLogo();
  
}
