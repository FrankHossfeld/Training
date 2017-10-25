package de.gishmo.gwt.example.module0403.server;

import com.google.web.bindery.requestfactory.shared.Locator;

import de.gishmo.gwt.example.module0403.server.domain.Person;

public class PersonLocator
  extends Locator<Person, String> {
  
  public static PersonSource getThreadLocalObject() {
    return PersonService.PERSON_SOURCE.get();
  }

  @Override
  public Person create(Class<? extends Person> clazz) {
    return getThreadLocalObject().create(clazz);
  }

  @Override
  public Person find(Class<? extends Person> clazz, String id) {
    return getThreadLocalObject().find(clazz, id);
  }

  @Override
  public Class<Person> getDomainType() {
    return getThreadLocalObject().getDomainType();
  }

  @Override
  public String getId(Person domainObject) {
    return getThreadLocalObject().getId(domainObject);
  }

  @Override
  public Class<String> getIdType() {
    return getThreadLocalObject().getIdType();
  }

  @Override
  public Object getVersion(Person domainObject) {
    return getThreadLocalObject().getVersion(domainObject);
  }

  public void persist(Person domainObject) {
    getThreadLocalObject().persist(domainObject);
  }
}
