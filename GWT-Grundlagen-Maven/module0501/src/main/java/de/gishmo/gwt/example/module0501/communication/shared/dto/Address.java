package de.gishmo.gwt.example.module0501.communication.shared.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Address
  implements IsSerializable {

  private long id;

  private String street;
  private String zip;
  private String city;

  /* for serialization only */
  @SuppressWarnings("unused")
  private Address() {
    super();
  }

  public Address(long id,
                 String street,
                 String zip,
                 String city) {
    super();

    this.id = id;
    this.street = street;
    this.zip = zip;
    this.city = city;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
