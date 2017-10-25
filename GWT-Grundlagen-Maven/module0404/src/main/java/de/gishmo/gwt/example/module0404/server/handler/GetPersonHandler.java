package de.gishmo.gwt.example.module0404.server.handler;

import java.util.HashMap;
import java.util.Map;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;
import de.gishmo.gwt.example.module0404.shared.actions.GetPersonAction;
import de.gishmo.gwt.example.module0404.shared.dto.Address;
import de.gishmo.gwt.example.module0404.shared.dto.Person;
import de.gishmo.gwt.example.module0404.shared.exceptions.PersonNotFoundException;
import de.gishmo.gwt.example.module0404.shared.results.GetPersonResult;

public class GetPersonHandler
  implements ActionHandler<GetPersonAction, GetPersonResult> {

  private static Map<Long, Person> persons;

  @Override
  public Class<GetPersonAction> getActionType() {
    return GetPersonAction.class;
  }

  @Override
  public GetPersonResult execute(GetPersonAction action,
                                 ExecutionContext context)
    throws DispatchException {
    if (persons == null) {
      persons = new HashMap<Long, Person>();
      initList();
    }
    return new GetPersonResult(this.get(action.getId()));
  }

  @Override
  public void rollback(GetPersonAction action,
                       GetPersonResult result,
                       ExecutionContext context)
    throws DispatchException {
    // TODO Auto-generated method stub
  }
  
  private Person get(long id)
      throws PersonNotFoundException {
      if (persons.containsKey(new Long(id))) {
        return persons.get(id);
      } else {
        throw new PersonNotFoundException("no data found for ID >>" + Long.toString(id) + "<<"); 
      }
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
