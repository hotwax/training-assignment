import java.io.FileInputStream;
import java.io. ObjectInputStream;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.util.List;
public class DeserializerTest 
{
    public static void main(String args[]) 
    {
        // Here filename is taken from command line while executing the file.
        String fileName=args[0];
        try 
        {
            // Creating object of file input stream.
            FileInputStream fis=new FileInputStream(fileName);
            ObjectInputStream ois=new ObjectInputStream(fis);
            List<Student> students=(List<Student>)ois.readObject();
            ois.close();
            fis.close();

            // This loop will iterate through students list and print all the data present inside the list.
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
 