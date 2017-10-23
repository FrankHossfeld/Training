package de.gishmo.gwt.example.module0503.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.gishmo.gwt.example.module0503.shared.dto.Person;
import de.gishmo.gwt.example.module0503.shared.dto.PersonSearch;
import de.gishmo.gwt.example.module0503.shared.exception.PersonException;
import de.gishmo.gwt.example.module0503.shared.exception.PersonNotFoundException;

import java.util.List;

@RemoteServiceRelativePath("person")
public interface PersonService
  extends RemoteService {

  Person get(long id)
    throws PersonNotFoundException;

  List<Person> getAll();

  List<Person> get(PersonSearch search);

  void insert(Person person)
    throws PersonException;

  void update(Person person)
    throws PersonException;

}
