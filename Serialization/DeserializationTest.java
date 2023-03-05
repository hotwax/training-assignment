import java.io.FileInputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.util.List;

public class DeserializationTest {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide file name as command line arguments.");
            return;
        }

        try {
            FileInputStream fileInputStream1 = new FileInputStream(args[0]);
            ObjectInputStream objectInputStream1 = new ObjectInputStream(fileInputStream1);
            List<Student> studentList1 = (List<Student>) objectInputStream1.readObject();
            objectInputStream1.close();
            fileInputStream1.close();

            System.out.println("List of students in file " + args[0] + ":");
            for (Student student : studentList1) {
                System.out.println("Name: " + student.getFirstName() + ", DOB: " + student.getDateOfBirth()
                        + ", Address: " + student.getAddress().getCity() + ", " + student.getAddress().getState()
                        + ", " + student.getAddress().getPinCode() + ", " + student.getAddress().getCountry());
            }
        } catch (InvalidClassException e) {
            System.err.println("Error: Serialized object is of an incompatible version.");
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        
    }
}
