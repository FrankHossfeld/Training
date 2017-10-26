package de.gishmo.gwt.example.domain.services.client.dto;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import de.gishmo.gwt.example.domain.services.client.transport.ExceptionInfo;

public interface ExceptionInfoService
  extends RestService {

  @POST
  @Path("/exceptionInfo/log")
  void log(ExceptionInfo pExceptionInfo, MethodCallback<Void> methodCallback);

}
