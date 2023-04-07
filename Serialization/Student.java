import java.io.*;
import java.util.Date;



class Address implements Serializable {

    private String city;
    private String state;
    private Integer pinCode;
    private String country;

    public Address(String city, String state, Integer pinCode, String country) {
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public Integer getPinCode() {
        return pinCode;
    }

    public String getCountry() {
        return country;
    }
}

class Student implements Serializable {



    /* In the Student class, a serialVersionID can be added as a private static final long variable,
     which is used to identify the version of the class during serialization and deserialization. 
     Whenever a change is made to the class, this ID should be updated. By doing so we are able to deserialize both the version of ouptput
     file output1.ser (serialized using String type of DOB) and output2.ser(serialized using data type Data) */


    /*   Object type in dateOfBirth in the Student class is to allow for flexibility in the type of date format that can be used,
    In Java, the Object class is a root class for all classes, meaning that every class in Java is either directly or indirectly
      derived from the Object class. As a result, any data type in Java can be cast to an Object type. */

    

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