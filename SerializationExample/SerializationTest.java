import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


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
