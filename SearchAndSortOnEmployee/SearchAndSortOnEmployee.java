import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.awt.Desktop;

public class SearchAndSortOnEmployee {

  static Scanner sc = new Scanner(System.in);

  static HashSet<Employee> set = new HashSet<>(); 
  // to store employees objects, hashset because it doesn't allow duplicate entries

  static void add() {  
    // here we will take employee's details from user, create an employee object, add object to set and call addToFile method

    try {
      System.out.println("Enter name of the employee: ");
      String name = sc.next();
      System.out.println("Enter age of the employee: ");
      int age = sc.nextInt();
      System.out.println("Enter email of the employee: ");
      String email = sc.next();
      System.out.println("Enter date of birth of the employee: (format- yyyy-mm-dd)");
      String dateOfBirth = sc.next();

      Employee employeeObject = new Employee();
      employeeObject.setName(name);
      employeeObject.setAge(age);
      employeeObject.setEmail(email);
      employeeObject.setDate(dateOfBirth);

      set.add(employeeObject);

      addToFile(employeeObject); // file handling

      System.out.println("Data is inserted successfully.");
      System.out.println("-------------------------------\n");
    } catch (Exception e) {
      System.out.println(e);
    }

  }

  static void addToFile(Employee employeeObject) { 
    // here we will perform file handling and add the employee object to employee.txt 

    try {
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

      if (Desktop.isDesktopSupported()) { // Desktop class is used to open employee.txt file after adding data to the file
        Desktop desktop = Desktop.getDesktop();
        desktop.open(employeefile);
      }

    } catch (Exception e) {
      System.out.println(e);
    }

  }

  static void delete() {
    // here we will first remove the specified employee from set and rewrite the set data to employee.txt file
    try {
      System.out.println("Enter the email id of the employee: ");
      String emailId = sc.next();

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
      if (Desktop.isDesktopSupported()) { // Desktop class is used to open employee.txt file after adding data to the file
        Desktop desktop = Desktop.getDesktop();
        desktop.open(employeeFile);
      }

    } catch (Exception e) {
      System.out.println(e);
    }

  }

  static void fetch() { 
    // if employee.txt file is already present then we will fetch its data and add it to set.
    try {
      File employeeFile = new File("employees.txt");
      if (employeeFile.createNewFile() == false) { // employee.txt already exists
        FileReader fileReader = new FileReader("employees.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String employee = "";
        while ((employee = bufferedReader.readLine()) != null) {

          String[] employeeData = employee.split(", "); // all employee's data

          Employee employeeObject = new Employee();
          employeeObject.setName(employeeData[0].split(" ")[1]);
          employeeObject.setAge(Integer.parseInt(employeeData[1].split(" ")[1]));
          employeeObject.setEmail(employeeData[2].split(" ")[1]);
          employeeObject.setDate(employeeData[3].split(" ")[3]);

          set.add(employeeObject);

        }

        bufferedReader.close();
      }

    } catch (Exception e) {
      System.out.println(e);
    }

  }

  static void searchAndSort() {
    try {
      System.out.println("Enter which field to search: (name, age, date of birth)");
      String field = sc.next(); // name, age, dateOfBirth

      System.out.println("Enter the field value to search: ");
      String fieldName = sc.next(); // if field is name- eg: Nidhi, if field is age- eg: 20

      System.out.println("Enter which field to sort by: (name, age, date of birth)");
      String sortBY = sc.next(); // any field

      System.out.println("Enter the direction to sort (ascending or descending): ");
      String direction = sc.next(); // ascending or descending

      ArrayList<Employee> resultOfSearchAndSort = new ArrayList<>(); 
      // if user has chosen field as "name" and fieldName as "Ram" then all employees with "Ram" name
      // will get added to this arraylist

      if (field.equals("name")) {
        for (Employee employee : set) {
          if (employee.getName().equals(fieldName))
            resultOfSearchAndSort.add(employee);
        }
      } else if (field.equals("age")) {
        for (Employee employee : set) {
          if (employee.getAge() == Integer.parseInt(fieldName))
            resultOfSearchAndSort.add(employee);
        }
      } else if (field.equals("dateOfBirth")) {
        for (Employee employee : set) {
          if (employee.getDateOfBirth().toString().equals(fieldName))
            resultOfSearchAndSort.add(employee);
        }
      }

      // to sort the resultOfSearchAndSort arraylist data in ascending or descending order.
      if (sortBY.equals("name")) {
        resultOfSearchAndSort.sort(Comparator.comparing(Employee::getName,
            direction.equals("ascending") ? Comparator.naturalOrder() : Comparator.reverseOrder()));
      } else if (sortBY.equals("age")) {
        resultOfSearchAndSort.sort(Comparator.comparing(Employee::getAge,
            direction.equals("ascending") ? Comparator.naturalOrder() : Comparator.reverseOrder()));
      } else if (sortBY.equals("dateOfBirth")) {
        resultOfSearchAndSort.sort(Comparator.comparing(Employee::getDateOfBirth,
            direction.equals("ascending") ? Comparator.naturalOrder() : Comparator.reverseOrder()));
      }

      //display the result obtained of resultOfSearchAndSort
      for (Employee employeeObj : resultOfSearchAndSort) {
        System.out.println(
            "Name: " + employeeObj.getName() + ", Age: " + employeeObj.getAge() + ", Email: " + employeeObj.getEmail()
                + ", Date of birth: "
                + employeeObj.getDateOfBirth());
      }
      System.out.println("-------------------------------\n");

    } catch (Exception e) {
      System.out.println(e);
    }

  }

  public static void main(String[] args) {

    try {
      fetch(); // if employee.txt already exists then fetch its data

      while (true) {
        System.out.println("Enter 1 to add data");
        System.out.println("Enter 2 to delete data");
        System.out.println("Enter 3 to search and sort data");
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

          default:
            System.out.println("-------------------------------\n");
            System.out.println("Program is terminated successfully");
            return;
        }

      }

    } catch (Exception e) {
      System.out.println(e);
    }

  }

}

class Employee {
  private String name;
  private int age;
  private String email;
  private Date dateOfBirth;

  Employee() {

  }

  // setters

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setDate(String dateString) {
    Date dateObject = Date.valueOf(dateString);
    this.dateOfBirth = dateObject;
  }

  // getters
  public String getName() {
    return this.name;
  }

  public int getAge() {
    return this.age;
  }

  public String getEmail() {
    return this.email;
  }

  public Date getDateOfBirth() {
    return this.dateOfBirth;
  }

}
