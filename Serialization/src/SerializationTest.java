import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SerializationTest {

    public static Student createStudentObject(String name, String dob, String city, String state, String pincode, String country){
        Address address = new Address(city, state, pincode, country);
        Student student = new Student(name, dob, address);
        return student;
    }


    public static void main(String[] args){
        Student student1 = createStudentObject("Ram", "12/09/1998", "Delhi", "Delhi", "110001", "India");
        Student student2 = createStudentObject("Shyam", "24/12/1970", "Indore", "Madhya Pradesh", "110001", "India");
        Student student3 = createStudentObject("Adarsht", "20/06/2001", "Mumbai", "Maharashtra", "110001", "India");
        Student student4 = createStudentObject("Hardik", "07/05/2004", "Pilani", "Goa", "110001", "India");
        ArrayList<Student> students = new ArrayList<Student>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        try {
            FileOutputStream fileOut = new FileOutputStream("Serialization/src/output1.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(students);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in .ser file in the folder.");
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        

    }
    
}
