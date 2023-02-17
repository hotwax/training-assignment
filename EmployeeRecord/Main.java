
// Import the File class
import java.io.File;
// FileWriter Class
import java.io.FileWriter;
// Import the IOException class to handle errors
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

// Create a Employee Class
class Employee {

  static int no_Of_Employees = 1; // Store the count of employees
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
  public Employee(int id, String name, String email, int age, String date_of_birth) {
    this.employee_id = id;
    this.name = name;
    this.email = email;
    this.age = age;
    this.date_of_birth = date_of_birth;
    this.employee_id = no_Of_Employees++;
  }

  // Getters for all the Class Members
  public Integer getId() {
    return employee_id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public Integer getAge() {
    return age;
  }

  public String getDOB() {
    return date_of_birth;
  }

  @Override
  public String toString() { // to print the Object of the class
    return (getId() + "," + getName() + "," + getEmail() + "," + getAge() + "," + getDOB());
  }

  public static class OrderByID implements Comparator<Employee> // class to sort by id
  {
    public int compare(Employee obj1, Employee obj2) {
      return obj1.employee_id - obj2.employee_id;
    }
  }

  public static class OrderByName implements Comparator<Employee> // inner class to sort by name
  {
    public int compare(Employee obj1, Employee obj2) {
      return obj1.name.compareTo(obj2.name);
    }
  }

  public static class OrderByEmail implements Comparator<Employee> // inner class to sort by email
  {
    public int compare(Employee obj1, Employee obj2) {
      return obj1.email.compareTo(obj2.email);
    }
  }

  public static class OrderByDOB implements Comparator<Employee> // inner class to sort by DOB
  {
    public int compare(Employee obj1, Employee obj2) {
      return obj1.date_of_birth.compareTo(obj2.date_of_birth);
    }
  }

  public static class OrderByAge implements Comparator<Employee> // inner class to sort by Age
  {
    public int compare(Employee obj1, Employee obj2) {
      return obj1.age - obj2.age;
    }
  }
}

// Create a Class For Doing all the File Related Operations
class FileOperations {

  // insertIntoArrayList() Method Used to Insert All the content of File into a
  // ArrayList of Type Employee
  public ArrayList<Employee> insertIntoArrayList() {
    ArrayList<Employee> temporary_List = new ArrayList<Employee>(); // Create a Temporary List for storing Data of File

    try {
      Scanner Reader = new Scanner(new File("Employee.txt"));
      while (Reader.hasNext()) {
        String records = Reader.next();
        String record_array[] = records.split(",");
        Employee employee_object = new Employee(Integer.parseInt(record_array[0]), record_array[1], record_array[2],
            Integer.parseInt(record_array[3]), record_array[4]);
        temporary_List.add(employee_object);
      }
    } catch (IOException e) {
      System.out.println(e);
    } catch (InputMismatchException e) {
      System.out.println(e);
    }
    return temporary_List;
  }

  // insertIntoFile() Method Used to Insert All the content of ArrayList into a
  // file
  public void insertIntoFile(ArrayList<Employee> list) {
    try {
      FileWriter Writer = new FileWriter("Employee.txt");

      for (Employee employee : list) {
        Writer.write("" + employee + "\n"); // Put all the content of ArrayList into file
      }

      Writer.close();
    } catch (IOException e) {
      System.out.println(e);
    } catch (InputMismatchException e) {
      System.out.println(e);
    }
  }

  // Method to Delete the Employee Record From the Array List
  public void deleteFromArrayList(ArrayList<Employee> list, Integer id) {
    // Record is Exist or not
    int isExist = 0;
    // Find the Record Corresponding to the given id
    for (int index = 0; index < list.size(); index++) {

      // Compare the given id is present or not in the list , if present delete it
      if (list.get(index).getId().equals(id)) {
        // Found id in the file
        isExist = 1;
        list.remove(index); // remove the record
        break;
      }
    }
    // if Exist
    if (isExist == 1) {
      insertIntoFile(list);
      System.out.println("Operation Done");
    } else {
      System.out.println("Id not Found");
    }
  }

  // Method to Search the record in the List
  ArrayList<Employee> searchRecord(ArrayList<Employee> list, int response, String key) {
    ArrayList<Employee> foundRecords = new ArrayList<Employee>(); // Store all the Records Corresponding to the key
    switch (response) // Switch Case
    {
      case 1: // Search by Id
        for (Employee employee : list) {
          if (employee.getId().equals(Integer.parseInt(key)))
            foundRecords.add(employee);
        }
        break;
      case 2: // Search By Name
        for (Employee employee : list) {
          if (employee.getName().equalsIgnoreCase(key))
            foundRecords.add(employee);
        }
        break;

      case 3: // Search By Email
        for (Employee employee : list) {
          if (employee.getEmail().equalsIgnoreCase(key))
            foundRecords.add(employee);
        }
        break;
      case 4: // Search By date of Birth
        for (Employee employee : list) {
          if (employee.getDOB().equalsIgnoreCase(key))
            foundRecords.add(employee);
        }
        break;

      case 5: // Search by Age
        for (Employee employee : list) {
          if (employee.getAge().equals(Integer.parseInt(key)))
            foundRecords.add(employee);
        }
        break;

    }
    return foundRecords; // Return the list Which Contain all the Records Corresponding to the Key
  }

  ArrayList<Employee> sortRecords(ArrayList<Employee> list, int response) {
    // Sort the List which found after the Search in List by Key

    switch (response) {
      case 1: // Sort by Id
        Collections.sort(list, new Employee.OrderByID());
        break;

      case 2: // Sort By Name
        Collections.sort(list, new Employee.OrderByName());
        break;

      case 3: // Sort by Email
        Collections.sort(list, new Employee.OrderByEmail());
        break;

      case 4: // Sort By Date Of Birth
        Collections.sort(list, new Employee.OrderByDOB());
        break;

      case 5: // Sort By age
        Collections.sort(list, new Employee.OrderByAge());
        break;

    }
    return list;
  }
}

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
            age = Reader.nextInt();

            // Enter your Date of Birth
            System.out.println("Enter your Date of Birth in (dd/mm/yyyy) format ");
            Reader.nextLine();
            date_of_birth = Reader.nextLine();
            String dob_validation = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-2][0-9])?[0-9][0-9]$"; // Put
                                                                                                       // validations on
                                                                                                       // DOB
            while (!date_of_birth.matches(dob_validation)) {
              System.out.println("Invalid Date of Birth Format");
              System.out.println("Sample Date of Birth : 01/01/2023 ");
              System.out.println("Enter Date of Birth again ");
              date_of_birth = Reader.nextLine();
            }

            // Create a Employee class Object
            Employee employee_object = new Employee( name , email, age, date_of_birth);
            list.add(employee_object); // Add the Object into the ArrayList 
            fileOperations_object.insertIntoFile(list); // Insert the list into File
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
            if(response<1 || response>5) 
            {
              System.out.println("Choose from option 1 to 5");
              break;
            }
            System.out.println("Enter Value to be Searched");
            String key = Reader.next(); // Which value to be searched
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

    }
  }
}
