package de.gishmo.gwt.example.module0403.server;

import de.gishmo.gwt.example.module0403.server.domain.Person;

import javax.servlet.*;
import java.io.IOException;
import java.util.List;


/**
 * The server-side service class.
 */
public class PersonService
  implements Filter {

  public static final ThreadLocal<PersonSource> PERSON_SOURCE = new ThreadLocal<PersonSource>();

  public static Person createPerson() {
    checkPersonSource();
    Person person = new Person();
    PERSON_SOURCE.get().persist(person);
    return person;
  }

  public static Person findById(String id) {
    checkPersonSource();
    return PERSON_SOURCE.get().findById(id);
  }

  public static List<Person> getAll() {
    checkPersonSource();
    return PERSON_SOURCE.get().getAll();
  }

  public static void persist(Person person) {
    checkPersonSource();
    PERSON_SOURCE.get().persist(person);
  }

  private static void checkPersonSource() {
    if (PERSON_SOURCE.get() == null) {
      throw new IllegalStateException(
          "Calling service method outside of HTTP request");
    }
  }

  private PersonSource backingStore;

  public void destroy() {
  }

  public void doFilter(ServletRequest req,
                       ServletResponse resp,
                       FilterChain chain) 
      throws IOException, ServletException {
    try {
      PERSON_SOURCE.set(PersonSource.of(backingStore));
      chain.doFilter(req, resp);
    } finally {
      PERSON_SOURCE.set(null);
    }
  }

  public void init(FilterConfig config) {
    backingStore = (PersonSource) config.getServletContext().getAttribute(PersonService.class.getName());
    if (backingStore == null) {
      List<Person> people = PersonFuzzer.generatePersons();
      backingStore = PersonSource.of(people);
      config.getServletContext().setAttribute(PersonService.class.getName(), backingStore);
    }
  }
}
