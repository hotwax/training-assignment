import java.io.*;
import java.util.Date;



class Address implements Serializable {

    private  static final long serialVersionUID = 10L;


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

    public Address getAddress() {
        return address;
    }

   
}