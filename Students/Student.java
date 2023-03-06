package Students;

import java.io.Serializable;
import java.util.Date;

class Address implements Serializable {
  private String city;
  private String state;
  private Integer pinCode;
  private String country;

  Address(String city, String state, Integer pinCode, String country) {
    this.city = city;
    this.state = state;
    this.city = city;
    this.pinCode = pinCode;
    this.country = country;
  }
//get the City
  String getCity() {
    return this.city;
  }
//get the State
  String getState() {
    return this.state;
  }
//get the Pincode
  Integer getPincode() {
    return this.pinCode;
  }
//get the Country
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
    this.address = add;
  }
//get the Name
  String getName() {
    return this.firstName;
  }
//get the Date of Birth
  Object getDob() {
    return dateOfBirth;
  }
//get the Address
  Address getAddress() {
    return this.address;
  }

  // String getDob1(){
  //   return this.dateOfBirth;
  // }

  public static void main(String args[]) {

  }

}
