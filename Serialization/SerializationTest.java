import java.util.ArrayList;
import java.util.List;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class SerializationTest {
    public static void main(String[] args) {
        Address address1 = new Address("Shahdol", "MP", 123456, "India");
        Address address2 = new Address("Indore", "MP", 456789, "India");
        Address address3 = new Address("Bhopal", "MP", 789123, "India");
        Address address4 = new Address("Gurugram", "HR", 321654, "India");

        Student student1 = new Student("Jayesh", "01/01/2000", address1);
        Student student2 = new Student("Arun", "02/02/2001", address2);
        Student student3 = new Student("Ankit", "03/03/2002", address3);
        Student student4 = new Student("Devendra", "04/04/2003", address4);

        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);


        if (!args[0].endsWith(".ser")) {
            System.out.println("Error: The file must have a .ser extension.");
            return;
        }

        else{

        try {
            FileOutputStream fileOutputStream1 = new FileOutputStream(args[0]);
            ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(fileOutputStream1);
            objectOutputStream1.writeObject(studentList);
            System.out.println("Serialisation successful." + "on file- "+ args[0]);
            objectOutputStream1.close();
            fileOutputStream1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }}

        
    }
}