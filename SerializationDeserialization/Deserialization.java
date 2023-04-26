
// Run file to deserialize the Object
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.EOFException;
import java.io.StreamCorruptedException;

// Deserialization class
public class Deserialization {
    public void deserializationTest(String fileName) {
        try {
            // deserialization
            FileInputStream fileInputObject = new FileInputStream(fileName);
            ObjectInputStream objectInputObject = new ObjectInputStream(fileInputObject);

            ArrayList list = (ArrayList) objectInputObject.readObject(); // reading object from file and storing in a
                                                                         // list

            for (int index = 0; index < list.size(); index++)
                System.out.println(list.get(index)); // Print content of the list

            fileInputObject.close();
            objectInputObject.close();

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (EOFException e) {
            System.out.println("File is empty");
            System.out.println(e);
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("File Not Found");
        } catch (ClassCastException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
