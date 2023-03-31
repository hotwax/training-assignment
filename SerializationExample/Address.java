import java.io.Serializable;

class Address implements Serializable {
  String city;
  String state;
  int pinCode;
  String country;

  Address(String city, String state, int pinCode, String country) {
    this.city = city;
    this.state = state;
    this.pinCode = pinCode;
    this.country = country;
  }
}
