package de.gishmo.gwt.example.module040101.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.gishmo.gwt.example.module040101.client.services.PersonService;
import de.gishmo.gwt.example.module040101.shared.dto.Address;
import de.gishmo.gwt.example.module040101.shared.dto.Person;
import de.gishmo.gwt.example.module040101.shared.exception.PersonNotFoundException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.util.HashMap;
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
    Address address01 = new Address(1,
                                    "Evergreen Terrace",
                                    "7 42",
                                    "Springfield");
    persons.put(new Long(1),
                new Person(1,
                           "Simpson",
                           "Homer",
                           address01));
    persons.put(new Long(2),
                new Person(2,
                           "Simpson",
                           "Marge",
                           address01));
    persons.put(new Long(3),
                new Person(3,
                           "Simpson",
                           "Bart",
                           address01));
    persons.put(new Long(4),
                new Person(4,
                           "Simpson",
                           "Maggie",
                           address01));
    persons.put(new Long(5),
                new Person(5,
                           "Simpson",
                           "Lisa",
                           address01));
    Address address02 = new Address(2,
                                    "Blumenweg Nr. 13",
                                    "",
                                    "Entenhausen");
    persons.put(new Long(6),
                new Person(6,
                           "Duck",
                           "Donald",
                           address02));
    persons.put(new Long(7),
                new Person(7,
                           "Duck",
                           "Trick",
                           address02));
    persons.put(new Long(8),
                new Person(8,
                           "Duck",
                           "Tick",
                           address02));
    persons.put(new Long(9),
                new Person(9,
                           "Duck",
                           "Tack",
                           address02));
    Address address03 = new Address(2,
                                    "Am Goldberg Nr. 1",
                                    "",
                                    "Entenhausen");
    persons.put(new Long(10),
                new Person(10,
                           "Duck",
                           "Dagobert",
                           address03));
  }

  @Override
  public Person get(long id)
    throws PersonNotFoundException {
    if (persons.containsKey(id)) {
      return persons.get(id);
    } else {
      throw new PersonNotFoundException("no data found for ID >>" + Long.toString(id) + "<<");
    }
  }

}
