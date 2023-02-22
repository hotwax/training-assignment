
import java.io.Serializable;
import java.text.SimpleDateFormat;

class Address implements Serializable
{
    String city ;
    String state ;
    Integer pinCode ;
    String country  ;

    // private static final long serialVersionUID = 1l; // Provide a unique Id for sender and Reciever

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
public class Student implements Serializable
{
    public String firstName ;
    // public String dateOfBirth ; // Initially dateOfBirth was string 
    public Object dateOfBirth ; // After Error 
    public Address address ;


    SimpleDateFormat simpleDate = new SimpleDateFormat("dd/mm/yyyy"); // for date formatting

    public Student(String firstName, String dateOfBirth, Address address) throws Exception //constructor to initialize
    {
        this.firstName = firstName;
        this.dateOfBirth = simpleDate.parse(dateOfBirth);
        this.address = address;
    }

    public String toString() // to print a meaningful output
    {
        return " Name = " + firstName + "\n DateOfBirth = " + dateOfBirth + "\n Address = " + address + "\n";
    }

    public static void main(String[] args) {
        
    }
}