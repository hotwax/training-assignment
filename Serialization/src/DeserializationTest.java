import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;


public class DeserializationTest{
    public static void main(String[] args){
        try{
            FileInputStream fileIn = new FileInputStream("Serialization/src/output2.ser");
            ObjectInputStream object = new ObjectInputStream(fileIn);
            ArrayList<Student> students = (ArrayList<Student>) object.readObject();
            object.close();
            fileIn.close();
            for(Student student: students){
                System.out.println();
                System.out.println(student.name);
                System.out.println(student.dob);
                System.out.println(student.address.city);
                System.out.println(student.address.state);
                System.out.println(student.address.picode);
                System.out.println(student.address.country);
            }
            System.out.println();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
