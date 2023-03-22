package Program4;

import java.io.Serializable;

public class Address implements Serializable {
    public String city; //Variable to store city.
    public String state; // Variable to store state.
    public int pinCode; // Integer to store the pincode

    public String country; //Variable to store the country
    // Constructor
    public Address(String city, String state, int pinCode, String country) {
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
        this.country = country;
    }
    // Overriding the toString method to print Object to String
    @Override
    public String toString() {
        return city+" "+ state +" "+ pinCode +" "+ country;
    }
}