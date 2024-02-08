import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SerializationTest {
    public static void main(String[] args) {
        // Create four Student objects with different details
        Student student1 = new Student("Kunal", "11/15/2001", new Address("Bhopal", "Madhya Pradesh", 410001, "India"));
        Student student2 = new Student("Raj", "12/15/2001", new Address("Noida", "Uttar Pradesh", 612001, "India"));
        Student student3 = new Student("Akhil", "02/02/2002", new Address("Nasik", "Maharashtra", 456221, "India"));
        Student student4 = new Student("Pranav", "05/03/2003", new Address("Dewas", "Madhya Pradesh", 313346, "India"));

        // Create an ArrayList and add the four Student objects to it
        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);

        try {
            // Write the ArrayList to the file "Output2.ser"
            FileOutputStream file = new FileOutputStream("ProblemStatement-4/Output2.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(studentList);
            out.close();
            file.close();

            // Print the details of each student
            for (Student student : studentList) {
                System.out.print(student.getFirstName() + ",");
                System.out.print(student.getDateOfBirth() + ",");
                System.out.print(student.getAddress().getCity() + ",");
                System.out.print(student.getAddress().getState() + ",");
                System.out.print(student.getAddress().getPinCode() + ",");
                System.out.print(student.getAddress().getCountry());
                System.out.println();
            }
            System.out.println("Serialization successful. Output2.ser");
        } catch (IOException exception) {
            System.out.println("Error occurred: " + exception.getMessage());
        }
    }
}
