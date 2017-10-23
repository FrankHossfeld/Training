package de.gishmo.gwt.example.module0701.communication.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.gishmo.gwt.example.module0701.communication.shared.dto.Person;
import de.gishmo.gwt.example.module0701.communication.shared.exception.PersonNotFoundException;

import java.util.List;

@RemoteServiceRelativePath("person")
public interface PersonService
  extends RemoteService {
  
  public Person get(long id) throws PersonNotFoundException;
  
  public List<Person> getAll();

}
