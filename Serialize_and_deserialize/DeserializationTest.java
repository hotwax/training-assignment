package Serialize_and_deserialize;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;


/*
The serialization at runtime associates with each serializable class a version number called a serialVersionUID,
which is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes
for that object that are compatible with respect to serialization.
It is because SerialVersionUID is used to ensure that during deserialization the same class (that was used during 
serialize process) is loaded.
JVM will match the serialVersionUID of current .class file and the serialized file if they both don't match
then JVM will throw an InvalidClassException
*/

//If you try to deserialize the output1.txt file then it will be deserialized easily and all objects are shown in command prompt.
//If you try to deserialize the output2.txt file then it will show InvalidClassException if we don't provide serialVerseionUID in our Student class.
//As I have provided the serialVersionUID in my code it will deserialize the previous version of object and print the objects even we have changed the type of date from string to objects.
//but if we change the type of dtae to Date the it will show ClassCastException.

public class DeserializationTest {

    public static void main(String args[]) {
        // Object deserialization
        try {
            //FileInputStream to read file from output2.txt
            System.out.println("Deserializing Output2.txt data");
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
