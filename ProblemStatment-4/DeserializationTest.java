import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
public class DeserializationTest {
    public static void main(String[] args) {
        //ArrayList to store the deserialized objects
       ArrayList<Student> List=  new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(args[0]))) {
            List = (ArrayList<Student>) ois.readObject();
            //print out the details of each student
            for (Student c :List)
            {
                System.out.print(c.getFirstName()+ ",");
                System.out.print(c.getDateOfBirth()+",");
                System.out.print(c.getAddress().getCity()+",");
                System.out.print(c.getAddress().getState()+",");
                System.out.print(c.getAddress().getPinCode()+",");
                System.out.print(c.getAddress().getCountry());
                System.out.println();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
