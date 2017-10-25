package de.gishmo.gwt.example.module0403.server;

import de.gishmo.gwt.example.module0403.server.domain.Person;

import java.util.*;


/**
 * Provides a number of Person objects as a demonstration datasource. Many of
 * the operations in this implementation would be much more efficient in a real
 * database, but are implemented is a straightforward fashion because they're
 * not really important for understanding the RequestFactory framework.
 */
public abstract class PersonSource {
  
  static class Backing
    extends PersonSource {
    
    private Long serial = 0L;
    
    public Backing() {
    }

    @Override
    public Person create(Class<? extends Person> clazz) {
      return new Person();
    }

    @Override
    public Person find(Class<? extends Person> clazz, String id) {
      if (!Person.class.equals(clazz)) {
        return null;
      }
      return persons.get(id);
    }

    @Override
    public Class<Person> getDomainType() {
      return Person.class;
    }

    @Override
    public String getId(Person domainObject) {
      return domainObject.getId();
    }

    @Override
    public Class<String> getIdType() {
      return String.class;
    }

    @Override
    public Object getVersion(Person domainObject) {
      return domainObject.getVersion();
    }

    @Override
    public int countPeople() {
      return persons.size();
    }

    @Override
    public Person findById(String id) {
      return persons.get(id);
    }

    @Override
    public void persist(Person person) {
      if (person.getId() == null) {
        person.setId(Long.toString(++serial));
      }
      person.setVersion(person.getVersion() + 1);
      Person existing = persons.get(person.getId());
      if (existing != null) {
        existing.copyFrom(person);
      } else {
        persons.put(person.getId(), person);
      }
    }

    @Override
    public List<Person> getAll() {
      Iterator<Person> it = persons.values().iterator();
      List<Person> toReturn = new ArrayList<Person>();
      while (it.hasNext()) {
        Person person = (Person) it.next();
        toReturn.add(person);
      }
      return toReturn;
    }
  }

  static class CopyOnRead
    extends Backing {
    private final PersonSource backingStore;

    public CopyOnRead(PersonSource backingStore) {
      this.backingStore = backingStore;
    }

    @Override
    public int countPeople() {
      return backingStore.countPeople();
    }

    @Override
    public Person findById(String id) {
      Person toReturn;
      if (persons.containsKey(new Long(id))) {
        toReturn = persons.get(id);
      } else {
        toReturn = backingStore.findById(id);
        if (toReturn != null) {
          toReturn = toReturn.makeCopy();
         }
        persons.put(id, toReturn);
      }
      return toReturn;
    }

    @Override
    public List<Person> getAll() {
      return backingStore.getAll();
    }

    @Override
    public void persist(Person person) {
      backingStore.persist(person);
    }
  }

  /**
   * Create a PersonSource that will act directly on the given list.
   */
  public static PersonSource of(List<Person> people) {
    PersonSource backing = new Backing();
    for (Person person : people) {
      backing.persist(person);
    }
    return backing;
  }

  /**
   * Create a PersonSource that will read through to the given source and make
   * copies of any objects that are requested.
   */
  public static PersonSource of(PersonSource backing) {
    return new CopyOnRead(backing);
  }

  final Map<String, Person> persons = new LinkedHashMap<String, Person>();

  public abstract int countPeople();

  public abstract Person findById(String id);

  public abstract List<Person> getAll();

  public abstract void persist(Person person);
  
  public abstract Person create(Class<? extends Person> clazz);
  
  public abstract Person find(Class<? extends Person> clazz, String id);

  public abstract Class<Person> getDomainType();

  public abstract String getId(Person domainObject);

  public abstract Class<String> getIdType();

  public abstract Object getVersion(Person domainObject);

}
