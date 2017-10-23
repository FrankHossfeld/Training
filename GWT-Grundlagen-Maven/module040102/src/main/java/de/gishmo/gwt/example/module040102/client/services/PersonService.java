package de.gishmo.gwt.example.module040102.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.gishmo.gwt.example.module040102.shared.dto.Person;
import de.gishmo.gwt.example.module040102.shared.exception.PersonNotFoundException;

import java.util.List;

@RemoteServiceRelativePath("person")
public interface PersonService
  extends RemoteService {
  
  Person get(long id)
    throws PersonNotFoundException;

  List<Person> getAll();

}
