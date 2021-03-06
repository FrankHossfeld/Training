package de.gishmo.module0812.domain.dto.shared.transport.response;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import de.gishmo.module0812.domain.dto.shared.model.Person;

@JsonTypeName("PersonGetResponse")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY)
public class PersonGetResponse extends AbstractResponse {

	private Person person;

	public PersonGetResponse() {
		super();
	}

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

}
