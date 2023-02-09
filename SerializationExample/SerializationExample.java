import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

class Student implements Serializable {
  private static final long serialVersionUID = 100; 
  // SerialVersionUID is used to ensure that during deserialization the same class (that was used during serialize process) is loaded.  
  // The serialization at runtime associates with each serializable class a version number called a serialVersionUID, which is used during 
  // deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible 
  // with respect to serialization. 

  String firstName;
  // Date dateOfBirth;
  // String dateOfBirth;
  Object dateOfBirth;
  Address address;

  // Student(String firstName, String dateOfBirth, Address address) { //before changing the data type of date of birth from string to date
  // this.firstName = firstName;
  // this.dateOfBirth = dateOfBirth;
  // this.address = address;
  // }

  Student(String firstName, String dateOfBirth, Address address) { //after changing the data type of date of birth from string to date
    this.firstName = firstName;
    this.dateOfBirth = new Date(dateOfBirth);
    this.address = address;
  }

  

  public String toString() {
    return this.firstName + " " + this.dateOfBirth + " " + this.address.city + " " + this.address.country + " "
        + this.address.pinCode + " " + this.address.state;
  }

}

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

class SerializationTest {

  void serialize(String fileName) {
    Student student1 = new Student("Nidhi", "01/01/2002", new Address("indore", "m.p.", 452001, "India"));
    Student student2 = new Student("Nidhi", "01/01/2002", new Address("indore", "m.p.", 452001, "India"));
    Student student3 = new Student("Nidhi", "01/01/2002", new Address("indore", "m.p.", 452001, "India"));
    Student student4 = new Student("Nidhi", "01/01/2002", new Address("indore", "m.p.", 452001, "India"));

    ArrayList<Student> students = new ArrayList<>();
    students.add(student1);
    students.add(student2);
    students.add(student3);
    students.add(student4);

    try {
      FileOutputStream fileOutputStream = new FileOutputStream(fileName);
      ObjectOutputStream objoutputStream = new ObjectOutputStream(fileOutputStream);

      objoutputStream.writeObject(students); // coverting objects to bytes 
      objoutputStream.close();
      fileOutputStream.close();

      System.out.println("Serialized\n");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

}

class DeserializationTest {
  void deserialize(String fileName) {
    try {
      FileInputStream fileInputStream = new FileInputStream(fileName);
      ObjectInputStream objInputStream = new ObjectInputStream(fileInputStream);
      ArrayList<Student> students = (ArrayList<Student>) objInputStream.readObject();
      objInputStream.close();
      fileInputStream.close();

      for (Student student : students) {
        System.out.println(student.toString());
      }

      System.out.println("Deserialized");
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}

public class SerializationExample {
  public static void main(String[] args) {
    try {
      Scanner sc = new Scanner(System.in);

      while (true) {
        System.out.println("Enter 1 to serialize");
        System.out.println("Enter 2 to deserialize");

        int choice = sc.nextInt();

        switch (choice) {
          case 1:
            System.out.println("Enter the file name: ");
            String fileName = sc.next();
            new SerializationTest().serialize(fileName);
            System.out.println("---------------------------\n");
            break;

          case 2:
            System.out.println("Enter the file name: ");
            fileName = sc.next();
            new DeserializationTest().deserialize(fileName);
            System.out.println("---------------------------\n");
            break;

          default:
            System.out.println("---------------------------\n");
            System.out.println("Program terminated successfully.");
            return;
        }
      }

    } catch (Exception e) {
      System.out.println(e);
    }

  }
}
