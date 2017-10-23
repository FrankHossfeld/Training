package de.gishmo.module0812.domain.server.resource;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import de.gishmo.module0812.domain.dto.shared.transport.ExceptionInfo;

@Singleton
@Path("/exceptionInfo")
public class ExceptionInfoResource {
  
  /**
   * @param pExceptionInfo
   */
  @POST
  @Path("/log")
  @Consumes("application/json")
  public void save(ExceptionInfo pExceptionInfo) {

    System.out.println("=====");
    System.out.println(pExceptionInfo.toString());
    System.out.println("=====");

  }
}
