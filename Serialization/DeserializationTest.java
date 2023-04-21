import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class DeserializationTest {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide file name as command line arguments.");
            return;
        }


        if (!args[0].endsWith(".ser")) {
            System.out.println("Error: The file must have a .ser extension.");
            return;
        }
        else{

        try {
            
            FileInputStream fileInputStream1 = new FileInputStream(args[0]);
            ObjectInputStream objectInputStream1 = new ObjectInputStream(fileInputStream1);
            ArrayList<Student> studentList1 = (ArrayList<Student>) objectInputStream1.readObject();

            if(studentList1.isEmpty()) {
                System.out.println("File is empty.");
                objectInputStream1.close();
                fileInputStream1.close();
                return;
            }

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

        catch (EOFException e){
            System.out.println("Error: Empty File.");

        }

        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        catch (IOException e) {
            System.out.println("Error: File not found.");
            e.printStackTrace();
        }
         catch (Exception e) {
            e.printStackTrace();
        }

    }
        

        
    }
}
