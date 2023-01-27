import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Scanner;

public class DeserializationTest {
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in); // Create a Scanner object
        System.out.println("Enter The Filename : ");

        String fileName = input.nextLine(); // Read user input

        List<Student> list = null; // For storing

        // Deserialization
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            

            list = (List<Student>) in.readObject();

            in.close();
            file.close();

            System.out.println(list);
            // printing the objects -- 

            for(Student stu: list){
                System.out.println(stu.firstName);
            }
        }

        catch (Exception ex) {
            System.out.println(ex);
        }


        

    }
}
