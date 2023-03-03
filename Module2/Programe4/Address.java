
public class Address implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public String city;
    public String state;
    public int pincode;
    public String country;

    public Address(String city, String state, int pincode, String country) {
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.country = country;
    }

    public String tostring() {
        return city + " " + state + " " + pincode + " " + country;
    }

}
