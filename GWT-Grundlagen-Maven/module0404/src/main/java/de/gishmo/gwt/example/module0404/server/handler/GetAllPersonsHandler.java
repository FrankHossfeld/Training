package de.gishmo.gwt.example.module0404.server.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;
import de.gishmo.gwt.example.module0404.shared.actions.GetAllPersonsAction;
import de.gishmo.gwt.example.module0404.shared.dto.Address;
import de.gishmo.gwt.example.module0404.shared.dto.Person;
import de.gishmo.gwt.example.module0404.shared.results.GetAllPersonsResult;

public class GetAllPersonsHandler
  implements ActionHandler<GetAllPersonsAction, GetAllPersonsResult> {

  private static Map<Long, Person> persons;

  @Override
  public Class<GetAllPersonsAction> getActionType() {
    return GetAllPersonsAction.class;
  }

  @Override
  public GetAllPersonsResult execute(GetAllPersonsAction action,
                                     ExecutionContext context)
    throws DispatchException {
    if (persons == null) {
      persons = new HashMap<Long, Person>();
      initList();
    }
    return new GetAllPersonsResult(this.getAll());
  }

  @Override
  public void rollback(GetAllPersonsAction action,
                       GetAllPersonsResult result,
                       ExecutionContext context)
    throws DispatchException {
    // TODO Auto-generated method stub
  }
  
  private List<Person> getAll() {
    /* handle the static stuff */
    List<Person> list = new ArrayList<Person>();
    Iterator<Long> iterator = persons.keySet().iterator();
    while (iterator.hasNext()) {
      list.add(persons.get(iterator.next()));
    }
    return list;
  }
  
  private void initList() {
    Address address01 = new Address(1, "Evergreen Terrace", "7 42", "Springfield");
    persons.put(new Long(1), new Person(1, "Simpson", "Homer", address01));
    persons.put(new Long(2), new Person(2, "Simpson", "Marge", address01));
    persons.put(new Long(3), new Person(3, "Simpson", "Bart", address01));
    persons.put(new Long(4), new Person(5, "Simpson", "Maggie", address01));
    persons.put(new Long(5), new Person(4, "Simpson", "Lisa", address01));
    Address address02 = new Address(2, "Blumenweg Nr. 13", "", "Entenhausen");
    persons.put(new Long(6), new Person(6, "Duck", "Donald", address02));
    persons.put(new Long(7), new Person(7, "Duck", "Trick", address02));
    persons.put(new Long(8), new Person(8, "Duck", "Tick", address02));
    persons.put(new Long(9), new Person(9, "Duck", "Tack", address02));
    Address address03 = new Address(2, "Am Goldberg Nr. 1", "", "Entenhausen");
    persons.put(new Long(10), new Person(10, "Duck", "Dagobert", address03));
  }
}
