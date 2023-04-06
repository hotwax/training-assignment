import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SerializationTest {

    public static void main(String[] args) {
        Student s1 = new Student("Pushpraj Singh", "10/03/2001",
                new Address("Indore", "Madhya Pradesh", 452001, "India"));
        Student s2 = new Student("Anuj Pawar", "15/04/2015", new Address("Bhopal", "Madhya Pradesh", 462801, "India"));
        Student s3 = new Student("Gourav Singh", "17/12/2001",
                new Address("Indore", "Madhya Pradesh", 452001, "India"));
        Student s4 = new Student("Shivam Shukla", "21/10/2002",
                new Address("Badnawar", "Madhya Pradesh", 452521, "India"));

        ArrayList<Student> studentdata = new ArrayList<>();

        studentdata.add(s1);
        studentdata.add(s2);
        studentdata.add(s3);
        studentdata.add(s4);

        try {
            String dir = System.getProperty("user.dir");

            FileOutputStream outFile = new FileOutputStream(dir + "/" + "Output1.ser");
            ObjectOutputStream out = new ObjectOutputStream(outFile);
            out.writeObject(studentdata);
            System.out.println("Data Successfully Serialized.");
            out.close();
            outFile.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
