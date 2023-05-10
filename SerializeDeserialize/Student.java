package SerializeDeserialize;
import java.io.Serializable;
import java.util.Date;

// Serializable class for representing a student
public class Student implements Serializable {
  private static final long serialVersionUID = 1L;
  private String firstName;
  private Object dateOfBirth;
  private Address address;
 
  // Constructor to initialize a Student object 
  Student(String name, String dOB, Address add) {
    this.firstName = name;
    this.dateOfBirth = new Date(dOB); // The date of birth is stored as a Date object
    this.address = add;
  }

  // Method to get the first name of the student
  String getName() {
    return this.firstName;
  }

  // Method to get the date of birth of the student
  Object getDob() {
    return dateOfBirth; // Returns the date of birth object
  }
   
  // Method to get the address of the student
  Address getAddress() {
    return this.address;
  }
}
