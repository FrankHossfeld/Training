package de.gishmo.gwt.example.module0404.server;

import net.customware.gwt.dispatch.client.standard.StandardDispatchService;
import net.customware.gwt.dispatch.server.DefaultActionHandlerRegistry;
import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.server.InstanceActionHandlerRegistry;
import net.customware.gwt.dispatch.server.SimpleDispatch;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.DispatchException;
import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.gishmo.gwt.example.module0404.server.handler.GetAllPersonsHandler;
import de.gishmo.gwt.example.module0404.server.handler.GetPersonHandler;

@SuppressWarnings("serial")
public class Module0404DispatchServlet
  extends RemoteServiceServlet
  implements StandardDispatchService {

  private Dispatch dispatch;
  
  public Module0404DispatchServlet() {
    InstanceActionHandlerRegistry registry = new DefaultActionHandlerRegistry();
    registry.addHandler(new GetAllPersonsHandler());
    registry.addHandler(new GetPersonHandler());
    dispatch = new SimpleDispatch(registry);
  }
  
  @Override
  public Result execute(Action<?> action)
    throws DispatchException {
    try {
      return dispatch.execute(action);
    } catch (RuntimeException e) {
      log( "Exception while executing " + action.getClass().getName() + ": " + e.getMessage(), e );
      throw e;
    }
  }
}
