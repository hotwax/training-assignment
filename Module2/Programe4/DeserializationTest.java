import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class DeserializationTest {

    public static void main(String[] args) {
        ArrayList<Student> studentdata = new ArrayList();
        String dir = System.getProperty("user.dir");

        try {
            // getting serialized file in current directory
            FileInputStream inFile = new FileInputStream(dir + "/" + "Output1.ser");
            // Creating a ObjectInputStream to get Objects
            ObjectInputStream inObject = new ObjectInputStream(inFile);
            // storing object list to students
            studentdata = (ArrayList<Student>) inObject.readObject();
            // closing the fileInputStream
            inFile.close();
            // closing objectInputStream
            inObject.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            studentdata.forEach(student -> System.out.println(student.tostring()));
        }

    }

}
