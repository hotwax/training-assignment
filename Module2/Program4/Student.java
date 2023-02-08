package Program4;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {
    // this variable is used for determining the class while deserializing.
    private  static final long serialVersionUID = 10L;
    public String firstName;
//    public String dateOfBirth;
    public Object dateOfBirth; //object
    Address address;
    // constructor
    public Student(String firstName, String dateOfBirth, Address address) {
        this.firstName = firstName;
        this.dateOfBirth = new Date(dateOfBirth); //new Date(dateOfBirth)
        this.address = address;
    }

    // Overriding the to String method to print the Object in string format
    @Override
    public String toString() {
        return firstName +" " + dateOfBirth+" "+ address.toString() ;
    }
}