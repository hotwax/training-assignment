import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
public class SerializationTest 
{
    public static void main(String[] args) 
    {
        // Creating the list object for storing 
        List<Student> students=new ArrayList<Student>();
        students.add(new Student("John",new Date(),new Address("New York", "NY", 10001, "USA")));
        students.add(new Student("Jane",new Date(),new Address("Los Angeles", "CA", 90001, "USA")));
        students.add(new Student("Bob",new Date(),new Address("Chicago", "IL", 60601, "USA")));
        students.add(new Student("Alice",new Date(),new Address("Houston", "TX", 77001, "USA")));
        String fileName = args[0];
        try 
        {
            // Creating instance of file output stream.
            FileOutputStream fos=new FileOutputStream(fileName);
            // Creating instance for object output stream for writing the serializer
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(students);
            oos.close();
            fos.close();
            System.out.println("Serialization Successful. Output File Name: " + fileName);
        }catch(Exception ex) 
        {
            ex.printStackTrace();
        }
    }
}
 