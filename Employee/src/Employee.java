import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Employee class for storing employee data
public class Employee {
    public int id;
    public String name;
    public int age;
    public String email;
    public String dob;
    ArrayList<Employee> employeeData = new ArrayList<Employee>();

    public Employee() {
    }

    // Constructor for employee class
    public Employee(int id, String name, int age, String email, String dob) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.dob = dob;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    // Method to write the data in the file which is stored in the ArrayList
    public void writeInFile() {
        try {
            // BufferedWriter is used to write the data in the file.
            BufferedWriter writer = new BufferedWriter(new FileWriter("Employee/Employee.txt"));
            for (Employee employee : employeeData) {
                writer.write(employee.id + ", " + employee.name + ", " + employee.age + ", " + employee.email + ", "
                        + employee.dob);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Not able to write in the file.");
        }
    }

    // Validate name
    public static boolean isValidName(String name) {
        String regex = "^[A-Za-z]*";
        Pattern pattern = Pattern.compile(regex);
        if (name == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    // Validate email
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pattern.matcher(email).matches();
    }

    // Validate Date
    public static boolean isValidDob(String dob) {
        String regex = "^(((0[1-9]|[12][0-9]|30)[-/]?(0[13-9]|1[012])|31[-/]?(0[13578]|1[02])|(0[1-9]|1[0-9]|2[0-8])[-/]?02)[-/]?[0-9]{4}|29[-/]?02[-/]?([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00))$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher((CharSequence) dob);
        return matcher.matches();
    }

    // Method to add the employee data in the ArrayList
    public void addEmployee(int id, String name, int age, String email, String dob) {
        if (checkIdInData(id)) {
            System.out.println("Employee data already exists.");
        } else {
            setId(id);
            setName(name);
            setAge(age);
            setEmail(email);
            setDob(dob);
            Employee employee = new Employee(id, name, age, email, dob);
            if (!isValidName(name)) {
                System.out.println("Invalid name");
                return;
            }
            if (!isValidEmail(email)) {
                System.out.println("Invalid email");
                return;
            }

            employeeData.add(employee);
            writeInFile();
            System.out.println("Employee data added successfully.");
        }
    }

    // Method to check if the id is already present in the ArrayList or not
    public boolean checkIdInData(int id) {
        for (Employee employee : employeeData) {
            if (employee.id == id) {
                return true;
            }
        }
        return false;
    }

    // Method to delete the employee data from the ArrayList
    public void deleteEmployee(int id) {
        if (!checkIdInData(id)) {
            System.out.println("This Employee data does not exist.");
        } else {
            for (Employee employee : employeeData) {
                if (employee.id == id) {
                    employeeData.remove(employee);
                    writeInFile();
                    System.out.println("Employee data deleted successfully.");
                    break;
                }
            }
        }
    }

    // Method to show all the employee data from the ArrayList
    public void showAllEmployee() {
        for (Employee employee : employeeData) {
            System.out.println();
            System.out.println(
                    employee.id + " " + employee.name + " " + employee.age + " " + employee.email + " " + employee.dob);
        }
        System.out.println();
    }

    // Method to search the employee data from the ArrayList
    public void searchEmployeeData(int id) {
        for (Employee employee : employeeData) {
            if (employee.id == id) {
                System.out.println();
                System.out.println("Id: " + employee.id);
                System.out.println("Name: " + employee.name);
                System.out.println("Age: " + employee.age);
                System.out.println("Email: " + employee.email);
                System.out.println("Date of birth: " + employee.dob);
                System.out.println();
                return;
            }
        }
        System.out.println("This Employee data does not exist.");
    }
}

// Main class
class ManageEmployee {
    public static void main(String[] args) throws Exception {
        Employee employee = new Employee();
        try {
            // BufferedReader is used to read the data from the file.
            BufferedReader reader = new BufferedReader(new FileReader("Employee/Employee.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                String str[] = line.split(", ");
                Employee emp = new Employee(Integer.parseInt(str[0]), str[1], Integer.parseInt(str[2]), str[3], str[4]);
                employee.employeeData.add(emp);
            }
            reader.close();
        } catch (NumberFormatException | FileNotFoundException e) {
            System.out.println("Unable to read the file");
        }
        try (Scanner input = new Scanner(System.in)) {
            int choice;
            do {
                System.out.println("Select an option:");
                System.out.println("1. Add a new employee data.");
                System.out.println("2. Delete an employee data.");
                System.out.println("3. Search an employee data.");
                System.out.println("4. Show all employee data.");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = input.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println();
                        System.out.println("Enter the id of the employee: ");
                        int id = input.nextInt();
                        System.out.println("Enter the name of the employee: ");
                        String name = input.next();
                        System.out.println("Enter the age of the employee: ");
                        int age = input.nextInt();
                        System.out.println("Enter the email of the employee: ");
                        String email = input.next();
                        System.out.println("Enter the date of birth of the employee: ");
                        String dob = input.next();
                        System.out.println();
                        employee.addEmployee(id, name, age, email, dob);
                        System.out.println();
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("Enter the id of the employee: ");
                        int empId = input.nextInt();
                        System.out.println();
                        employee.deleteEmployee(empId);
                        System.out.println();
                        break;
                    case 3:
                        System.out.println();
                        System.out.println("Enter the id of the employee: ");
                        id = input.nextInt();
                        System.out.println();
                        employee.searchEmployeeData(id);
                        System.out.println();
                        break;
                    case 4:
                        employee.showAllEmployee();
                        break;
                    case 5:
                        System.out.println("Exit done");
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } while (choice != 5);
        } catch (InputMismatchException exception) {
            System.out.println(exception);
        }
    }
}