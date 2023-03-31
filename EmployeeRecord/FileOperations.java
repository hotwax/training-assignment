// Create a Class For Doing all the File Related Operations
import java.io.File;
// FileWriter Class
import java.io.FileWriter;
// Import the IOException class to handle errors
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class FileOperations {

    // insertIntoArrayList() Method Used to Insert All the content of File into a
    // ArrayList of Type Employee
    public ArrayList<Employee> insertIntoArrayList() {
      ArrayList<Employee> temporary_List = new ArrayList<Employee>(); // Create a Temporary List for storing Data of File
  
      try {
        Scanner Reader = new Scanner(new File("Employee.txt"));
        while (Reader.hasNext()) {
          
          String records = Reader.nextLine();
          if(records.length()!=0)
          {
            String record_array[] = records.split(",");
            // System.out.println(record_array) ;
            Employee employee_object = new Employee(Integer.parseInt(record_array[0]), record_array[1], record_array[2],
                Integer.parseInt(record_array[3]), record_array[4]);
            temporary_List.add(employee_object);
          }
        }
      } catch (IOException e) {
        System.out.println("Employee.txt File Not found\nCreating Employee.txt File...");
      } catch (InputMismatchException e) {
        System.out.println(e);
      }
      return temporary_List;
    }
  
    // insertIntoFile() Method Used to Insert All the content of ArrayList into a
    // file
    public void insertIntoFile(ArrayList<Employee> list) {
      try {
        FileWriter Writer = new FileWriter("Employee.txt",true);
  
        for (Employee employee : list) {
          // if(employee.getId())
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
      try {
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
      } catch (NumberFormatException e) {
        System.out.println("Value should be numeric");
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
  