package de.gishmo.module0812.domain.dto.shared.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import de.gishmo.module0812.domain.dto.shared.AbstractDto;

@SuppressWarnings("serial")
@JsonTypeName("Person")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY)
public class Person
  extends AbstractDto
  implements Serializable {

  private long id;
  
  private String name;
  private String firstName;
  
  private Address address;
  
  /* for serialization only */
  public Person() {
    super();
  }

  public Person(long id, String name, String firstName, Address adress) {
    super();
    
    this.id = id;
    this.name = name;
    this.firstName = firstName;
    this.address = adress;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }
}
