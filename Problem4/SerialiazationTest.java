package Problem4;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

class SerialiazationTest {
    public static void main(String[] args) {
        // Creating the Objects of Student class
      Student s1 = new Student("Ravi Kumar",new Date("01-10-1998"),new Address("Bhopal","Madhya Pradesh",410001,"India"));
      Student s2 = new Student("Pankaj",new Date("13-03-2003"), new Address("Noida","Uttar Pradesh",612001,"India"));
      Student s3 = new Student("Shyam",new Date("12-03-2001"), new Address("Lucknow","Uttar Pradesh",456221,"India"));
      Student s4 = new Student("Chandan",new Date("21-04-1971"), new Address("Surat","Gujarat",313346,"India"));
      // Creating list of students
      ArrayList<Student> studentList = new ArrayList<>();
      // Adding Students to list
      studentList.add(s1);
      studentList.add(s2);
      studentList.add(s3);
      studentList.add(s4);
      // getting the path of the current directory
      String currDir = getDirectory();
        try {
            FileOutputStream outFile = new FileOutputStream(currDir +"output2.ser");
            ObjectOutputStream out = new ObjectOutputStream(outFile);
            out.writeObject(studentList);
            System.out.println("Object has been serialized");
            out.close();
            outFile.close();
        } catch (IOException err){
            System.out.println("File not Found");
            err.printStackTrace();
        } catch (Exception err){
            System.out.println();
            err.printStackTrace();
        }
    }
    private static String getDirectory(){
        String currDir = System.getProperty("user.dir");
        String newDir;
        newDir = currDir.replaceAll("\\\\", "\\\\\\\\") + "\\Problem4\\";
        return newDir;
    }

}
