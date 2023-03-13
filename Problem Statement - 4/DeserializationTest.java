import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Scanner;

// for deserialization -- 

public class DeserializationTest {
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in); // Create a Scanner object
        System.out.println("Enter The Filename : ");
        String fileName = input.nextLine(); // Read user input
        List<Student> studentList = null; // For storing

        // Deserialization
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            studentList = (List<Student>) in.readObject();
            in.close();
            file.close();

            // printing the objects --
            for (Student stu : studentList) {
                System.out.println(stu);
            }
        }

        catch (Exception ex) {
            // Exception handling
            System.out.println(ex);
        }
    }
}
