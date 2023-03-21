package Problem4;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

class DeserialiazationTest {
    public static void main(String[] args) {
        // Defining the student List for storing the Student Objects
        ArrayList<Student> students = new ArrayList();
        // getting the current working directory
        String currDir = getDirectory();
        try {
            // getting serialized file in current directory
            FileInputStream inFile = new FileInputStream(currDir+"output1.ser");
            // Creating a ObjectInputStream to get Objects
            ObjectInputStream inObject =  new ObjectInputStream(inFile);
            // storing object list to students
            students = (ArrayList<Student>) inObject.readObject();
            // closing the fileInputStream
            inFile.close();
            // closing objectInputStream
            inObject.close();
        } catch(EOFException err){
            System.out.println("Empty File");
        } catch (IOException err){
            System.out.println("File not Found");
            err.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
           students.forEach(student -> System.out.println(student.toString()));
        }
    }
    // Function to get current working directory
    private static String getDirectory(){
        // Getting the project Directory
        String currDir = System.getProperty("user.dir");
        String newDir;
        // Replacing all "\" to "\\" and adding the subdirectory
        newDir = currDir.replaceAll("\\\\", "\\\\\\\\") + "\\Problem4\\";
        return newDir;
    }

}
