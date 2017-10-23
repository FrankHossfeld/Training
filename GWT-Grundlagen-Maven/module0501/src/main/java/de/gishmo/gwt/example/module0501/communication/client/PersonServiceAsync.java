package de.gishmo.gwt.example.module0501.communication.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import de.gishmo.gwt.example.module0501.communication.shared.dto.Person;

import java.util.List;

public interface PersonServiceAsync {

  void get(long id, AsyncCallback<Person> callback);

  void getAll(AsyncCallback<List<Person>> callback);

}
