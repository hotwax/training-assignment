import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

// A address class --

class Address implements Serializable {

    private static final long serialVersionUID = 652;

    private String city;
    private String state;
    private String country;
    private int pinCode;

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

    /*
     * To explicitly declare and initialize serialVersionUID .
     * If a class does not explicitly define a private static final long
     * serialVersionUID
     * in the code it will be autogenerated, and there is no guarantee that
     * different machines
     * will generate the same id; it looks like that is exactly what happened. Also
     * if the
     * classes are different in any way (using different versions of the class) the
     * autogenerated serialVersionUIDs will also be different.
     */

    // private static final long serialVersionUID = 652;

    String firstName;

    // Initial
    // String dateOfBirth;

    // After Change
    Date dateOfBirth;

    Address address;

    // iniatilizing simple date object for formatting
    SimpleDateFormat simpleDate = new SimpleDateFormat("dd/mm/yyyy"); // for date formatting

    // Constructor

    Student(String firstName, String dateOfBirth, Address address) {
        try {

            this.firstName = firstName;
            this.address = address;
            // After Change
            this.dateOfBirth = simpleDate.parse(dateOfBirth);

            // Initial
            // this.dateOfBirth = dateOfBirth;
        } catch (Exception exp) {
            System.out.println(exp);
        }
    }

    // for printing purpose

    public String toString() {
        return "First Name : " + this.firstName + ", Address : " + this.address.getCity() + ", DOB : "
                + this.dateOfBirth;
    }

}

// class for creating 4 objects and has serialization method

class TestForSerailization {
    Student stu1;
    Student stu2;
    Student stu3;
    Student stu4;

    TestForSerailization() {
        this.setStu1();
        this.setStu2();
        this.setStu3();
        this.setStu4();
    }

    // Functions for initializing Data Members

    public void setStu1() {

        Address newAddress = new Address("Bamhori", "Madhya Pradesh", "India", 464672);

        this.stu1 = new Student("Sanskar", "19/03/2002", newAddress);
    }

    public void setStu2() {

        Address newAddress = new Address("Bhopal", "Madhya Pradesh", "India", 464600);

        this.stu2 = new Student("Shubham", "22/03/2002", newAddress);
    }

    public void setStu3() {

        Address newAddress = new Address("Shamgarh", "Madhya Pradesh", "India", 460072);

        this.stu3 = new Student("Pratham", "29/08/2002", newAddress);
    }

    public void setStu4() {

        Address newAddress = new Address("Indore", "Madhya Pradesh", "India", 452010);

        this.stu4 = new Student("Sumit", "28/03/2002", newAddress);
    }

    public void runTest(String fileName) {

        // Creating a list and adding objects into it

        List<Student> studentList = new ArrayList<Student>();

        studentList.add(this.stu1);
        studentList.add(this.stu2);
        studentList.add(this.stu3);
        studentList.add(this.stu4);

        // Serialization
        try {
            // Saving of object in a file
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(studentList);

            out.close();
            file.close();

            System.out.println("Object has been serialized");

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }
}

public class SerializationTest {
    public static void main(String args[]) {

        TestForSerailization test = new TestForSerailization();

        Scanner input = new Scanner(System.in); // Create a Scanner object
        System.out.println("Enter The Filename : ");

        String fileName = input.nextLine(); // Read user input

        // calling the test method of test class which serializes the objects

        test.runTest(fileName);

    }
}