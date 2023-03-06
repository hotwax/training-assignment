import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SerializeTest {
 ArrayList<Student> studentList;

  SerializeTest() {
    studentList = new ArrayList<>();
  }

//Create the Student Object which all the details
  void createStudentObjects(String name, String dOB, String city, String state, Integer pin, String county) {
    Address address = new Address(city, state, pin, county);
    Student student = new Student(name, dOB, address);
    studentList.add(student);
  }

  public static void main(String args[]) {
    SerializeTest serializationTest = new SerializeTest();
    serializationTest.createStudentObjects("Gourav", "20/12/2002", "Indore", "Madhya Pradesh", 452010, "India");
    serializationTest.createStudentObjects("Kunal", "28/02/2003", "Dewas", "Madhya Pradesh", 452001, "India");
    serializationTest.createStudentObjects("Yash", "01/03/2000", "Toronto", "United States", 452100, "India");
    serializationTest.createStudentObjects("Prakhar", "18/09/2001", "Switzerland", "Madhya Pradesh", 452000, "India");
    try {
      String filename=args[0];
      FileOutputStream file = new FileOutputStream( filename);
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
      System.out.println("Generate " + filename);

    } catch (IOException e) {
      System.out.println(e);
    }

  }

}
