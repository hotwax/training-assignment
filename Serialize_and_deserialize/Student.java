package Serialize_and_deserialize;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {

    private static final long serialVersionUID = 2L;
    private String firstName;
    private Date dateOfBirth;
    private Address address;
    private transient int version = 3;

    public Student(String name, String dob, Address address) {
        this.firstName = name;
        this.dateOfBirth = new Date(dob);
        this.address = address;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the dateOfBirth
     */
    public String getDateOfBirth() {
        return dateOfBirth.toString();
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = new Date(dateOfBirth);
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    public String toString() {
        return firstName + " " + dateOfBirth + " " + address;
    }

}
