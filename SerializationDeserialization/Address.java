import java.io.Serializable;

public class Address implements Serializable
{
    String city ;
    String state ;
    Integer pinCode ;
    String country  ;

    // private static final long serialVersionUID = 1l; // Provide a unique Id for sender and Recieve/

    public Address(String city , String state , Integer pinCode , String country )
    {
        this.city = city ;
        this.state = state ;
        this.pinCode = pinCode ;
        this.country = country ;
    }

    public String toString() //to print a meaningfull output
    {
        return "City = " + city + ", State = " + state + ", PinCode = " + pinCode + ", Country = " + country ;
    }
}