

import java.io.Serializable;
import java.util.Date;
// Class Address
class Address implements Serializable {
  private String city;
  private String state;
  private Integer pinCode;
  private String country;
// constructor
  Address(String city, String state, Integer pinCode, String country) {
    this.city = city;
    this.state = state;
    this.city = city;
    this.pinCode = pinCode;
    this.country = country;
  }
// Getter functions
  String getCity() {
    return this.city;
  }

  String getState() {
    return this.state;
  }

  Integer getPincode() {
    return this.pinCode;
  }

  String getCountry() {
    return this.country;
  }

}

public class Student implements Serializable {

  private static final long serialVersionUID = 1L;
  private String firstName;
  private Object dateOfBirth;
  private Address address;

  Student(String name, String dOB, Address add) {
    this.firstName = name;
    this.dateOfBirth = new Date(dOB);
  //  / this.dateOfBirth=dOB;
    this.address = add;
  }

  String getName() {
    return this.firstName;
  }

  Object getDob() {
    return dateOfBirth;
  }

  Address getAddress() {
    return this.address;
  }


  public static void main(String args[]) {

  }

}