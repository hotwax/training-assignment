package Serialize_and_deserialize;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

//If you try to deserialize the output1.txt file then it will be deserialized easily and all objects are shown in command prompt.
//but if you try to deserialize output2.txt file then it will give you a ClassCastException because we have changed the type of date variable from String to Date.
//it will show InvalidClassException if we don't provide serialVerseionUID in our Student class.
public class DeserializationTest {

    public static void main(String args[]) {
        // Object deserialization
        try {
            Student object2;
            //FileInputStream to read file from output2.txt
            System.out.println("Enter file name you want to Deserialize");
            FileInputStream fileInputStream = new FileInputStream("E:\\Work\\java work\\Module2\\src\\Serialize_and_deserialize\\" + args[0]);
            //creating an object objectInput Stream to read FileInputStream
            ObjectInputStream objectinputStream = new ObjectInputStream(fileInputStream);
            //deserializing data using readObject() method.
            ArrayList<Student> students = (ArrayList<Student>) objectinputStream.readObject();
            objectinputStream.close();
            //printing deserialized data of objects.
            for (int student = 0; student < students.size(); student++) {
                System.out.println(students.get(student));
            }
            System.out.println("data is Deserialized");
        } catch (Exception exception) {
            System.out.println("Exception during deserialization: " + exception);
            exception.printStackTrace();
            System.exit(0);
        }
    }
}
