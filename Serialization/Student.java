import java.io.Serializable;
import java.util.Date;

//Student class Implements Serializable interface
public class Student implements Serializable {
    // Unique ID for Serialization and Deserialization
    private static final long serialVersionUID = 1L;

    // Instance variables for the class.
    private String firstName;
    private Object dateOfBirth;
    private Address address;

    // Constructor for Student class
    public Student(String firstName, String dateOfBirth, Address address) {
        this.firstName = firstName;
        this.dateOfBirth = new Date(dateOfBirth);
        this.address = address;
    }

    // getter methods
    public String getFirstName() {
        return firstName;
    }

    public Object getDateOfBirth() {
        return dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

}
