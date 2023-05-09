package SerializeDeserialize;

//Import neccessary packages
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SerializationTest {
  ArrayList<Student> studentList;

  // Constructor to initialize the studentList ArrayList
  SerializationTest() {
    studentList = new ArrayList<>();
  }

  // Method to create a Student object with the given details and add it to the
  // studentList
  void createStudentObjects(String name, String dOB, String city, String state, Integer pin, String county) {
    Address address = new Address(city, state, pin, county);
    Student student = new Student(name, dOB, address);
    studentList.add(student);
  }

  public static void main(String args[]) {
    SerializationTest serializationTest = new SerializationTest();

    // Create Student objects and add them to the studentList
    serializationTest.createStudentObjects("Gourav", "12/20/2002", "Indore", "Madhya Pradesh", 452010, "India");
    serializationTest.createStudentObjects("Kunal", "02/28/2003", "Bhopal", "Madhya Pradesh", 452001, "India");
    serializationTest.createStudentObjects("Yash", "03/01/2000", "Indore", "Madhya Pradesh", 452100, "India");
    serializationTest.createStudentObjects("Niharika", "01/02/2001", "Ujjain", "Madhya Pradesh", 452000, "India");

    try {
      // Create a file output stream with the given filename in the Students directory
      FileOutputStream file = new FileOutputStream("SerializeDeserialize/"+args[0]);

      // Create an ObjectOutputStream to write the studentList to the file
      ObjectOutputStream obj = new ObjectOutputStream(file);

      // Write the studentList to the file
      obj.writeObject(serializationTest.studentList);

      // Close the ObjectOutputStream and FileOutputStream
      obj.close();
      file.close();

      // Print the studentList before serialization
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

      // Print a success message along with the filename of the serialized object
      System.out.println("Serialize Successfully");
      System.out.println("Generate " + args[0]);
    } catch (IOException exception) {
      // Handle any IOException that may occur during serialization
      System.out.println(exception);
    }
  }
}
