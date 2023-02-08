package Program4;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

class DeserializationTest {
    public static void main(String[] args) {
        // Defining the student List for storing the Student Objects
        ArrayList<Student> students = new ArrayList();
        // getting the current working directory
        String currDir = getDirectory();
        try {
            // getting serialized file in current directory
            FileInputStream inFile = new FileInputStream(currDir+"Output1.ser");
            // Creating a ObjectInputStream to get Objects
            ObjectInputStream inObject =  new ObjectInputStream(inFile);
            // storing object list to students
            students = (ArrayList<Student>) inObject.readObject();
            // closing the fileInputStream
            inFile.close();
            // closing objectInputStream
            inObject.close();
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
        newDir = currDir.replaceAll("\\\\", "\\\\\\\\") + "\\src\\Program4\\";
        return newDir;
    }

}