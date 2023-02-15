import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

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
