import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class DeserializationTest {

    public static void main(String args[]) throws ClassNotFoundException {
        ArrayList<Student> List = new ArrayList<Student>();
        try {
            String filename=args[0];
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream obj = new ObjectInputStream(file);
            List = (ArrayList) obj.readObject();
            System.out.println("Deserialize the file "+filename);

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
        } catch (IOException e) {
            System.out.println(e);
        }

    }

}