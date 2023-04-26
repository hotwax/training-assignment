import java.util.Comparator;


// Create a Employee Class

public class Employee {

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
      return (getId() + "," + getName() + "," + getEmail() + "," + getAge() + "," + getDOB()+"\n");
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
  
