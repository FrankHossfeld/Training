package de.gishmo.gwt.example.module0403.server;

import com.google.web.bindery.requestfactory.shared.ServiceLocator;

public class PersonServiceLocator
  implements ServiceLocator {

  @Override
  public Object getInstance(Class<?> clazz) {
    return clazz.equals(PersonService.class) ? new PersonService() : null;
  }

}
