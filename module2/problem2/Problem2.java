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

public class Problem2 {

  static Scanner sc = new Scanner(System.in);

  static HashSet<Employee> set = new HashSet<>();

  static void add() {

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

      addToFile(employeeObject);

      System.out.println("Data is inserted successfully.");
      System.out.println("-------------------------------\n");
    } catch (Exception e) {
      System.out.println(e);
    }

  }

  static void addToFile(Employee employeeObject) {
    try {
      String employeefilePath = "D:\\Nidhi pal\\hotwax\\training-assignment\\module2\\problem2\\employees.txt";
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

      if (Desktop.isDesktopSupported()) {
        Desktop desktop = Desktop.getDesktop();
        desktop.open(employeefile);
      }

    } catch (Exception e) {
      System.out.println(e);
    }

  }

  static void delete() {
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

      String employeeFilePath = "D:\\Nidhi pal\\hotwax\\training-assignment\\module2\\problem2\\employees.txt";
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
      if (Desktop.isDesktopSupported()) {
        Desktop desktop = Desktop.getDesktop();
        desktop.open(employeeFile);
      }

    } catch (Exception e) {
      System.out.println(e);
    }

  }

  static void fetch() {
    try {
      File employeeFile = new File("D:\\Nidhi pal\\hotwax\\training-assignment\\module2\\problem2\\employees.txt");
      if (employeeFile.createNewFile() == false) { // employee.txt already exists
        FileReader fileReader = new FileReader(
            "D:\\Nidhi pal\\hotwax\\training-assignment\\module2\\problem2\\employees.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String employee = "";
        while ((employee = bufferedReader.readLine()) != null) {

          String[] employeeData = employee.split(", ");

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
            add();
            break;

          case 2:
            delete();
            break;

          case 3:
            searchAndSort();
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
