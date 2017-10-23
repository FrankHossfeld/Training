package de.gishmo.gwt.example.module0503.shared.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Person
  implements IsSerializable {

  private long id;

  private String name;
  private String firstName;

  private Address address;

  /* for serialization only */
  @SuppressWarnings("unused")
  private Person() {
    super();
  }

  public Person(long id,
                String name,
                String firstName,
                Address adress) {
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
