import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Desktop;

public class SearchAndSortOnEmployee {

  static Scanner sc = new Scanner(System.in);

  static HashSet<Employee> set = new HashSet<>();
  // to store employees objects, hashset because it doesn't allow duplicate
  // entries

  final static String employeeFilePath = "employees.txt";

  static String enterAge() {
    System.out.println("Enter age of employee: ");
    String age = sc.next();
    String regex = "[0-9]+";
    boolean match = age.matches(regex);
    if (!match) {
      System.out.println("Invalid age. Please enter a different age.\n");
      return enterAge();
    } else
      return age;
  }

  static String enterName() {
    System.out.println("Enter name of employee: ");
    String name = sc.nextLine();
    return name;
  }

  static String enterEmail() {
    System.out.println("Enter email of employee: ");
    String email = sc.next();
    String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    boolean match = email.matches(regex);
    if (!match) {
      System.out.println("Invalid email. Please enter a different email.\n");
      return enterEmail();
    }
    return email;
  }

  static String enterDateOfBirth() {
    System.out.println("Enter date of birth of employee (format- yyyy-mm-dd):");
    String dateOfBirth = sc.next();
    String regex = "^([1-9][0-9][0-9][0-9])-(0[1-9]||1[0-2])-([0-2][1-9]||3[0-1])$";
    boolean match = dateOfBirth.matches(regex);
    if (!match) {
      System.out.println("Invalid date. Please enter a different date.\n");
      return enterDateOfBirth();
    }
    return dateOfBirth;
  }

  static boolean whetherEmailExists(String email) {
    for (Employee employee : set) {
      if (employee.getEmail().equals(email))
        return true;
    }
    return false;
  }

  static ArrayList<Employee> whetherNameExists(String name) {
    ArrayList<Employee> matchedNames = new ArrayList<>();
    for (Employee employee : set) {
      if (employee.getName().equals(name))
      matchedNames.add(employee);
    }
    return matchedNames;
  }

  static ArrayList<Employee> whetherAgeExists(String age) {
    ArrayList<Employee> matchedAges  = new ArrayList<>();
    for (Employee employee : set) {
      if (employee.getAge() == Integer.parseInt(age))
      matchedAges.add(employee);
    }
    return matchedAges;
  }

  static ArrayList<Employee> whetherDateOfBirthExists(String dateOfBirth) {
    ArrayList<Employee> matchedDates  = new ArrayList<>();
    for (Employee employee : set) {
      Date employeeDateOfBirth = employee.getDateOfBirth();
      if (employeeDateOfBirth.toString().equals(dateOfBirth))
      matchedDates.add(employee);
    }
    return matchedDates;
  }

  static void add() {
    // here we will take employee's details from user, create an employee object,
    // add object to set and call addToFile method

    String name = enterName();
    String age = enterAge();
    String email = enterEmail();
    if (whetherEmailExists(email)) {
      System.out.println("Email already exists. Please enter a different email.");
      enterEmail();
    }
    String dateOfBirth = enterDateOfBirth();

    Employee employeeObject = new Employee();
    employeeObject.setName(name);
    employeeObject.setAge(Integer.parseInt(age));
    employeeObject.setEmail(email);
    employeeObject.setDate(dateOfBirth);

    set.add(employeeObject);

    addToFile(employeeObject); // file handling

    System.out.println("Data is inserted successfully.");
    System.out.println("-------------------------------\n");

  }

  static void addToFile(Employee employeeObject) {
    // here we will perform file handling and add the employee object to
    // employee.txt

    FileWriter fileWriter = null;
    BufferedWriter bufferedWriter = null;
    File employeefile = null;
    try {
      employeefile = new File(employeeFilePath);
      employeefile.createNewFile();
      fileWriter = new FileWriter(employeeFilePath, true); // true- appends to file
      bufferedWriter = new BufferedWriter(fileWriter);
      bufferedWriter.write(employeeObject.toSring() + "\n");

      if (Desktop.isDesktopSupported()) { // Desktop class is used to open employee.txt file after adding data to the
        // file
        Desktop desktop = Desktop.getDesktop();
        desktop.open(employeefile);
      }
      bufferedWriter.close();
      fileWriter.close();
    } catch (IOException err) {
      System.out.println("some error occurred while writing data to the file. - " + err);
    }

  }

  static void delete() {
    // here we will first remove the specified employee from set and rewrite the set
    // data to employee.txt file

    String email = "";
    while (true) {
      email = enterEmail();
      if (!whetherEmailExists(email)) {
        System.out.println("Email doesn't exists. Please enter a different email.");
      } else {
        break;
      }
    }

    Iterator<Employee> iterator = set.iterator();
    while (iterator.hasNext()) { // we have used iterator in place of for loop because java throws an exception
                                 // if we apply loop on a class and remove from the same class in it.
      Employee employee = iterator.next();
      if (employee.getEmail().equals(email)) {
        set.remove(employee);
        break;
      }
    }

    reWriteInFile();
    System.out.println("Employee's data is removed successfully.");
    System.out.println("-------------------------------\n");
  }

  static void reWriteInFile() {

    FileWriter fileWriter = null;
    BufferedWriter bufferedWriter = null;
    File employeeFile = null;
    try {
      employeeFile = new File(employeeFilePath);
      fileWriter = new FileWriter(employeeFilePath);
      bufferedWriter = new BufferedWriter(fileWriter);

      for (Employee employeeObject : set)
        bufferedWriter.write(employeeObject.toSring() + "\n");

      if (Desktop.isDesktopSupported()) { // Desktop class is used to open employee.txt file after adding data to the
                                          // file
        Desktop desktop = Desktop.getDesktop();
        desktop.open(employeeFile);
      }
      bufferedWriter.close();
      fileWriter.close();
    } catch (IOException err) {
      System.out.println("some error occurred while rewriting data to the file. - " + err);
    }

  }

  static void fetch() {
    // if employee.txt file is already present then we will fetch its data and add
    // it to set.

    try {
      File employeeFile = new File(employeeFilePath);
      if (employeeFile.createNewFile() == false) { // employee.txt already exists
        FileReader fileReader = new FileReader(employeeFilePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String employee = "";
        while ((employee = bufferedReader.readLine()) != null) {

          String[] employeeData = employee.split(", "); // all employee's data

          Employee employeeObject = new Employee();

          employeeObject.setName(employeeData[0].split(" ", 2)[1]); // here we have used limit: 2 in case user has
                                                                    // entered
                                                                    // full name(first name + last name)
          employeeObject.setAge(Integer.parseInt(employeeData[1].split(" ")[1]));
          employeeObject.setEmail(employeeData[2].split(" ")[1]);
          employeeObject.setDate(employeeData[3].split(" ")[3]);

          set.add(employeeObject);

        }

        bufferedReader.close();
        fileReader.close();
      }

    } catch (IOException err) {
      System.out.println("some error occurred while reading data from the file. - " + err);
    }

  }

  static void showAllEmployees() {
    if (set.size() == 0) {
      System.out.println("No employee exists yet. To add an employee enter 1.\n");
      return;
    }
    System.out.println("Employee's data:");
    for (Employee employee : set) {
      System.out.println(employee.toSring());
    }
    System.out.println();
  }

  static ArrayList<Employee> searchAnEmployee() throws InputMismatchException {
    System.out.println("Choose which field to search:");
    System.out.println("Enter 1 for name");
    System.out.println("Enter 2 for age");
    System.out.println("Enter 3 for email");
    System.out.println("Enter 4 for date of birth");
    
    int fieldNameToSearch = sc.nextInt();
    switch (fieldNameToSearch) {
      case 1:
        String name = enterName();
        ArrayList<Employee> matchedNames = whetherNameExists(name);
        if (matchedNames.size() == 0) {
          System.out.println("Entered name doesn't exists.");
        } else {
          return matchedNames;
        }
        break;

        case 2:
        String age = enterAge();
        ArrayList<Employee> matchedAges = whetherAgeExists(age);
        if (matchedAges.size() == 0) {
          System.out.println("Entered age doesn't exists.");
        } else {
          return matchedAges;
        }
        break;

        case 3:
        String email = enterEmail();
        if(!whetherEmailExists(email)){
          System.out.println("Entered email doesn't exists.");
        }
        else{
          ArrayList<Employee> matchedEmails = new ArrayList<>();
          for(Employee employee: set){
            if(employee.getEmail().equals(email)){
              matchedEmails.add(employee);
            }
          }
          return matchedEmails;
        }
        break;

        case 4:
        String dateOfBirth = enterDateOfBirth();
        ArrayList<Employee> matchedDates = whetherDateOfBirthExists(dateOfBirth);
        if (matchedDates.size() == 0) {
          System.out.println("Entered date of birth doesn't exists.");
        } else {
          return matchedDates;
        }
        break;
        

      default:
        System.out.println("Invalid input.");
    }
    return new ArrayList<>();
  }

  static String enterSortByField() {
    System.out.println("Enter the field to sort by: (name, age, dateOfBirth)");
    String sortBY = sc.next();

    if (!sortBY.equals("name") && !sortBY.equals("age") && !sortBY.equalsIgnoreCase("dateOfBirth")) {
      System.out.println("Please enter 'name', 'age' or 'date of birth' as sort by input value\n");
      return enterSortByField();
    }

    return sortBY;
  }

  static String enterDirection() {
    System.out.println("Enter the direction to sort (ascending or descending): ");
    String direction = sc.next(); // ascending or descending

    if (!direction.equals("ascending") && !direction.equals("descending")) {
      System.out.println("Either enter ascending or descending as direction input value\n");
      return enterDirection();
    }
    return direction;
  }

  static void sortEmployees() {
    ArrayList<Employee> matchedResults = searchAnEmployee();

    String sortBY = enterSortByField();
    String direction = enterDirection();

    if (sortBY.equals("name")) {
      matchedResults.sort(Comparator.comparing(Employee::getName,
          direction.equals("ascending") ? Comparator.naturalOrder() : Comparator.reverseOrder()));
    } else if (sortBY.equals("age")) {
      matchedResults.sort(Comparator.comparing(Employee::getAge,
          direction.equals("ascending") ? Comparator.naturalOrder() : Comparator.reverseOrder()));
    } else if (sortBY.equalsIgnoreCase("date Of Birth")) {
      matchedResults.sort(Comparator.comparing(Employee::getDateOfBirth,
          direction.equals("ascending") ? Comparator.naturalOrder() : Comparator.reverseOrder()));
    }

    for (Employee employee : matchedResults) {
      System.out.println(employee.toSring());
    }
    System.out.println();
  }

  public static void main(String[] args) {

    fetch(); // if employee.txt already exists then fetch its data

    while (true) {
      System.out.println("Enter 1 to add data");
      System.out.println("Enter 2 to delete data");
      System.out.println("Enter 3 to search and sort employees");
      System.out.println("Enter 4 to show all the employees");
      System.out.println("Enter 5 to exit the program");
      System.out.println();

      try {
        Scanner scn = new Scanner(System.in);
        int choice = scn.nextInt();

        switch (choice) {
          case 1:
            add(); // add an employee
            break;

          case 2:
            delete(); // delete an employee
            break;

          case 3:
            sortEmployees(); // search and sort an employee
            break;

          case 4:
            showAllEmployees(); // show all employees
            break;

          case 5:
            System.out.println("-------------------------------\n");
            System.out.println("Program is terminated successfully");
            return;

          default:
            System.out.println("-------------------------------\n");
            System.out.println("Please enter a valid number\n");

        }

      } catch (InputMismatchException e) {
        System.out.println("Please enter a valid number.");
      }

    }

  }

}