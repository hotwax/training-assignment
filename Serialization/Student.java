import java.io.*;
import java.util.Date;



class Student implements Serializable {




    /* In the Student class, a serialVersionID can be added as a private static final long variable,
     which is used to identify the version of the class during serialization and deserialization. 
     Whenever a change is made to the class, this ID should be updated. By doing so we are able to deserialize both the version of ouptput
     file output1.ser (serialized using String type of DOB) and output2.ser(serialized using Date type as objec) */


    private  static final long serialVersionUID = 1L;

    private String firstName;
    private Object dateOfBirth;
    private Address address;

    public Student(String firstName, String dateOfBirth, Address address) {
        this.firstName = firstName;
        this.dateOfBirth = new Date(dateOfBirth);
        // this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public Object getDateOfBirth() {
        return dateOfBirth;
    }

    // public String getDateOfBirth() {
    //     return dateOfBirth;
    // }

    // public Date getDateOfBirth() {
    //     return dateOfBirth;
    // }

    public Address getAddress() {
        return address;
    }

   
}