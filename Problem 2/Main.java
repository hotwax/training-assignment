// Import the File class
import java.io.File;
// FileWriter Class
import java.io.FileWriter;
// Import the IOException class to handle errors
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;


// Create a Employee Class
class Employee {

  static int no_Of_Employees = 1;   // Store the count of employees
  private int employee_id;    
  private String name;
  private String email;
  private int age;
  private String date_of_birth;

  // Constructor to initialise all the members of the Class
  public Employee(String name, String email, int age, String date_of_birth) {
    this.name = name;
    this.email = email;
    this.age = age;
    this.date_of_birth = date_of_birth;
    this.employee_id = no_Of_Employees++;
  }

  // Overload a Constructor for InsertFileContent Method of Class FileOperation
  public Employee( int id , String name , String email , int age ,String date_of_birth) 
  {
    this.employee_id = id;
    this.name = name;
    this.email = email;
    this.age = age;
    this.date_of_birth = date_of_birth;
    this.employee_id = no_Of_Employees++;
  }

  public Integer getId() {
    return employee_id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public int getAge() {
    return age;
  }

  public String getDOB() {
    return date_of_birth;
  }

  @Override
  public String toString() {
    return ( getId()+ "," + getName() + "," + getEmail() + "," + getAge() + "," + getDOB() );
  }
}

// Create a Class For Doing all the File Related Operations
class FileOperations {

  // insertIntoArrayList() Method Used to Insert All the content of File into a ArrayList of Type Employee
  public ArrayList<Employee> insertIntoArrayList() 
  {
    ArrayList<Employee> temporary_List = new ArrayList<Employee>();  // Create a Temporary List for storing Data of File

    try 
    {
      Scanner Reader = new Scanner(new File("Employee.txt"));
      while (Reader.hasNext()) 
      {
        String temp = Reader.next();
        String str[] = temp.split(",");
        Employee employee_object = new Employee( Integer.parseInt(str[0]), str[1], str[2], Integer.parseInt(str[3]), str[4] );
        temporary_List.add(employee_object);
      }
    } 
    catch (IOException e) {
      System.out.println(e);
    } catch (InputMismatchException e) {
      System.out.println(e);
    }
    return temporary_List;
  }

  // insertIntoFile() Method Used to Insert All the content of ArrayList into a file 
  public void insertIntoFile(ArrayList<Employee> list) 
  {
    try 
    {
      FileWriter Writer = new FileWriter("Employee.txt"); 

      for (Employee Emp : list) {
        Writer.write("" + Emp + "\n");  // Put all the content of ArrayList into file
      }

      Writer.close();
    } 
    catch (IOException e) {
      System.out.println(e);
    } catch (InputMismatchException e) {
      System.out.println(e);
    }
  }

  public void deleteFromArrayList(ArrayList<Employee> list , Integer id)
  {
    int isExist = 0 ;
    // Find the Record Corresponding to the given id 
    for(int index = 0 ; index < list.size() ; index++)
    {

      if(list.get(index).getId().equals(id))
      {
        // Found id in the file 
        isExist=1 ;
        list.remove(index); // remove the record
        break ;
      }
    }
    // if Exist
    if(isExist==1)
    {
      insertIntoFile(list);
    }
    else
    {
      System.out.println("Id not Found");
    }
  }
}

// Driver Class / Main Class
public class Main 
{

  // main Function
  public static void main(String[] args) 
  {

    FileOperations fileOperations_object = new FileOperations(); // Create Instance of FileOpreration Class
    ArrayList<Employee> list = new ArrayList<Employee>();  // Create a List to store all the content of the File
    list = fileOperations_object.insertIntoArrayList();   
    
    String name, email, date_of_birth;
    int age;

    while (true)     // menu
    {
      System.out.println("      Menu      ");
      System.out.println("1. Add Employee ");
      System.out.println("2. Delete Employee ");
      System.out.println("3. Search Employee ");
      System.out.println("4. EXIT");

      System.out.println("Enter Your Choice ");
      try
      {
         Scanner Reader = new Scanner(System.in);
         int choice = Reader.nextInt() ;
         switch (choice) 
         {
          case 1:
            {
              // Enter Your Name
              Reader.nextLine();
              System.out.println("Enter Your Name ");
              name = Reader.nextLine();


              // Enter your Email Address
              System.out.println("Enter Your Email Address ");
              email = Reader.next();
              String email_validation = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";  // Put validations on email
              while (!email.matches(email_validation)) 
              {
                System.out.println("Invalid Email Format");
                System.out.println("Sample Email : abc@gmail.com");
                System.out.println("Enter Email again ");
                email = Reader.next();
              }

              // Enter Your Age
              System.out.println("Enter Your Age");
              age = Reader.nextInt();

              // Enter your Date of Birth
              System.out.println( "Enter your Date of Birth in (dd/mm/yyyy) format " );
              date_of_birth = Reader.next();
              String dob_validation = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-2][0-9])?[0-9][0-9]$"; // Put validations on DOB
              while (!date_of_birth.matches(dob_validation)) 
              {
                System.out.println("Invalid Date of Birth Format");
                System.out.println("Sample Date of Birth : 01/01/2023 ");
                System.out.println("Enter Date of Birth again ");
                date_of_birth = Reader.nextLine();
              }

              // Create a Employee class Object
              Employee employee_object = new Employee(
                name,
                email,
                age,
                date_of_birth
              );
              list.add(employee_object);
              fileOperations_object.insertIntoFile(list);
              break;
            }
          case 2:
          {
            // Delete the Employee
            System.out.println("Enter the Employee Id to be Deleted ");
            int id = Reader.nextInt();
            fileOperations_object.deleteFromArrayList(list,id);
            break ;
          }
          case 3:  
          {
            // To do Search Function
            break ;
          }
          case 4:
          {
            System.out.println("Exit");
            
            System.exit(0);
          }

          default:
            System.out.println("Invalid Choice");
            break ;
        }

      }
      catch(InputMismatchException e){
        System.out.println("Invalid Choice");
      }
      
    }
  }
}
