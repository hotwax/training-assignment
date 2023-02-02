package Problem4;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {
    // serialVersion for determining the class while deserializing
    private  static final long serialVersionUID = 10L;
    // first name
    public String firstName;
//    public String dateOfBirth;
    public Date dateOfBirth;
    // address
    Address address;
    // constructor
    public Student(String firstName, Date dateOfBirth, Address address) {
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    // Overriding the to String method to print the Object in string format
    @Override
    public String toString() {
        return firstName +" " + dateOfBirth+" "+ address.toString() ;
    }
}
