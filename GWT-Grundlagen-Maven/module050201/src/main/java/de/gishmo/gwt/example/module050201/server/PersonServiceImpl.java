package de.gishmo.gwt.example.module050201.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.gishmo.gwt.example.module050201.client.PersonService;
import de.gishmo.gwt.example.module050201.shared.dto.Address;
import de.gishmo.gwt.example.module050201.shared.dto.Person;
import de.gishmo.gwt.example.module050201.shared.exception.PersonNotFoundException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class PersonServiceImpl
  extends RemoteServiceServlet
  implements PersonService {

  private static Map<Long, Person> persons;
  
  public void init(ServletConfig config)
    throws ServletException {
    super.init(config);
    
    if (persons == null) {
      persons = new HashMap<Long, Person>();
      initList();
    }
  }
  
  private void initList() {
    Address address01 = new Address(1, "Evergreen Terrace", "7 42", "Springfield");
    persons.put(1L, new Person(1, "Simpson", "Homer", address01));
    persons.put(2L, new Person(2, "Simpson", "Marge", address01));
    persons.put(3L, new Person(3, "Simpson", "Bart", address01));
    persons.put(4L, new Person(5, "Simpson", "Maggie", address01));
    persons.put(5L, new Person(4, "Simpson", "Lisa", address01));
    Address address02 = new Address(2, "Blumenweg Nr. 13", "", "Entenhausen");
    persons.put(6L, new Person(6, "Duck", "Donald", address02));
    persons.put(7L, new Person(7, "Duck", "Trick", address02));
    persons.put(8L, new Person(8, "Duck", "Tick", address02));
    persons.put(9L, new Person(9, "Duck", "Tack", address02));
    Address address03 = new Address(2, "Am Goldberg Nr. 1", "", "Entenhausen");
    persons.put(10L, new Person(10, "Duck", "Dagobert", address03));
  }

  @Override
  public Person get(long id)
    throws PersonNotFoundException {
    if (persons.containsKey(new Long(id))) {
      return persons.get(id);
    } else {
      throw new PersonNotFoundException("no data found for ID >>" + Long.toString(id) + "<<"); 
    }
  }

  @Override
  public List<Person> getAll() {
    return new ArrayList<Person>(persons.values());
  }
}
