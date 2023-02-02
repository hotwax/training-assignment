import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.util.InputMismatchException;

public class Employee {
    public int id;
    public String name;
    public int age;
    public String email;
    public String dob;
    ArrayList<Employee> employeeData = new ArrayList<Employee>();

    public Employee() {
    }

    public Employee (int id, String name, int age, String email, String dob){
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

    public void writeInFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Employee/src/Employee.txt"));
            for (Employee employee : employeeData) {
                writer.write(employee.id + ", " + employee.name + ", " + employee.age + ", " + employee.email + ", " + employee.dob);
                writer.newLine();
            }
            writer.close();
        } catch (Exception exc) {
            System.out.println("Not able to write in the file.");
        }
    }

    public void addEmployee(int id, String name, int age, String email, String dob) {
        if (checkIdInData(id) == true) {
            System.out.println("Employee data already exists.");
        } else {
            setId(id);
            setName(name);
            setAge(age);
            setEmail(email);
            setDob(dob);
            Employee employee = new Employee(id, name, age, email, dob);
            employeeData.add(employee);
            writeInFile();
            System.out.println("Employee data added successfully.");
        }
    }

    public boolean checkIdInData(int id) {
        for (Employee employee : employeeData) {
            if (employee.id == id) {
                return true;
            }
        }
        return false;
    }

    public void deleteEmployee(int id) {
        if (checkIdInData(id) == false) {
            System.out.println("Employee data does not exists.");
        }
        else{
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

    public void searchEmployee(int id, String name) {
        for (Employee employee : employeeData) {
            if (employee.id == id && employee.name == name) {
                employeeData.remove(employee);
                System.out.println("Employee data deleted successfully.");
            }
        }
    }

    public void showAllEmployee() {
        for (Employee employee : employeeData) {
            System.out.println();
            System.out.println("Id: " + employee.id);
            System.out.println("Name: " + employee.name);
            System.out.println("Age: " + employee.age);
            System.out.println("Email: " + employee.email);
            System.out.println("Date of birth: " + employee.dob);
        }
        System.out.println();
    }
}

class ManageEmployee{
    
    public static void main(String[] args) throws Exception {
        Employee employee = new Employee();
        BufferedReader reader = new BufferedReader(new FileReader("Employee/src/Employee.txt"));
        String line;
        boolean flag = true;
        try{
            while ((line = reader.readLine()) != null){
                String str[] = line.split(", ");
                Employee emp = new Employee(Integer.parseInt(str[0]), str[1], Integer.parseInt(str[2]), str[3], str[4]);
                employee.employeeData.add(emp);
            }
        }catch (Exception exc){  
            System.out.println("Not able to read the file.");
        }
        while (flag) {
            try{
                System.out.println("Choose an option: ");
                System.out.println("1. Add a new employee data.");
                System.out.println("2. Delete an employee data.");
                System.out.println("3. Search an employee data.");
                System.out.println("4. Show all employee data.");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                Scanner sc = new Scanner(System.in);
                int choice = sc.nextInt();
                switch(choice){
                    case 1:
                        System.out.println();
                        System.out.println("Enter the id of the employee: ");
                        int id = sc.nextInt();
                        System.out.println("Enter the name of the employee: ");
                        String name = sc.next();
                        System.out.println("Enter the age of the employee: ");
                        int age = sc.nextInt();
                        System.out.println("Enter the email of the employee: ");
                        String email = sc.next();
                        System.out.println("Enter the date of birth of the employee: ");
                        String dob = sc.next();
                        System.out.println();
                        employee.addEmployee(id, name, age, email, dob);
                        System.out.println();
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("Enter the id of the employee: ");
                        int emp_id = sc.nextInt();
                        System.out.println();
                        employee.deleteEmployee(emp_id);
                        System.out.println();
                        break;
                    case 3:
                        System.out.println();
                        System.out.println("Enter the id of the employee: ");
                        id = sc.nextInt();
                        System.out.println("Enter the name of the employee: ");
                        name = sc.next();
                        System.out.println();
                        employee.searchEmployee(id, name);
                        System.out.println();
                        break;
                    case 4:
                        employee.showAllEmployee();
                        break;
                    case 5:    
                        flag = false;
                        break;
                }
            }catch (InputMismatchException e){
                System.out.println("Wrong input.");
            }
        }
    }
}