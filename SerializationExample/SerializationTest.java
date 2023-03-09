import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


class SerializationTest {

  void serialize(String fileName) {
    Student student1 = new Student("Ram", "01/01/2002", new Address("indore", "m.p.", 452001, "India"));
    Student student2 = new Student("Ram", "01/01/2002", new Address("indore", "m.p.", 452001, "India"));
    Student student3 = new Student("Ram", "01/01/2002", new Address("indore", "m.p.", 452001, "India"));
    Student student4 = new Student("Ram", "01/01/2002", new Address("indore", "m.p.", 452001, "India"));

    ArrayList<Student> students = new ArrayList<>();
    students.add(student1);
    students.add(student2);
    students.add(student3);
    students.add(student4);

    try {
      FileOutputStream fileOutputStream = new FileOutputStream(fileName);
      ObjectOutputStream objoutputStream = new ObjectOutputStream(fileOutputStream);

      objoutputStream.writeObject(students); // converting objects to bytes 
      objoutputStream.close();
      fileOutputStream.close();

      System.out.println("\nSerialized\n");
      
    } catch (FileNotFoundException e) {
      System.out.println(e);
    } catch (IOException e){
      System.out.println(e);
    } catch (Exception e){
      System.out.println(e);
    }
  }

}
