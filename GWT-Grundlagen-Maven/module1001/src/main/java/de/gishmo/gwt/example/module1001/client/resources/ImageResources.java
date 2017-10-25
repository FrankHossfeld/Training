package de.gishmo.gwt.example.module1001.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface ImageResources
  extends ClientBundle {

  ImageResources INSTANCE = (ImageResources) GWT.create(ImageResources.class);
  
  @Source("Gwt-logo.png")
  ImageResource gwtLogo();
  
}
