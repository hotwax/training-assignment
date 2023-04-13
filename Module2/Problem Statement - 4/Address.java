import java.io.Serializable ;
public class Address implements Serializable{
    String city;
    String State;
    int pinCode;
    String country;
    /*
     *  This is a constructor of Address class.
     * It is parameterised constructor which takes city name, state name, pincode, and country name as an argument
     * while creating an instance of this class.
    */
    Address(String city, String state, int pinCode, String country){
        this.city = city;
        this.State = state;
        this.pinCode = pinCode;
        this.country = country;
    }
}