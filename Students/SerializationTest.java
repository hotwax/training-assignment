package Students;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
public class SerializationTest {
  ArrayList<Student> studentList;

  SerializationTest() {
    studentList = new ArrayList<>();
  }

  // Create the Student Object which all the details
  void createStudentObjects(String name, String dOB, String city, String state, Integer pin, String county) {
    Address address = new Address(city, state, pin, county);
    Student student = new Student(name, dOB, address);
    studentList.add(student);
  }

  public static void main(String args[]) {
    SerializationTest serializationTest = new SerializationTest();
    // create the List of Student Object
    serializationTest.createStudentObjects("Gourav", "12/20/2002", "Indore", "Madhya Pradesh", 452010, "India");
    serializationTest.createStudentObjects("Kunal", "02/28/2003", "Bhopal", "Madhya Pradesh", 452001, "India");
    serializationTest.createStudentObjects("Yash", "03/01/2000", "Indore", "Madhya Pradesh", 452100, "India");
    serializationTest.createStudentObjects("Niharika", "01/02/2001", "Ujjain", "Madhya Pradesh", 452000, "India");
    try {
      FileOutputStream file = new FileOutputStream("Students/" +args[0]);
      ObjectOutputStream obj = new ObjectOutputStream(file);

      obj.writeObject(serializationTest.studentList);

      obj.close();
      file.close();

      System.out.println("Students List Before Serialization");
      for (Student student : serializationTest.studentList) {
        System.out.println();
        System.out.println("Student Name - " + student.getName());
        System.out.println("Student Date Of Birth - " + student.getDob());
        System.out.println(
            "Student Adress - " + student.getAddress().getCity() + "," + student.getAddress().getPincode() + ","
                + student.getAddress().getState() + "," + student.getAddress().getCountry());
        System.out.println();
      }
      System.out.println("Serialize Successfully");
      System.out.println("Generate " + args[0]);

    } catch (IOException exception) {
      System.out.println(exception);
    }

  }

}
