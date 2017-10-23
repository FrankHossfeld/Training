package de.gishmo.gwt.example.module0402.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import de.gishmo.gwt.example.module0402.shared.dto.Person;

import java.util.List;

public interface PersonServiceAsync {

  void get(long id, AsyncCallback<Person> callback);

  void getAll(AsyncCallback<List<Person>> async);
}
