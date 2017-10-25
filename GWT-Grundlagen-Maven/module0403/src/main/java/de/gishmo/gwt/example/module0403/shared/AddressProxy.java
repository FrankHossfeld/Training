package de.gishmo.gwt.example.module0403.shared;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;

import de.gishmo.gwt.example.module0403.server.domain.Address;

@ProxyFor(value = Address.class)
public interface AddressProxy
  extends ValueProxy {

  public String getStreet();

  public void setStreet(String street);

  public String getZip();

  public void setZip(String zip);

  public String getCity();

  public void setCity(String city);

  public String getAddressId();

  public void setAddressId(String id);

}
