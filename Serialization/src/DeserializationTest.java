import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;


public class DeserializationTest{
    public static void main(String[] args){
        try{
            FileInputStream fileIn = new FileInputStream("Serialization/src/output1.ser");
            ObjectInputStream object = new ObjectInputStream(fileIn);
            ArrayList<Student> students = (ArrayList<Student>) object.readObject();
            // ArrayList students = (ArrayList) object.readObject();
            object.close();
            fileIn.close();
            for(Student student: students){
                System.out.println("Name: " + student.name);
                System.out.println("Date of Birth: " + student.dob);
                System.out.println("City: " + student.address.city);
                System.out.println("State: " + student.address.state);
                System.out.println("Pincode: " + student.address.picode);
                System.out.println("Country: " + student.address.country);
                System.out.println();
            }
            System.out.println();
        }
        catch(Exception e){
            System.out.println("Cannot deserialize the object. The class is not found.");
        }
    }
}
