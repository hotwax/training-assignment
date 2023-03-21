import java.io.*;
import java.util.*;
public class SerializationTest 
{
    public static void main(String[] args) 
    {
        List<Student> students=new ArrayList<Student>();
        students.add(new Student("John",new Date(),new Address("New York", "NY", 10001, "USA")));
        students.add(new Student("Jane",new Date(),new Address("Los Angeles", "CA", 90001, "USA")));
        students.add(new Student("Bob",new Date(),new Address("Chicago", "IL", 60601, "USA")));
        students.add(new Student("Alice",new Date(),new Address("Houston", "TX", 77001, "USA")));
        String fileName = args[0];
        try 
        {
            FileOutputStream fos=new FileOutputStream(fileName);
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
 