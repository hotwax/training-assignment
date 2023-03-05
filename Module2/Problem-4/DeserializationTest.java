import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class DeserializationTest {
    public static void main(String[] args) {
        String fileName = args[0];

        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<Student> students = (List<Student>) ois.readObject();
            ois.close();
            fis.close();

            for (Student student : students) {
                System.out.println("Name: " + student.getFirstName() +
                        ", DOB: " + student.getDateOfBirth() +
                        ", Address: " + student.getAddress().getCity() + ", " +
                        student.getAddress().getState() + ", " +
                        student.getAddress().getPinCode() + ", " +
                        student.getAddress().getCountry());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
 
