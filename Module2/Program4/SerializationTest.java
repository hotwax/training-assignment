package Program4;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

class SerializationTest {
    public static void main(String[] args) {
        Student s1 = new Student("Ravi Jain","10/10/1398",new Address("Bhopal","Madhya Pradesh",410001,"India"));
        Student s2 = new Student("Panther","12/03/2013", new Address("Noida","Uttar Pradesh",612001,"India"));
        Student s3 = new Student("Shruti","12/03/2111", new Address("Lucknow","Uttar Pradesh",456221,"India"));
        Student s4 = new Student("Chandelier","11/04/1921", new Address("Surat","Gujarat",313346,"India"));
        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);
        studentList.add(s4);
        String currDir = getDirectory();
        try {
            FileOutputStream outFile = new FileOutputStream(currDir +"Output1.ser");
            ObjectOutputStream out = new ObjectOutputStream(outFile);
            out.writeObject(studentList);
            System.out.println("Data Successfully Serialized.");
            out.close();
            outFile.close();
        } catch (Exception err){
            err.printStackTrace();
        }
    }
    private static String getDirectory(){
        String currDir = System.getProperty("user.dir");
        String newDir;
        newDir = currDir.replaceAll("\\\\", "\\\\\\\\") + "\\src\\Program4\\";
        return newDir;
    }

}