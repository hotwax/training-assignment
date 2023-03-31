
import java.io.Serializable;
import java.text.SimpleDateFormat;

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
    
    // Student(String firstName, String dateOfBirth, Address address) { //before changing the data type of date of birth from string to date
        // this.firstName = firstName;
        // this.dateOfBirth = dateOfBirth;
        // this.address = address;
    // }

    public String toString() // to print a meaningful output
    {
        return " Name = " + firstName + "\n DateOfBirth = " + dateOfBirth + "\n Address = " + address + "\n";
    }

    public static void main(String[] args) {
        
    }
}