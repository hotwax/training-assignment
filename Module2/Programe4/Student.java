
// Importing the required package
import java.util.Date;

// Defining a public class called Student which implements the java.io.Serializable interface
public class Student implements java.io.Serializable {

    // Declaring a static final variable serialVersionUID
    private static final long serialVersionUID = 1L;

    // Declaring instance variables
    public String firstName;
    public Object dateofBirth;
    // public String dateofbirth;
    // public Date dateofbirth;
    Address address;

    // Defining a constructor
    public Student(String firstname, String dateOfBirth, Address address) {
        // Initializing instance variables
        this.firstName = firstname;
        // this.dateofbirth = new Date(dateofbirth);
        this.dateofBirth = dateOfBirth;
        this.address = address;
    }

    // Overriding the toString() method to return the string representation of the
    // Student object
    public String toString() {
        return firstName + " " + dateofBirth + " " + address.toString();
    }

}
