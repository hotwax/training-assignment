

// Steps to Run code 

// step 1 : First Run Serialization.java file 
//          while running the file ,  it will ask for filename , Give name output1.ser to file1 
//          This will Serialise the Object 
// Step2 : to deserialize the file , Run Deserialization.java
//          While running the file , it will which file you want to deserialize Enter output1.ser or output2.ser ........
import java.io.FileNotFoundException;
import java.io.EOFException;
import java.util.ArrayList;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

// Class Serialization for Serialize the file 
class Serialization 
{

    // Method to Create the Object of the Address and Student class
    public static Student createStudentObject(String name, String dob, String city, String state, Integer pincode, String country) throws Exception
    {
        Address address = new Address(city, state, pincode, country); // set the data members 
        Student student = new Student(name, dob, address);
        return student;
    }

    // Function performs the Serialization
    public void serializationTest(String fileName)
    {
        try 
        {
            // Create four Objects of the student class 
            Student studentObj1 = createStudentObject("Ram", "12/09/1998", "Delhi", "Delhi", 110001, "India");
            Student studentObj2 = createStudentObject("Shyam", "24/12/1970", "Indore", "Madhya Pradesh", 110001, "India");
            Student studentObj3 = createStudentObject("Adarsht", "20/06/2001", "Mumbai", "Maharashtra", 110001, "India");
            Student studentObj4 = createStudentObject("Hardik", "07/05/2004", "Pilani", "Goa", 110001, "India");

            // Add objects in the ArrayList 
            ArrayList < Student > objectList = new ArrayList < >(); //adding them in a list
            objectList.add(studentObj1);
            objectList.add(studentObj2);
            objectList.add(studentObj3);
            objectList.add(studentObj4);


            FileOutputStream fileOutputStreamObject = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStreamObject = new ObjectOutputStream(fileOutputStreamObject);
            objectOutputStreamObject.writeObject(objectList); //writting objects in file
            System.out.println("Serialization Done");
            fileOutputStreamObject.close();
            objectOutputStreamObject.close();

        }catch (FileNotFoundException e) {
            System.out.println(e);
          } catch (EOFException e){
            System.out.println("File is empty");
            System.out.println(e);
          } catch (ClassCastException e){
            System.out.println(e);
          } catch (Exception e){
            System.out.println(e);
          }
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter file name"); // Enter File Name 
        String fileName = sc.next();
        Serialization Serialization_Object  = new Serialization(); // Instance of the class Serialization
        Serialization_Object.serializationTest(fileName);
        sc.close() ;
    }    
}
