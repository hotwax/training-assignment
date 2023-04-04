import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.io.IOException;

public class DeserializationTest {
    public static void main(String[] args) {
        try {

            // FileInputStream provides the functionality to read the data from a file.
            FileInputStream fileIn = new FileInputStream("Serialization/src/output1.ser");

            // ObjectInputStream is used to read the object from the file.
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList<Student> students = (ArrayList<Student>) in.readObject();

            // ArrayList students = (ArrayList) object.readObject();
            in.close();
            fileIn.close();
            if (students.size() > 1) {
                for (Student student : students) {
                    System.out.println("Name: " + student.name);
                    System.out.println("Date of Birth: " + student.dob);
                    System.out.println("City: " + student.address.city);
                    System.out.println("State: " + student.address.state);
                    System.out.println("Pincode: " + student.address.picode);
                    System.out.println("Country: " + student.address.country);
                    System.out.println();
                }
            } else {
                System.out.println("No student found.");
            }
            System.out.println();
        } catch (IOException | ClassNotFoundException exc) {
            System.out.println("Not able to read the file.");
        }
    }
}