import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.Collections;
import java.time.LocalDate;  
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.io.IOException;

class Employee {
  static int count = 1; //varible to generate unique id
  private Integer id;
  private String name;
  private String email;
  private LocalDate DOB;
  private Integer age;

  Employee(String name, String email, String DOB, Integer age) //constructor to initialize
  {
    this.name = name;
    this.email = email;
    this.DOB = LocalDate.parse(DOB);
    this.age = age;
    this.id = count++;
  }

  Employee(Integer id, String name, String email, String DOB, Integer age) // constructor overloading
  {
    count = Math.max(count, id + 1); //to get maxium id till now
    this.name = name;
    this.email = email;
    this.DOB = LocalDate.parse(DOB);
    this.age = age;
    this.id = id;
  }

  Integer getID() //getting id
  {
    return id;
  }

  String getName() //getting name
  {
    return name;
  }
  String getEmail() //getting email
  {
    return email;
  }

  String getDOB() //getting DOB
  {
    return ""+DOB;
  }

  Integer getAge() //getting age
  {
    return age;
  }

  void setName(String name) //setting name
  {
    this.name = name;
  }

  void setEmail(String email) //setting email
  {
    this.email = email;
  }

  void setDOB(String DOB) //setting DOB
  {
    this.DOB = LocalDate.parse(DOB);
  }

  void setAge(Integer age) //setting age
  {
    this.age = age;
  }

  public int hashCode() // overriding hashCode method of object class
  {
    return id;
  }

  public boolean equals(Object obj) //ove  equals method of object class
  {
    Employee emp = (Employee) obj;
    if (id == emp.id) return true;
    return false;
  }

  public String toString() // to print data we have stored 
  {
    return "{ ID = " + id + ", Name = " + name + ", Email = " + email + ", DOB = " + DOB + ", Age =" + age + "}\n";
  }

  public static class OrderByID implements Comparator < Employee > //class to sort by id
    {
      public int compare(Employee obj1, Employee obj2) {
        return obj1.id - obj2.id;
      }
    }
  public static class OrderByName implements Comparator < Employee > //inner class to sort by name
    {
      public int compare(Employee obj1, Employee obj2) {
        return obj1.name.compareTo(obj2.name);
      }
    }

  public static class OrderByEmail implements Comparator < Employee > //inner class to sort by email
    {
      public int compare(Employee obj1, Employee obj2) {
        return obj1.email.compareTo(obj2.email);
      }
    }

  public static class OrderByDOB implements Comparator < Employee > //inner class to sort by DOB
    {
      public int compare(Employee obj1, Employee obj2) {
        return obj1.DOB.compareTo(obj2.DOB);
      }
    }

  public static class OrderByAge implements Comparator < Employee > //inner class to sort by Age
    {
      public int compare(Employee obj1, Employee obj2) {
        return obj1.age - obj2.age;
      }
    }

}
class StoreEmployee // A class which file related operations
{
  ArrayList < Employee > alist;

  StoreEmployee() throws Exception //constructor
  {
    get();
  }

  // to search by name,id,email,DOB or age
  ArrayList < Employee > search(String input, String find) 
  {
    ArrayList < Employee > store_emp = new ArrayList < > ();

    if ("ID".equalsIgnoreCase(input)) //searching by id
    {
      for (Employee employee: alist)
        if (employee.getID().equals(Integer.parseInt(find)))
          store_emp.add(employee);
    } else if ("name".equalsIgnoreCase(input)) //searching by name
    {
      for (Employee employee: alist)
        if (employee.getName().equalsIgnoreCase(find))
          store_emp.add(employee);
    } else if ("email".equalsIgnoreCase(input)) //searching by email
    {
      for (Employee employee: alist)
        if (employee.getEmail().equalsIgnoreCase(find))
          store_emp.add(employee);
    } else if ("DOB".equalsIgnoreCase(input)) //searching by DOB
    {
      for (Employee employee: alist)
        if (employee.getDOB().equalsIgnoreCase(find))
          store_emp.add(employee);
    } else if ("age".equalsIgnoreCase(input)) //age
    {
      for (Employee employee: alist)
        if (employee.getAge().equals(Integer.parseInt(find)))
          store_emp.add(employee);
    }

    return store_emp;
  }

  ArrayList < Employee > sort(ArrayList < Employee > emp, String order) //to sort by name, id, email, DOB or age
  {

    if ("ID".equalsIgnoreCase(order)) { //if we have to order by id
      Collections.sort(emp, new Employee.OrderByID());
      return emp;
    } else if ("name".equalsIgnoreCase(order)) { //if we have to order by name
      Collections.sort(emp, new Employee.OrderByName());
      return emp;
    } else if ("email".equalsIgnoreCase(order)) { //if we have to order by email
      Collections.sort(emp, new Employee.OrderByEmail());
      return emp;
    } else if ("DOB".equalsIgnoreCase(order)) { //if we have to order by DOB
      Collections.sort(emp, new Employee.OrderByDOB());
      return emp;
    } else if ("age".equalsIgnoreCase(order)) { //if we have to order by age
      Collections.sort(emp, new Employee.OrderByAge());
      return emp;
    }

    return emp;
  }

  ArrayList < Employee > delete(int data)  //method to delete some record by id only
  {
	for (int index = 0; index < alist.size(); index++) {
      if (alist.get(index).getID().equals(data))
      alist.remove(index);
      insert(alist);
    }
    return alist;
  }

  ArrayList < Employee > get() //method to get data from file
  {
    alist = new ArrayList < > ();

    try
	{
	BufferedReader br = new BufferedReader(new FileReader("input.txt"));
    String line = br.readLine();
    while (line != null) {
    String str[] = line.split(",");
    Employee emp = new Employee(Integer.parseInt(str[0]), str[1], str[2], str[3], Integer.parseInt(str[4]));
    alist.add(emp);
    line = br.readLine();
    }
    br.close();

	}
	catch(IOException exception)
	{
		System.out.println(exception);
	}
    return alist;
  }

  ArrayList < Employee > insert(ArrayList<Employee> list) //method to insert data into file
  {
	  
    try {
      BufferedWriter br = new BufferedWriter(new FileWriter("input.txt"));
      for (Employee emp: list) {
        br.write("" + emp.getID() + "," + emp.getName() + "," + emp.getEmail() + "," + emp.getDOB() + "," + emp.getAge());
        br.newLine();
      }
      br.close();
    } catch (Exception exception) {
		System.out.println(exception);
	}

    return alist;
  }

}

class Demo {
  public static void main(String[] args) throws Exception {
    String name, email, DOB;
    Integer age, id;
	
    StoreEmployee file = new StoreEmployee();
    ArrayList < Employee > list = file.get();
    while (true) {
      System.out.println("===========================");
      System.out.println("*****Enter your choice*****");
      System.out.println("1.Add employee object");
      System.out.println("2.Delete employee object");
      System.out.println("3.ShowAll");
      System.out.println("4.Search");
      System.out.println("5.Exit");
      System.out.println("===========================");
      try {
        Scanner sc = new Scanner(System.in);
        int condition = sc.nextInt();
        switch (condition) {

        case 1:
          InputStreamReader isr = new InputStreamReader(System.in);
          BufferedReader br = new BufferedReader(isr);

          System.out.println("Enter name ");
          name = br.readLine();
		  
          while(true) //while accurate format is not given
		  {
          System.out.println("Enter email");
		  email = sc.next();
		  String regex = "^[a-zA-Z0-9+_.-]+@gmail.com$";
		  boolean res = email.matches(regex);
		  if(!res)
		  System.out.println("\nEnter valid email");
		  if(res)break;
		  }
		  
		  while(true) //while accurate format is not given 
		  {
          System.out.println("Enter DOB (Format : YYYY-MM-DD)");
          DOB = sc.next();
		  String regex = "^([0-9][0-9][0-9][0-9])-(0[0-9]||1[0-2])-([0-2][0-9]||3[0-1])$";
		  boolean res = DOB.matches(regex);
		  if(!res)
		  System.out.println("Enter valid DOB");
		  if(res)break;
		  }
          
          System.out.println("Enter age");
          age = sc.nextInt();

          Employee emp = new Employee(name, email, DOB, age);
		  list.add(emp);
          file.insert(list);
          System.out.println("Done ");
          break;

        case 2:
          System.out.println("Enter ID ");
          id = sc.nextInt();
          file.delete(id);
          System.out.println("Done");
          break;

        case 3:
          ArrayList alist = file.get();
          Collections.sort(alist, new Employee.OrderByID());
          System.out.println(alist);
          break;

        case 4:
          System.out.println("Enter Query-(e.g.) ID,Name, Email, DOB, Age");
          String input = sc.next();

          System.out.println("Enter Search value");
          String find = sc.next();

          ArrayList < Employee > order = file.search(input, find);

          System.out.println("Sort by- (e.g.) ID,Name, Email, DOB, Age");
          String input1 = sc.next();
          order = file.sort(order, input1);

          System.out.println("Direction- (asc/desc) ");
          String dir = sc.next();

          if ("asc".equals(dir))
            System.out.println(order);
          else if ("desc".equals(dir)) {
            Collections.reverse(order);
            System.out.println(order);
          }
          break;

        case 5:
          System.out.println("Thank you");
          System.exit(0);
          break;

        default:
          System.out.println("Invalid");
          break;
        }
      } catch (InputMismatchException exception) {
        System.out.println("Enter valid option");
      }
    }

  }
}