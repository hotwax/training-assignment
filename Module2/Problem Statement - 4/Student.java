import java.util.Date;
import java.io.*;

public class Student implements Serializable {
    String firstName;
    Object dateOfBirth;
    // This is an instance of address class
    Address address;

    // This is a parameterised constructor which takes fileName, date and an object of address class
    // It will automatically get invoked.
    Student(String firstName,Date date, Address address){
        this.firstName = firstName;
        this.dateOfBirth = date;
        this.address = address;
    }
}