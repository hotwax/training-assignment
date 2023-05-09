package SerializeDeserialize;

// Import necessary packages
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class DeserializationTest {
    public static void main(String args[]) {
        try {
            // Read the serialized file
            FileInputStream file = new FileInputStream("SerializeDeserialize/" + args[0]);
            ObjectInputStream obj = new ObjectInputStream(file);

            // Convert the object back into ArrayList of Student type
            @SuppressWarnings("unchecked")
            ArrayList<Student> List = (ArrayList) obj.readObject();
            System.out.println("Deserialize the file " + args[0]);

            // Print the details of all the students from the deserialized ArrayList
            for (Student student : List) {
                System.out.println();
                System.out.println("Student Name - " + student.getName());
                System.out.println("Student Date Of Birth - " + student.getDob());
                System.out.println("Student Adress - " + student.getAddress().getCity() + ","
                        + student.getAddress().getPincode() + ","
                        + student.getAddress().getState() + "," + student.getAddress().getCountry());
                System.out.println();
            }

            // Close the file streams
            obj.close();
            file.close();
        } catch (InvalidClassException exception) {
            // Catch the InvalidClassCastException and print the error message
            System.out.println(exception);
        } catch (IOException exception) {
            // Catch the IOException and print the error message
            System.out.println(exception);
        } catch (ClassCastException exception) {
            // Catch the ClassCastException and print the error message
            System.out.println(exception);
        } catch (ClassNotFoundException exception) {
            // Catch the ClassNotFoundException and print the error message
            System.out.println(exception);
        } catch (Exception exception) {
            // Catch the Exception and print the error message
            System.out.println(exception);
        }
    }
}
