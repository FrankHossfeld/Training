package de.gishmo.module0812.domain.server.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import org.codehaus.jackson.map.ObjectMapper;

public class LoggingResponseFilter implements ContainerResponseFilter {

  public void filter(ContainerRequestContext requestContext,
                     ContainerResponseContext responseContext) throws IOException {
    String method = requestContext.getMethod();

    System.out
        .println("=====================================================================================================================");
    System.out.println("DomainService: Requesting " + method + " for path " + requestContext.getUriInfo().getPath());
    Object entity = responseContext.getEntity();
    if (entity != null) {
      System.out.println("DomainService: Response " + new ObjectMapper().writerWithDefaultPrettyPrinter()
          .writeValueAsString(entity));
    }
    System.out
        .println("=====================================================================================================================");

  }
}
