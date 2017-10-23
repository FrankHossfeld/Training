package de.gishmo.gwt.example.module040101.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import de.gishmo.gwt.example.module040101.shared.dto.Person;

public interface PersonServiceAsync {

  void get(long id, AsyncCallback<Person> callback);

}
