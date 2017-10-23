package de.gishmo.gwt.example.module0503.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import de.gishmo.gwt.example.module0503.shared.dto.Person;
import de.gishmo.gwt.example.module0503.shared.dto.PersonSearch;

import java.util.List;

public interface PersonServiceAsync {

  void get(long id,
           AsyncCallback<Person> callback);

  void getAll(AsyncCallback<List<Person>> callback);

  void get(PersonSearch search,
           AsyncCallback<List<Person>> callback);

  void insert(Person person,
              AsyncCallback<Void> callback);

  void update(Person person,
              AsyncCallback<Void> callback);

}
