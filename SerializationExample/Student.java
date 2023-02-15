import java.io.Serializable;
import java.util.Date;


class Student implements Serializable {
  private static final long serialVersionUID = 100; 
  // SerialVersionUID is used to ensure that during deserialization the same class (that was used during serialize process) is loaded.  
  // The serialization at runtime associates with each serializable class a version number called a serialVersionUID, which is used during 
  // deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible 
  // with respect to serialization. 

  String firstName;
  // Date dateOfBirth;
  // String dateOfBirth;
  Object dateOfBirth;
  Address address;

  // Student(String firstName, String dateOfBirth, Address address) { //before changing the data type of date of birth from string to date
  // this.firstName = firstName;
  // this.dateOfBirth = dateOfBirth;
  // this.address = address;
  // }

  Student(String firstName, String dateOfBirth, Address address) { //after changing the data type of date of birth from string to date
    this.firstName = firstName;
    this.dateOfBirth = new Date(dateOfBirth);
    this.address = address;
  }

  

  public String toString() {
    return this.firstName + " " + this.dateOfBirth + " " + this.address.city + " " + this.address.country + " "
        + this.address.pinCode + " " + this.address.state;
  }

}
