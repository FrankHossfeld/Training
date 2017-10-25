package de.gishmo.gwt.example.module0403.server;

import de.gishmo.gwt.example.module0403.server.domain.Address;
import de.gishmo.gwt.example.module0403.server.domain.Person;

import java.util.ArrayList;
import java.util.List;


/**
 * Utility class for creating persons.
 */
class PersonFuzzer {

  private static List<Person> persons;
  
  private static void initList() {
    Address address01 = new Address("1", "Evergreen Terrace", "7 42", "Springfield");
    persons.add(new Person("1", "Simpson", "Homer", address01));
    persons.add(new Person("2", "Simpson", "Marge", address01));
    persons.add(new Person("3", "Simpson", "Bart", address01));
    persons.add(new Person("5", "Simpson", "Maggie", address01));
    persons.add(new Person("4", "Simpson", "Lisa", address01));
    Address address02 = new Address("2", "Blumenweg Nr. 13", "", "Entenhausen");
    persons.add(new Person("6", "Duck", "Donald", address02));
    persons.add(new Person("7", "Duck", "Trick", address02));
    persons.add(new Person("8", "Duck", "Tick", address02));
    persons.add(new Person("9", "Duck", "Tack", address02));
    Address address03 = new Address("2", "Am Goldberg Nr. 1", "", "Entenhausen");
    persons.add(new Person("10", "Duck", "Dagobert", address03));
  }

  PersonFuzzer() {
  }
  
  public static List<Person> generatePersons() {
    if (persons == null) {
      persons = new ArrayList<Person>();
      initList();
    } 
    return persons;
  }
}
