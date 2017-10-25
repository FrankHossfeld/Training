package de.gishmo.gwt.example.module0403.client;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;
import de.gishmo.gwt.example.module0403.server.PersonServiceLocator;
import de.gishmo.gwt.example.module0403.server.domain.Person;
import de.gishmo.gwt.example.module0403.shared.PersonProxy;

import java.util.List;

public interface PersonRequestFactory
  extends RequestFactory {
  
  @Service(value = Person.class, locator = PersonServiceLocator.class)
  public interface PersonRequest
    extends RequestContext {
    
    Request<PersonProxy> findById(String id);

    Request<List<PersonProxy>> getAll();
    
  }

  PersonRequest personRequest();
  
}
