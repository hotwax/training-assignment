package Problem4;

import java.io.Serializable;

public class Address implements Serializable {
    // String to store the city
    public String city;
    // String to store the state
    public String state;
    // integer to store the pincode
    public int pinCode;
    //integer to store the country
    public String country;
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
