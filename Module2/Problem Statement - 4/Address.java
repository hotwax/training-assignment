import java.io.*;
public class Address implements Serializable{
    String city;
    String State;
    int pinCode;
    String country;
    Address(String city, String state, int pinCode, String country){
        this.city = city;
        this.State = state;
        this.pinCode = pinCode;
        this.country = country;
    }
}