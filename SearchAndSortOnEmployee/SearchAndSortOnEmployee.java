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

  static void add() throws InputMismatchException, IOException {
    // here we will take employee's details from user, create an employee object,
    // add object to set and call addToFile method

    System.out.println("Enter name of the employee: ");
    sc.nextLine();
    String name = sc.nextLine();

    System.out.println("Enter age of the employee: ");
    String age = sc.next();
    if (!checkAge(age))
      throw new InputMismatchException("Please enter a valid age");

    System.out.println("Enter email of the employee: ");
    String email = sc.next();
    if (!checkEmail(email))
      throw new InputMismatchException("Please enter a valid email");

    System.out.println("Enter date of birth of the employee: (format- yyyy-mm-dd)");
    String dateOfBirth = sc.next();
    if (!checkDateOfBirth(dateOfBirth))
      throw new InputMismatchException("Please enter a valid date");

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

  static void addToFile(Employee employeeObject) throws IOException {
    // here we will perform file handling and add the employee object to
    // employee.txt

    String employeefilePath = "employees.txt";
    File employeefile = new File(employeefilePath);
    employeefile.createNewFile();
    FileWriter fileWriter = new FileWriter(employeefilePath, true); // true- appends to file
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    bufferedWriter.write("Name: " + employeeObject.getName() + ", Age: " + employeeObject.getAge() + ", Email: "
        + employeeObject.getEmail() + ", Date of birth: "
        + employeeObject.getDateOfBirth());

    bufferedWriter.newLine();

    bufferedWriter.close();
    fileWriter.close();

    if (Desktop.isDesktopSupported()) { // Desktop class is used to open employee.txt file after adding data to the
                                        // file
      Desktop desktop = Desktop.getDesktop();
      desktop.open(employeefile);
    }

  }

  static void delete() throws InputMismatchException, IOException {
    // here we will first remove the specified employee from set and rewrite the set
    // data to employee.txt file

    System.out.println("Enter the email id of the employee: ");
    String emailId = sc.next();

    if(!checkEmail(emailId)) throw new InputMismatchException("Please enter a valid");

    Iterator<Employee> iterator = set.iterator();
    while (iterator.hasNext()) { // we have used iterator in place of for loop because java throws an exception
                                 // if we apply loop on a class and remove from the same class in it.
      Employee employee = iterator.next();
      if (employee.getEmail().equals(emailId)) {
        set.remove(employee);
        break;
      }
    }

    String employeeFilePath = "employees.txt";
    File employeeFile = new File(employeeFilePath);
    FileWriter fileWriter = new FileWriter(employeeFilePath);
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

    for (Employee employeeObject : set) {
      bufferedWriter.write("Name: " + employeeObject.getName() + ", Age: " + employeeObject.getAge() + ", Email: "
          + employeeObject.getEmail()
          + ", Date of birth: " + employeeObject.getDateOfBirth());
      bufferedWriter.newLine();

    }

    bufferedWriter.close();
    fileWriter.close();

    System.out.println("Employee " + emailId + " is removed successfully.");
    System.out.println("-------------------------------\n");
    if (Desktop.isDesktopSupported()) { // Desktop class is used to open employee.txt file after adding data to the
                                        // file
      Desktop desktop = Desktop.getDesktop();
      desktop.open(employeeFile);
    }

  }

  static void fetch() throws IOException {
    // if employee.txt file is already present then we will fetch its data and add
    // it to set.

    File employeeFile = new File("employees.txt");
    if (employeeFile.createNewFile() == false) { // employee.txt already exists
      FileReader fileReader = new FileReader("employees.txt");
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String employee = "";
      while ((employee = bufferedReader.readLine()) != null) {

        String[] employeeData = employee.split(", "); // all employee's data

        Employee employeeObject = new Employee();

        employeeObject.setName(employeeData[0].split(" ", 2)[1]); // here we have used limit: 2 in case user has entered
                                                                  // full name(first name + last name)
        employeeObject.setAge(Integer.parseInt(employeeData[1].split(" ")[1]));
        employeeObject.setEmail(employeeData[2].split(" ")[1]);
        employeeObject.setDate(employeeData[3].split(" ")[3]);

        set.add(employeeObject);

      }

      bufferedReader.close();
    }

  }

  static void searchAndSort() throws InputMismatchException {

    System.out.println("Enter the field name to search: (name, age, email, date of birth)");
    sc.nextLine();
    String fieldName = sc.nextLine();

    if (!fieldName.equals("name") && !fieldName.equals("age") && !fieldName.equals("email")
        && !fieldName.equalsIgnoreCase("date of birth")) {
      throw new InputMismatchException("Please enter 'name', 'age', 'email' or 'date Of Birth' as fieldname");
    }

    System.out.println("Enter the field value to search: ");
    String fieldValue = sc.nextLine();

    if (fieldName.equals("age") && !checkAge(fieldValue)) {
      throw new InputMismatchException("Please enter a valid age");
    } else if (fieldName.equalsIgnoreCase("date Of Birth") && !checkDateOfBirth(fieldValue)) {
      throw new InputMismatchException("Please enter a valid date of birth");
    } else if (fieldName.equalsIgnoreCase("email") && !checkEmail(fieldValue)) {
      throw new InputMismatchException("Please enter a valid email");
    }




    ArrayList<Employee> resultOfSearchAndSort = new ArrayList<>();
    // if user has chosen fieldName as "name" and fieldValue as "Ram" then all
    // employees
    // with "Ram" name
    // will get added to this arraylist

    if (fieldName.equals("name")) {
      for (Employee employee : set) {
        if (employee.getName().equals(fieldValue))
          resultOfSearchAndSort.add(employee);
      }
    } else if (fieldName.equals("age")) {
      for (Employee employee : set) {
        if (employee.getAge() == Integer.parseInt(fieldValue))
          resultOfSearchAndSort.add(employee);
      }
    } else if (fieldName.equals("email")) {
      for (Employee employee : set) {
        if (employee.getEmail().equals(fieldValue))
          resultOfSearchAndSort.add(employee);
      }
    } else if (fieldName.equalsIgnoreCase("date Of Birth")) {
      for (Employee employee : set) {
        if (employee.getDateOfBirth().toString().equals(fieldValue))
          resultOfSearchAndSort.add(employee);
      }
    }

    if (resultOfSearchAndSort.size() == 0) {
      System.out.println("\nEmployee with "+fieldValue+" "+fieldName+" doesn't exists.");
      System.out.println("-------------------------------\n");
      return;
    }

    System.out.println("Enter which field to sort by: (name, age, date Of Birth)");
    String sortBY = sc.nextLine();

    if (!sortBY.equals("name") && !sortBY.equals("age") && !sortBY.equalsIgnoreCase("date Of Birth")) {
      throw new InputMismatchException("Please enter 'name', 'age' or 'date of birth'");
    }

    if (fieldName.equalsIgnoreCase(sortBY)) {
      System.out.println("Please enter different fieldname and sort bY input values");
      System.out.println("-------------------------------\n");
      return;
    }

    System.out.println("Enter the direction to sort (ascending or descending): ");
    String direction = sc.next(); // ascending or descending

    if (!direction.equals("ascending") && !direction.equals("descending")) {
      throw new InputMismatchException("Either enter ascending or descending");
    }


    // to sort the resultOfSearchAndSort arraylist data in ascending or descending
    // order.
    if (sortBY.equals("name")) {
      resultOfSearchAndSort.sort(Comparator.comparing(Employee::getName,
          direction.equals("ascending") ? Comparator.naturalOrder() : Comparator.reverseOrder()));
    } else if (sortBY.equals("age")) {
      resultOfSearchAndSort.sort(Comparator.comparing(Employee::getAge,
          direction.equals("ascending") ? Comparator.naturalOrder() : Comparator.reverseOrder()));
    } else if (sortBY.equalsIgnoreCase("date Of Birth")) {
      resultOfSearchAndSort.sort(Comparator.comparing(Employee::getDateOfBirth,
          direction.equals("ascending") ? Comparator.naturalOrder() : Comparator.reverseOrder()));
    }

    // display the result obtained of resultOfSearchAndSort
    System.out.println();
    for (Employee employeeObj : resultOfSearchAndSort) {
      System.out.println(
          "Name: " + employeeObj.getName() + ", Age: " + employeeObj.getAge() + ", Email: " + employeeObj.getEmail()
              + ", Date of birth: "
              + employeeObj.getDateOfBirth());
    }
    System.out.println("-------------------------------\n");

  }

  static boolean checkAge(String age) {
    String regex = "[0-9]+";
    boolean match = age.matches(regex);
    return match;
  }

  static boolean checkDateOfBirth(String dateOfBirth) {
    String regex = "^([0-9][0-9][0-9][0-9])-(0[0-9]||1[0-2])-([0-2][0-9]||3[0-1])$";
    boolean match = dateOfBirth.matches(regex);
    return match;
  }

  static boolean checkEmail(String email) {
    String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    boolean match = email.matches(regex);
    return match;
  }

  public static void main(String[] args) {

    try {
      fetch(); // if employee.txt already exists then fetch its data

      while (true) {
        System.out.println("Enter 1 to add data");
        System.out.println("Enter 2 to delete data");
        System.out.println("Enter 3 to search and sort data");
        System.out.println("Enter 4 to exit the program");
        System.out.println();

        int choice = sc.nextInt();

        switch (choice) {
          case 1:
            add(); // add an employee
            break;

          case 2:
            delete(); // delete an employee
            break;

          case 3:
            searchAndSort(); // search and sort the employees objects
            break;

          case 4:
            System.out.println("-------------------------------\n");
            System.out.println("Program is terminated successfully");
            return;

          default:
            System.out.println("-------------------------------\n");
            System.out.println("Please enter a valid number\n");

        }

      }

    } catch (InputMismatchException e) {
      System.out.println(e);
    } catch (IOException e) {
      System.out.println(e);
    } catch (Exception e) {
      System.out.println(e);
    }

  }

  
}