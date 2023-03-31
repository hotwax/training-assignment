
// FileWriter Class
import java.io.FileWriter;
// Import the IOException class to handle errors
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

// Driver Class / Main Class
public class Main {

  // main Function
  public static void main(String[] args) {

    FileOperations fileOperations_object = new FileOperations(); // Create Instance of FileOpreration Class
    ArrayList<Employee> list = new ArrayList<Employee>(); // Create a List to store all the content of the File
    list = fileOperations_object.insertIntoArrayList();

    String name, email, date_of_birth;
    int age;
    while (true) // menu
    {
      System.out.println("      Menu      ");
      System.out.println("1. Add Employee ");
      System.out.println("2. Delete Employee ");
      System.out.println("3. Search Employee ");
      System.out.println("4. EXIT");

      System.out.println("Enter Your Choice ");
      try {
        Scanner Reader = new Scanner(System.in);
        int choice = Reader.nextInt();
        switch (choice) {
          case 1: {
            // Enter Your Name
            Reader.nextLine();
            System.out.println("Enter Your Name ");
            name = Reader.nextLine();
            while(name.length()==0)
            {
              System.out.println("Name field Shouldn't be empty\nEnter Name");
              name = Reader.nextLine();
            }

            // Enter your Email Address
            System.out.println("Enter Your Email Address ");
            email = Reader.next();
            String email_validation = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$"; // Put validations on email
            while (!email.matches(email_validation)) {
              System.out.println("Invalid Email Format");
              System.out.println("Sample Email : abc@gmail.com");
              System.out.println("Enter Email again ");
              email = Reader.next();
            }

            // Enter Your Age
            System.out.println("Enter Your Age");
            // age = Reader.nextInt();
            String ageValid = Reader.next();
            String regex = "[0-9]+";
            while (!ageValid.matches(regex)) {
              System.out.println("Age Should be a Number");
              System.out.print("Enter Age Again ");
              ageValid = Reader.next();
            }
            age = Integer.parseInt(ageValid);
            // Enter your Date of Birth
            System.out.println("Enter your Date of Birth in (dd/mm/yyyy) format ");
            Reader.nextLine();
            date_of_birth = Reader.nextLine();
            String dob_validation = "^([0-2][1-9]||[1-2][0-9]||3[0-1])/(0[1-9]||1[0-2])/([0-2][0-9])?[0-9][0-9]$"; // Put
                                                                                                       // validations on
                                                                                                       // DOB
            while (!date_of_birth.matches(dob_validation)) {
              System.out.println("Invalid Date of Birth Format");
              System.out.println("Sample Date of Birth : 01/01/2023 ");
              System.out.println("Enter Date of Birth again ");
              date_of_birth = Reader.nextLine();
            }

            // Create a Employee class Object
            Employee employee_object = new Employee(name, email, age, date_of_birth);
            list.add(employee_object); // Add the Object into the ArrayList
            // fileOperations_object.insertIntoFile(list); // Insert the list into File
            FileWriter Writer = new FileWriter("Employee.txt",true);
            Writer.write("" + employee_object+ "\n");
            System.out.println(name + " Added Successfully ");
            Writer.close();
            break;
          }

          case 2: {
            // Delete the Employee
            System.out.println("Enter the Employee Id to be Deleted ");
            int id = Reader.nextInt();
            fileOperations_object.deleteFromArrayList(list, id);
            break;
          }
          case 3: {

            // Search Records
            System.out.println("Search Records of Employees");
            // Filters in Searching like Search by , Sort By .
            System.out.println("Search By : \n1. Employee Id \n2. Name \n3. Email\n4. Date of Birth\n5. Age");
            int response = Reader.nextInt(); // choice
            if (response < 1 || response > 5) {
              System.out.println("Choose option from 1 to 5");
              break;
            }
            System.out.println("Enter Value to be Searched");
            Reader.nextLine();
            String key = Reader.nextLine(); // Which value to be searched
            ArrayList<Employee> foundRecords = fileOperations_object.searchRecord(list, response, key);

            // Records Found corresponding to the Search Value
            if (!foundRecords.isEmpty()) {
              System.out.println("Sort By : \n1. Employee Id \n2. Name \n3. Email\n4. Date of Birth\n5. Age");
              response = Reader.nextInt();
              foundRecords = fileOperations_object.sortRecords(foundRecords, response); // Sort the records

              System.out.println("1. Ascending \n2. Descending "); // in which order you want to Sort
              int order = Reader.nextInt();

              if (order == 1) // Ascending Order
              {
                System.out.println("_______________________________________________________");
                System.out.println();
                for (Employee employee : foundRecords)
                  System.out.println("" + employee);
                System.out.println("_______________________________________________________");
              } else {
                Collections.reverse(foundRecords); // reverse list for desending order
                System.out.println();
                System.out.println("_______________________________________________________");
                for (Employee employee : foundRecords)
                  System.out.println("" + employee);
                System.out.println("_______________________________________________________");
              }
            } else // // if there is no record corresponding to searchd Value
            {
              System.out.println("Record Not found");
            }

            break;
          }
          case 4: {
            // EXIT case
            System.out.println("Exit");
            System.exit(0);

          }

          default:
            System.out.println("Invalid Choice");
            break;
        }

      } catch (InputMismatchException e) {
        System.out.println("Invalid Choice");
      }
      catch (IOException e)
      {
        System.out.println("Unable to Read File , Try again");
      }

    }
  }
}
