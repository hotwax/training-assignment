import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SerializationTest {

    public static void main(String[] args) {
        // creating Student objects
        Student s1 = new Student("Pushpraj Singh", "10/03/2001",
                new Address("Indore", "Madhya Pradesh", 452001, "India"));
        Student s2 = new Student("Anuj Pawar", "15/04/2015", new Address("Bhopal", "Madhya Pradesh", 462801, "India"));
        Student s3 = new Student("Gourav Singh", "17/12/2001",
                new Address("Indore", "Madhya Pradesh", 452001, "India"));
        Student s4 = new Student("Shivam Shukla", "21/10/2002",
                new Address("Badnawar", "Madhya Pradesh", 452521, "India"));

        // creating an ArrayList of Student objects
        ArrayList<Student> studentData = new ArrayList<>();
        studentData.add(s1);
        studentData.add(s2);
        studentData.add(s3);
        studentData.add(s4);

        try {
            // getting the current working directory
            String dir = System.getProperty("user.dir");

            // creating a new file output stream for Output2.ser in the current directory
            FileOutputStream outFile = new FileOutputStream(dir + "/" + "Output1.ser");

            // creating a new object output stream for outFile
            ObjectOutputStream out = new ObjectOutputStream(outFile);

            // writing the studentData object to out
            out.writeObject(studentData);

            // printing a success message
            System.out.println("Data Successfully Serialized.");

            // closing the object output stream and file output stream
            out.close();
            outFile.close();

        } catch (Exception e) {
            // printing the stack trace if an exception occurs
            e.printStackTrace();
        }
    }
}
