import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class DeserializationTest {
    public static void main(String[] args) {
        // ArrayList to store the deserialized objects
        ArrayList<Student> studentList = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ProblemStatement-4/Output1.ser"))) {
            studentList = (ArrayList<Student>) ois.readObject();

            // Print out the details of each student
            for (Student student : studentList) {
                System.out.print(student.getFirstName() + ",");
                System.out.print(student.getDateOfBirth() + ",");
                System.out.print(student.getAddress().getCity() + ",");
                System.out.print(student.getAddress().getState() + ",");
                System.out.print(student.getAddress().getPinCode() + ",");
                System.out.print(student.getAddress().getCountry());
                System.out.println();
            }
        } catch (IOException exception)
         {
            System.out.println("File not Found");
            exception.printStackTrace();
        }
        catch (ClassNotFoundException exception){
            exception.printStackTrace();
        } catch (ClassCastException exception) {
            exception.printStackTrace();
        }
    }
}
