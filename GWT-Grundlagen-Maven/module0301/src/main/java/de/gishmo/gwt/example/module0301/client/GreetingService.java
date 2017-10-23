package de.gishmo.gwt.example.module0301.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService
  extends RemoteService {
  String greetServer(String name)
    throws IllegalArgumentException;
}
