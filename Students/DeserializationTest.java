package Students;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class DeserializationTest {

    public static void main(String args[]) {
        try {
            FileInputStream file = new FileInputStream("Students/" + args[0]);
            ObjectInputStream obj = new ObjectInputStream(file);
            @SuppressWarnings("unchecked")
            ArrayList<Student> List = (ArrayList) obj.readObject();
            System.out.println("Deserialize the file " + args[0]);

            for (Student student : List) {
                System.out.println();
                System.out.println("Student Name - " + student.getName());
                System.out.println("Student Date Of Birth - " + student.getDob());
                System.out.println("Student Adress - " + student.getAddress().getCity() + ","
                        + student.getAddress().getPincode() + ","
                        + student.getAddress().getState() + "," + student.getAddress().getCountry());
                System.out.println();
            }
            obj.close();
            file.close();
        } catch (IOException exception) {
            System.out.println(exception);
        }
        catch(ClassCastException exception){
            System.out.println(exception);
        }
        catch(ClassNotFoundException exception){
            System.out.println(exception);
        }
        
    }

}
