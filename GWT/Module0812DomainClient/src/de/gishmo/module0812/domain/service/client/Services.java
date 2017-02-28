package de.gishmo.module0812.domain.service.client;

import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;

import com.google.gwt.core.client.GWT;

import de.gishmo.module0812.domain.service.client.dto.ExceptionInfoService;
import de.gishmo.module0812.domain.service.client.dto.PersonService;


public class Services {

  /* instance of the factory */
  private static Services services;
  private String   path;

  /* Services */
  private ExceptionInfoService exceptionInfoService;
  private PersonService PersonService;

  private Services() {
    // set standard date format!
    Defaults.setDateFormat(null);
    // initialize default variable
    String moduleName = GWT.getModuleName();
    GWT.log("getHostPageBaseURL >>" + GWT.getHostPageBaseURL() + "<<");
    GWT.log("getModuleBaseForStaticFiles >>" + GWT.getModuleBaseForStaticFiles() + "<<");
    GWT.log("getModuleBaseURL >>" + GWT.getModuleBaseURL() + "<<");
    path = GWT.getModuleBaseURL()
              .substring(0,
                         GWT.getModuleBaseURL()
                            .indexOf(moduleName) - 1);
    path = path.substring(0, path.lastIndexOf("/"));
    GWT.log("path: >>" + path + "<<");
  }

  public static Services get() {
    if (services == null) {
      services = new Services();
    }
    return services;
  }

  public PersonService getPersonService() {
    if (PersonService == null) {
    	PersonService = GWT.create(PersonService.class);
      ((RestServiceProxy) PersonService).setResource(getResource("Person"));
    }
    return PersonService;
  }
  
  public ExceptionInfoService getExceptionInfoService() {
    if (exceptionInfoService == null) {
      exceptionInfoService = GWT.create(ExceptionInfoService.class);
      ((RestServiceProxy) exceptionInfoService).setResource(getResource("ExceptionInfo"));
    }
    return exceptionInfoService;
  }

  private Resource getResource(String resource) {
    // Resource-Path aufbauen
    StringBuilder resourcePath = new StringBuilder();
    resourcePath
//    .append(path + "Services")
                .append(path + "/DomainServices")
                .append("/api")
                .append("/" + resource);
    GWT.log("current resource path >>" + resourcePath.toString() + "<<");
    return new Resource(resourcePath.toString());
  }
}
