import java.io.Serializable;
//Address class Implements Serializable interface
public class Address implements Serializable
{
    // Instance variables for the class.
    private String city;
    private String state;
    private int pinCode;
    private String country;
    
    //Construcotr for Addresss class
    public Address(String city, String state, int pinCode, String country) {
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
        this.country = country;
    }
    //getter methods
    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }
    public int getPinCode() {
        return pinCode;
    }

    public String getCountry() {
        return country;
    }

}
