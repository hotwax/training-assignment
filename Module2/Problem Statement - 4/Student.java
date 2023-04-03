import java.util.Date;
import java.io.*;

public class Student implements Serializable {
    String firstName;
    Object dateOfBirth;
    Address address;
    Student(String firstName,Date date, Address address){
        this.firstName = firstName;
        this.dateOfBirth = date;
        this.address = address;
    }
}