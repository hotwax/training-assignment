import java.io.Serializable;

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

public class Student implements Serializable{
    public String name;
    public String dob;
    public Address address;

    public Student(String name, String dob, Address address) {
        this.name = name;
        this.dob = dob;
        this.address = address;
    }

    public static void main(String[] args){
        
    }
}
