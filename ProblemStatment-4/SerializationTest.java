import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SerializationTest {
    public static void main(String[] args) {
        //Created four Student objects with different details
        Student student1 = new Student("Kunal","11/15/2001",new Address("Bhopal","Madhya Pradesh",410001,"India"));
        Student student2 = new Student("Raj","12/15/2001", new Address("Noida","Uttar Pradesh",612001,"India"));
        Student student3 = new Student("Akhil","02/02/2002", new Address("Nasik","Maharashtra",456221,"India"));
        Student student4 = new Student("Pranav","05/03/2003", new Address("Dewas","Madhya Pradesh",313346,"India"));
        //Create an ArrayList and add the four Student objects to it
        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);
        
        try {
            //to write to the file "Output1.ser"
            FileOutputStream file = new FileOutputStream("ProblemStatment-4/Output2.ser");

            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(studentList);
            out.close();
            file.close();
            
            for(Student s:studentList){
                System.out.print(s.getFirstName()+ ",");
                System.out.print(s.getDateOfBirth()+",");
                System.out.print(s.getAddress().getCity()+",");
                System.out.print(s.getAddress().getState()+",");
                System.out.print(s.getAddress().getPinCode()+",");
                System.out.print(s.getAddress().getCountry());
                System.out.println();
            }
            System.out.println("Serialization successful." + "Output2.ser");
        } catch (IOException ex) {
            System.out.println("Error occurred: " + ex.getMessage());
        }
    }
}
