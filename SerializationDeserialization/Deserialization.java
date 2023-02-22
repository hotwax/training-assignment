// Run file to deserialize the Object
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


// Deserialization class
public class Deserialization 
{
    public static void main(String[] args) 
    {
        try 
        {
            Scanner reader = new Scanner(System.in) ;
            System.out.println("Enter file name") ;
            String fileName = reader.next();
            // deserialization
            FileInputStream fileInputObject = new FileInputStream(fileName) ; 
            ObjectInputStream objectInputObject = new ObjectInputStream(fileInputObject) ; 

            ArrayList list = (ArrayList) objectInputObject.readObject() ; // reading object from file and storing in a list

            for (int index = 0; index < list.size(); index++)
                System.out.println(list.get(index)); //  Print content of the list

            objectInputObject.close(); 
            reader.close();
        } 
        catch (IOException | ClassNotFoundException exception) 
        {
            System.out.println(exception);
        }
    }
}
