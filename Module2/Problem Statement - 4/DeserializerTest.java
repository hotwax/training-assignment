import java.io.*;
import java.util.*;
public class DeserializerTest 
{
    public static void main(String args[]) 
    {
        String fileName=args[0];
        try 
        {
            FileInputStream fis=new FileInputStream(fileName);
            ObjectInputStream ois=new ObjectInputStream(fis);
            List<Student> students=(List<Student>)ois.readObject();
            ois.close();
            fis.close();
            for(Student student:students) 
            {
                System.out.println("Name: " + student.firstName+
                        ", DOB: " + student.dateOfBirth +
                        ", Address: " + student.address.city + ", " +
                        student.address.State + ", " +
                        student.address.pinCode + ", " +
                        student.address.country);
            }
        }
        catch(EOFException e){
            System.out.println("File is empty!!!!");
        }
        catch(FileNotFoundException e){
            System.out.println(fileName+" not found.");
        }

        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
 