package de.gishmo.gwt.example.module0403.shared;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

import de.gishmo.gwt.example.module0403.server.PersonLocator;
import de.gishmo.gwt.example.module0403.server.domain.Person;

@ProxyFor(value = Person.class, locator = PersonLocator.class)
public interface PersonProxy
  extends EntityProxy {
  
  public String getFirstName();

  public String getId();

  public String getName();
  
  public void setId(String id);

  public void setName(String name);

  public void setFirstName(String firstName);
  
  AddressProxy getAddress();
  
  void setAddress(AddressProxy address);
  
  
  EntityProxyId<PersonProxy> stableId();

}
