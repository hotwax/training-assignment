package SerializeDeserialize;

import java.io.Serializable;

// Serializable class for representing address
public class Address implements Serializable {
  private String city;
  private String state;
  private Integer pinCode;
  private String country;

  // Constructor to initialize a Address object
  Address(String city, String state, Integer pinCode, String country) {
    this.city = city;
    this.state = state;
    this.city = city;
    this.pinCode = pinCode;
    this.country = country;
  }

  // get the City
  String getCity() {
    return this.city;
  }

  // get the State
  String getState() {
    return this.state;
  }

  // get the Pincode
  Integer getPincode() {
    return this.pinCode;
  }

  // get the Country
  String getCountry() {
    return this.country;
  }
}