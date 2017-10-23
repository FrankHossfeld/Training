package de.gishmo.gwt.example.module050201.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import de.gishmo.gwt.example.module050201.shared.dto.Person;

import java.util.List;

public interface PersonServiceAsync {

  void get(long id,
           AsyncCallback<Person> callback);

  void getAll(AsyncCallback<List<Person>> callback);

}
