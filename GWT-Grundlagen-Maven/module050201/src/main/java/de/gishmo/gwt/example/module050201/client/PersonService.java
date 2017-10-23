package de.gishmo.gwt.example.module050201.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.gishmo.gwt.example.module050201.shared.dto.Person;
import de.gishmo.gwt.example.module050201.shared.exception.PersonNotFoundException;

import java.util.List;

@RemoteServiceRelativePath("person")
public interface PersonService
  extends RemoteService {
  
  public Person get(long id) throws PersonNotFoundException;
  
  public List<Person> getAll();

}
