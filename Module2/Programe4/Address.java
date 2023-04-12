public class Address implements java.io.Serializable {

    // Declare a serial version UID to ensure proper serialization of objects
    private static final long serialVersionUID = 1L;
    public String city;
    public String state;
    public int pincode;
    public String country;

    // Constructor to initialize Address object with given city, state, pincode, and
    // country
    public Address(String city, String state, int pincode, String country) {
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.country = country;
    }

    // toString method to return a string representation of the Address object
    public String toString() {
        return city + " " + state + " " + pincode + " " + country;
    }

}
