import java.io.Serializable;

public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
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
 
