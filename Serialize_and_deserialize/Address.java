package Serialize_and_deserialize;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Address implements Serializable {

    private String city;
    private String state;
    private int pinCode;
    private String country;

    public Address(String city, String state, int pinCode, String country) {
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
        this.country = country;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    //  city the city to set
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the pinCode
     */
    public int getPinCode() {
        return pinCode;
    }

    /**
     * @param pinCode the pinCode to set
     */
    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    public String toString() {
        return city + " " + state + " " + pinCode + " " + country;
    }

}
