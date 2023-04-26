import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

  static String enterFileName() {
    
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the file name: with Extension .ser");
    String fileName = sc.next();
    String extension[] = fileName.split("\\.");
    if(extension.length == 1 || !extension[1].equals("ser"))
    {
      System.out.println("please enter a valid file name. Extension allowed- '.ser'\n");
      return enterFileName();
    }
    else return fileName;
  }

  public static void main(String[] args) 
  {

    while (true) 
    {
      System.out.println("Enter 1 to serialize");
      System.out.println("Enter 2 to deserialize");
      System.out.println("Enter 3 to exit the program");
      System.out.println("Enter Your Choice");

      try 
      {
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        String fileName = "";

        switch (choice) 
        {
          case 1:
            fileName = enterFileName();
            new Serialization().serializationTest(fileName);
            System.out.println("---------------------------\n");
            break;

          case 2:
            fileName = enterFileName();
            new Deserialization().deserializationTest(fileName);
            System.out.println("---------------------------\n");
            break;

          case 3:
            System.out.println("---------------------------\n");
            System.out.println("Program terminated successfully.");
            return;

          default:
            System.out.println("---------------------------\n");
            System.out.println("Please enter a valid number");
        }
      } 
      catch (InputMismatchException e) 
      {
        System.out.println(e);
        System.out.println("Please enter a valid input");
      }

    }

  }
}