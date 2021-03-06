package de.gishmo.gwt.example.domain.services.client;

import com.google.gwt.core.client.GWT;
import de.gishmo.gwt.example.domain.services.client.dto.ExceptionInfoService;
import de.gishmo.gwt.example.domain.services.client.dto.PersonService;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;


public class Services {

  /* instance of the factory */
  private static Services services;
  private        String   path;

  /* Services */
  private ExceptionInfoService exceptionInfoService;
  private PersonService        PersonService;

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
    path = path.substring(0,
                          path.lastIndexOf("/"));
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

  public ExceptionInfoService getExceptionInfoService() {
    if (exceptionInfoService == null) {
      exceptionInfoService = GWT.create(ExceptionInfoService.class);
      ((RestServiceProxy) exceptionInfoService).setResource(getResource("ExceptionInfo"));
    }
    return exceptionInfoService;
  }
}
