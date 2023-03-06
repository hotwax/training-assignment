import java.io.Serializable;

//Student class Implements Serializable interface
public class Student implements Serializable {
    //Unique ID for Serialization and Deserialization
    private static final long serialVersionUID = 1L;

    // Instance variables for the class.
    private String firstName;
    private String dateOfBirth;
    private Address address;

    //Constructor for Student class
    public Student(String firstName, String dateOfBirth, Address address) {
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }
    //getter methods
    public String getFirstName() {
        return firstName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }
    
}
