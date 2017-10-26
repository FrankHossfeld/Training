package de.gishmo.gwt.example.domain.services.client.dto;

import de.gishmo.gwt.example.domain.dto.shared.transport.ExceptionInfo;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

public interface ExceptionInfoService
  extends RestService {

  @POST
  @Path("/exceptionInfo/log")
  void log(ExceptionInfo pExceptionInfo,
           MethodCallback<Void> methodCallback);

}
