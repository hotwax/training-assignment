package Serialize_and_deserialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SerializationTest {

    public static void main(String args[]) {
        // Object serialization
        try {
            //creating 4 objects of Student class
            Student student1 = new Student("Dhruv1", "12/01/2002", new Address("Indore", "MP", 452011, "India"));
            Student student2 = new Student("Dhruv2", "12/01/2002", new Address("Indore", "MP", 452011, "India"));
            Student student3 = new Student("Dhruv3", "12/01/2002", new Address("Indore", "MP", 452011, "India"));
            Student student4 = new Student("Dhruv4", "12/01/2002", new Address("Indore", "MP", 452011, "India"));
            //creating arrayList of objects and adding the created 4 objects
            ArrayList<Student> students = new ArrayList<>();
            students.add(student1);
            students.add(student2);
            students.add(student3);
            students.add(student4);
            //printlng all objects
            for (int student = 0; student < students.size(); student++) {
                System.out.println(students.get(student));
            }
            //FileOutputStream to read file from the file name entered by user
            System.out.println("Enter file name you want to Serialize");
            FileOutputStream fileOutputStream = new FileOutputStream("E:\\Work\\java work\\Module2\\src\\Serialize_and_deserialize\\" + args[0]);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            //Serializing the List into that file
            objectOutputStream.writeObject(students);
            //flushing the data
            objectOutputStream.flush();
            //closing the FileOutputStream
            objectOutputStream.close();
            System.out.println("data is Serialized");
        } catch (IOException exception) {
            //for catching exceptions
            System.out.println("Exception during serialization: " + exception);
            System.exit(0);
        }
    }
}
