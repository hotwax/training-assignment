import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

// A address class --

class Address implements Serializable {
    String city;
    String state;
    String country;
    int pinCode;

    // Getters and Setters

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    // Constructor

    Address(String city, String state, String country, int pinCode) {
        this.city = city;
        this.country = country;
        this.state = state;
        this.pinCode = pinCode;
    }

}

// A Student class --

class Student implements Serializable {

    // Solution for Invalid Class Exception
    private static final long serialVersionUID = 652;

    String firstName;
    // Initial
    String dateOfBirth;
    // After Change
    // Date dateOfBirth;
    Address address;

    // Constructor

    Student(String firstName, Address address) {
        this.firstName = firstName;
        this.address = address;
    }

    // making changes after serialization

    // Student(Date dateOfBirth) {
    // this.dateOfBirth = dateOfBirth;
    // }

}

// class for creating 4 objects and has serialization method

class Test {
    Student stu1;
    Student stu2;
    Student stu3;
    Student stu4;

    Test() {
        this.setStu1();
        this.setStu2();
        this.setStu3();
        this.setStu4();
    }

    // Functions for initializing Data Members

    public void setStu1() {

        Address newAddress = new Address("Bamhori", "Madhya Pradesh", "India", 464672);

        this.stu1 = new Student("Sanskar", newAddress);
    }

    public void setStu2() {

        Address newAddress = new Address("Bhopal", "Madhya Pradesh", "India", 464600);

        this.stu2 = new Student("Shubham", newAddress);
    }

    public void setStu3() {

        Address newAddress = new Address("Shamgarh", "Madhya Pradesh", "India", 460072);

        this.stu3 = new Student("Pratham", newAddress);
    }

    public void setStu4() {

        Address newAddress = new Address("Indore", "Madhya Pradesh", "India", 452010);

        this.stu4 = new Student("Sumit", newAddress);
    }

    public void runTest(String fileName) {

        // Creating a list and adding objects into it

        List<Student> list = new ArrayList<Student>();

        list.add(this.stu1);
        list.add(this.stu2);
        list.add(this.stu3);
        list.add(this.stu4);

        // Serialization
        try {
            // Saving of object in a file
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(list);

            out.close();
            file.close();

            System.out.println("Object has been serialized");

        }

        catch (Exception ex) {
            System.out.println(ex);
        }

    }
}

public class SerializationTest {
    public static void main(String args[]) {
        Test test = new Test();

        Scanner input = new Scanner(System.in); // Create a Scanner object
        System.out.println("Enter The Filename : ");

        String fileName = input.nextLine(); // Read user input

        // calling the test method of test class which serializes the objects

        test.runTest(fileName);

    }
}