import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

      System.out.println("\nDeserialized\n");

    } catch (FileNotFoundException e) {
      System.out.println(e);
    } catch (IOException e){
      System.out.println(e);
    } catch (ClassCastException e){
      System.out.println(e);
    } catch (Exception e){
      System.out.println(e);
    }
  }
}
