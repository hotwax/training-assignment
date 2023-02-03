import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

class Student implements Serializable {
  private static final long serialVersionUID = 2L;
  String firstName;
  Date dateOfBirth;
  // String dateOfBirth;
  Address address;
  

  Student(String firstName, String dateOfBirth, Address address) {
    this.firstName = firstName;
    this.dateOfBirth = new Date(dateOfBirth);
    this.address = address;
  }

  // Student(String firstName, String dateOfBirth, Address address) {
  //   this.firstName = firstName;
  //   this.dateOfBirth = dateOfBirth;
  //   this.address = address;
  // }

  public String toString(){
    return this.firstName+" "+this.dateOfBirth+" "+this.address.city+" "+this.address.country+" "+this.address.pinCode+" "+this.address.state; 
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
      FileOutputStream fOutputStream = new FileOutputStream(
          "D:\\Nidhi pal\\hotwax\\training-assignment\\module2\\problem4\\" + fileName);
      ObjectOutputStream objoutputStream = new ObjectOutputStream(fOutputStream);

      objoutputStream.writeObject(students);
      objoutputStream.close();

      System.out.println("Serialized");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

}

class DeserializationTest{
  void deserialize(String fileName){
     try {
       FileInputStream fileInputStream = new FileInputStream("D:\\Nidhi pal\\hotwax\\training-assignment\\module2\\problem4\\" + fileName);
       ObjectInputStream objInputStream = new ObjectInputStream(fileInputStream);
       ArrayList<Student> students = (ArrayList<Student>)objInputStream.readObject();
       objInputStream.close();
       for (Student student : students) {
         System.out.println(student.toString());
       }

       System.out.println("Deserialized");
     } catch (Exception e) {
       System.out.println(e);
     }
  }
}

public class Problem4{
  public static void main(String[] args) {
    while (true) {
      System.out.println("Enter 1 to serialize");
      System.out.println("Enter 2 to deserialize");

      Scanner sc = new Scanner(System.in);
      int choice = sc.nextInt();

      switch (choice) {
        case 1:
          System.out.println("Enter file name: ");
          String fileName = sc.next();
          new SerializationTest().serialize(fileName);
          System.out.println("---------------------------\n");
          break;
        
        case 2:
          System.out.println("Enter file name: ");
          fileName = sc.next();
          new DeserializationTest().deserialize(fileName);
          System.out.println("---------------------------\n");
          break;

        default:
          break;
      }
    }
  }
}

