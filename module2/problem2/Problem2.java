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

    System.out.println("Enter name of the employee: ");
    String name = sc.next();
    System.out.println("Enter age of the employee: ");
    int age = sc.nextInt();
    System.out.println("Enter email of the employee: ");
    String email = sc.next();
    System.out.println("Enter date of birth of the employee: (format- yyyy-mm-dd)");
    String dateOfBirth = sc.next();

    Employee emp = new Employee();
    emp.setName(name);
    emp.setAge(age);
    emp.setEmail(email);
    emp.setDate(dateOfBirth);

    set.add(emp);

    addToFile(emp);

    System.out.println("Data is inserted successfully.");
    System.out.println("-------------------------------\n");

  }

  static void addToFile(Employee emp) {
    try {
      File file = new File("D:\\Nidhi pal\\hotwax\\training-assignment\\module2\\problem2\\employees.txt");
      file.createNewFile();
      FileWriter fw = new FileWriter("D:\\Nidhi pal\\hotwax\\training-assignment\\module2\\problem2\\employees.txt",
          true);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.newLine();
      bw.write("Name: " + emp.getName() + ", Age: " + emp.getAge() + ", Email: " + emp.getEmail() + ", Date of birth: "
          + emp.getDateOfBirth());

      bw.close();

      if (Desktop.isDesktopSupported()) {
        Desktop desktop = Desktop.getDesktop();
        desktop.open(file);
      }
      fw.close();

    } catch (Exception e) {
      System.out.println(e);
    }

  }

  static void delete() {
    System.out.println("Enter the email id of the employee: ");
    String emailId = sc.next();

    Iterator<Employee> itr = set.iterator();
    while (itr.hasNext()) {
      Employee employee = itr.next();
      if (employee.getEmail().equals(emailId)) {
        set.remove(employee);
        break;
      }
    }

    try {
      File file = new File("D:\\Nidhi pal\\hotwax\\training-assignment\\module2\\problem2\\employees.txt");
      FileWriter fw = new FileWriter("D:\\Nidhi pal\\hotwax\\training-assignment\\module2\\problem2\\employees.txt");

      BufferedWriter bw = new BufferedWriter(fw);

      for (Employee emp : set) {
        bw.newLine();
        bw.write("Name: " + emp.getName() + ", Age: " + emp.getAge() + ", Email: " + emp.getEmail()
            + ", Date of birth: " + emp.getDateOfBirth());

      }

      bw.close();

      System.out.println("Employee " + emailId + " is removed successfully.");
      System.out.println("-------------------------------\n");
      Desktop desktop = Desktop.getDesktop();
      desktop.open(file);
      fw.close();

    } catch (Exception e) {
      System.out.println(e);
    }

  }

  static void fetch() {
    try {
      File file = new File("D:\\Nidhi pal\\hotwax\\training-assignment\\module2\\problem2\\employees.txt");
      if (file.createNewFile() == false) {
        FileReader fr = new FileReader("D:\\Nidhi pal\\hotwax\\training-assignment\\module2\\problem2\\employees.txt");
        BufferedReader br = new BufferedReader(fr);
        String s = "";
        int i = 0;
        while ((s = br.readLine()) != null) {
          if (i == 0) {

          } else {
            String[] ss = s.split(", ");

            Employee emp = new Employee();
            emp.setName(ss[0].split(" ")[1]);
            emp.setAge(Integer.parseInt(ss[1].split(" ")[1]));
            emp.setEmail(ss[2].split(" ")[1]);
            emp.setDate(ss[3].split(" ")[3]);

            set.add(emp);

          }

          i++;

        }

      }

    } catch (Exception e) {
      System.out.println(e);
    }

  }

  static void searchAndSort() {
    System.out.println("Enter which field to search: (name, age, date of birth)");
    String field = sc.next(); // name, age, dateOfBirth

    System.out.println("Enter the field value to search: ");
    String fieldName = sc.next(); // if field is name- eg: Nidhi, if field is age- eg: 20

    System.out.println("Enter which field to sort by: (name, age, date of birth)");
    String sortBY = sc.next(); // any field

    System.out.println("Enter the direction to sort (ascending or descending): ");
    String direction = sc.next(); // ascending or descending

    ArrayList<Employee> al = new ArrayList<>();

    if (field.equals("name")) {
      for (Employee employee : set) {
        if (employee.getName().equals(fieldName))
          al.add(employee);
      }
    } else if (field.equals("age")) {
      for (Employee employee : set) {
        if (employee.getAge() == Integer.parseInt(fieldName))
          al.add(employee);
      }
    } else if (field.equals("dateOfBirth")) {
      for (Employee employee : set) {
        if (employee.getDateOfBirth().toString().equals(fieldName))
          al.add(employee);
      }
    }

    if (sortBY.equals("name")) {
      al.sort(Comparator.comparing(Employee::getName,
          direction.equals("ascending") ? Comparator.naturalOrder() : Comparator.reverseOrder()));
    } else if (sortBY.equals("age")) {
      al.sort(Comparator.comparing(Employee::getAge,
          direction.equals("ascending") ? Comparator.naturalOrder() : Comparator.reverseOrder()));
    } else if (sortBY.equals("dateOfBirth")) {
      al.sort(Comparator.comparing(Employee::getDateOfBirth,
          direction.equals("ascending") ? Comparator.naturalOrder() : Comparator.reverseOrder()));
    }

    for (Employee emp : al) {
      System.out.println(
          "Name: " + emp.getName() + ", Age: " + emp.getAge() + ", Email: " + emp.getEmail() + ", Date of birth: "
              + emp.getDateOfBirth());
    }
    System.out.println("-------------------------------\n");

  }

  public static void main(String[] args) {

    fetch();

    Scanner sc = new Scanner(System.in);

    while (true) {
      System.out.println("Enter 1 to add data");
      System.out.println("Enter 2 to delete data");
      System.out.println("Enter 3 to search and sort data");
      System.out.println();
      
      int n = sc.nextInt();
      if (n == 1) { // add
        add();
      } else if (n == 2) { // delete
        delete();
      } else if (n == 3) { // search and sort
        searchAndSort();
      } else {
        System.out.println("-------------------------------\n");
        System.out.println("Program is terminated successfully");
        return;
      }
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
