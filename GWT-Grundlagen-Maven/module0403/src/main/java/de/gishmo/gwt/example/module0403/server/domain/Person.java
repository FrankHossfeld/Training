package de.gishmo.gwt.example.module0403.server.domain;

import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.gwt.user.client.rpc.IsSerializable;

import de.gishmo.gwt.example.module0403.server.PersonService;

/**
 * Hold relevant data for Person.
 */
public class Person
  implements IsSerializable {
  
  /**
   * New instances could be created on the client, but it's a better demo to
   * send back a Person with a bunch of dummy data.
   */
  public static Person createPerson() {
    return PersonService.createPerson();
  }

  /**
   * The RequestFactory requires a static finder method for each proxied type.
   * Soon it should allow you to customize how instances are found.
   */
  public static Person findPerson(String id) {
    return PersonService.findById(id);
  }

  /**
   * The RequestFactory requires a static finder method for each proxied type.
   * Soon it should allow you to customize how instances are found.
   */
  public static Person findById(String id) {
    return PersonService.findById(id);
  }

  /**
   * The RequestFactory requires a static finder method for each proxied type.
   * Soon it should allow you to customize how instances are found.
   */
  public static List<Person> getAll() {
    return PersonService.getAll();
  }

  private String id;

  @NotNull
  @DecimalMin("0")
  private Integer version = 0;
  
  @NotNull
  @Size(min = 1, max = 32)
  private String name;
  @NotNull
  @Size(min = 1, max = 32)
  private String firstName;
  
  @NotNull
  private Address address;
  
  public Person() {
    super();
    this.address = new Address();
  }

  public Person(String id,
                String name,
                String firstName,
                Address address) {
    super();
    
    this.id = id;
    this.name = name;
    this.firstName = firstName;
    if (address == null) {
      this.address = new Address();
    } else {
      this.address = address;
    }
  }

  protected Person(Person copyFrom) {
    this.address = new Address();
    copyFrom(copyFrom);
  }

  public void copyFrom(Person copyFrom) {
    address.copyFrom(copyFrom.address);
    firstName = copyFrom.firstName;
    name = copyFrom.name;
    id = copyFrom.id;
    version = copyFrom.version;
  }

  public Person makeCopy() {
    return new Person(this);
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
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

  public void setAddress(Address adress) {
    this.address = adress;
  }
}
