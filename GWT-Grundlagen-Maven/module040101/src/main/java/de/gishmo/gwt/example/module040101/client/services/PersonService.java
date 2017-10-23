package de.gishmo.gwt.example.module040101.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.gishmo.gwt.example.module040101.shared.dto.Person;
import de.gishmo.gwt.example.module040101.shared.exception.PersonNotFoundException;

@RemoteServiceRelativePath("person")
public interface PersonService
  extends RemoteService {
  
  Person get(long id)
    throws PersonNotFoundException;

}
