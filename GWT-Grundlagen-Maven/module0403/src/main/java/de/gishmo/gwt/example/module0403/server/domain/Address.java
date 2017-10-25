package de.gishmo.gwt.example.module0403.server.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Address
  implements IsSerializable {

  private String addressId;
  
  @NotNull
  @Size(min = 1, max = 32)
  private String street;
  @NotNull
  @Size(min = 5, max = 5)
  @Pattern(regexp = "\\d{5}")
  private String zip;
  @NotNull
  @Size(min = 1, max = 32)
  private String city;
  
  public Address() {
    super();
  }

  public Address(String id,
                 String street,
                 String zip,
                 String city) {
    super();
  
    this.addressId = id;
    this.street = street;
    this.zip = zip;
    this.city = city;
  }

  private Address(Address copyFrom) {
    copyFrom(copyFrom);
  }

  public void copyFrom(Address copyFrom) {
    city = copyFrom.city;
    addressId = copyFrom.addressId;
    street = copyFrom.street;
    zip = copyFrom.zip;
  }

  public Address makeCopy() {
    return new Address(this);
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

  public String getAddressId() {
    return addressId;
  }

  public void setAddressId(String id) {
    this.addressId = id;
  }
}
