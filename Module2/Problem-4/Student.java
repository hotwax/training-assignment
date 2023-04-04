import java.io.Serializable;
import java.util.Date;

class Address implements Serializable{
    public String city;
    public String state;
    public String picode;
    public String country;

    public Address(String city, String state, String picode, String country) {
        this.city = city;
        this.state = state;
        this.picode = picode;
        this.country = country;
    }
}

//Student class is serializable we will be able to write it to a file
public class Student implements Serializable{
    public String name;
    // public String dob;
    // public Date dob;
    public Object dob;
    public Address address; 
    // This is used to identify the version of the class. If the version of the class changes, 
    // then the serialVersionUID also changes. 
    // This is used to check if the class is compatible with the serialized object or not.
    public static final long serialVersionUID = 1L;

    public Student(String name, String dob, Address address) {
        this.name = name;
        this.dob = dob;
        // this.dob = new Date(dob);
        // this.dob = new Date(dob);
        this.address = address;
    }

    public static void main(String[] args){
        
    }
}