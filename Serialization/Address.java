import java.io.Serializable;

class Address implements Serializable{



    private String city;
    private String state;
    private Integer pinCode;
    private String country;

    public Address(String city, String state, Integer pinCode, String country) {
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

    public Integer getPinCode() {
        return pinCode;
    }

    public String getCountry() {
        return country;
    }
}