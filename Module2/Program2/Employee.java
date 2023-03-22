package Program2;

// Importing packages required in the program.
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.util.Comparator;
import java.util.Collections;
import java.util.InputMismatchException;

//Employee class
class Employee {
    int empID; //Unique ID of an employee.
    String name; //Name of the employee
    String emailAddress; //Mail address of the employee.
    int age; //Age of the employee.
    Date dateOfBirth;
    // Constructor
    public Employee(int empID,String name, String emailAddress, int age, Date dateOfBirth) {
        this.empID = empID;
        this.name = name;
        this.emailAddress = emailAddress;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
    }
    //Getters and setters method for their respective fields.
    // Method to get Employee ID
    public int getEmpID() {
        return empID;
    }
    // Method to set Employee ID
    public void setEmpID(int empID) {
        this.empID = empID;
    }
    // Method to get Employee Name
    public String getName() {
        return name;
    }
    // Method to set Employee Name
    public void setName(String name) {
        this.name = name;
    }
    // Method to get Employee emailAddress
    public String getemailAddress() {
        return emailAddress;
    }
    // Method to set Employee emailAddress
    public void setemailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    // Method to get Employee Age
    public int getAge() {
        return age;
    }
    // Method to set Employee Age
    public void setAge(int age) {
        this.age = age;
    }
    // Method to get Employee Date of Birth
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    // Method to set Employee Date of Birth
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    @Override
    public String toString() {
        return "Employee{" +
                "empID=" + empID +
                ", name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", age=" + age +
                ", dateOfBirth='" + Main.simpleDateFormat.format(dateOfBirth) + '\'' +
                '}';
    }
}
// Main Class
class Main{
    // ArrayList to store Employee Details
    static ArrayList<Employee> employeeList = new ArrayList<>();
    // Variable to store Employee ID uniquely
    static int id = 101;
    // Scanner to take input
    static Scanner sc = new Scanner(System.in);
    // SimpleDateFormat to format Date
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");


    public static void main(String[] args) {
        try {
            // File to read Employee Details
            File file = new File("src/Program2/Employee.txt");
            Scanner fileReader = new Scanner(file);
            // Reading Employee Details from File
            while (fileReader.hasNextLine()) {
                // Splitting Employee Details by comma
                String[] obj = fileReader.nextLine().split(",");
                // Parsing Employee Details
                int empid = Integer.parseInt(obj[0]);
                String name = obj[1];
                String emailAddress = obj[2];
                int age = Integer.parseInt(obj[3]);
                Date dob = simpleDateFormat.parse(obj[4]);
                // Creating Employee Object
                Employee emp = new Employee(empid, name, emailAddress, age, dob);
                // Adding Employee Object to ArrayList
                employeeList.add(emp);
                // Setting Employee ID
                id = 1 + Math.max(emp.getEmpID(), id);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not Found!!!! Kindly give the correct path...");
            e.printStackTrace();
        }
        catch (ParseException e){
            System.out.println("Kindly give the correct format to evaluate.");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        while (true) {
            System.out.println("Choose an option from the menu given below:");
            System.out.println("Type Add    : To Insert Record");
            System.out.println("Type Delete : To Delete Record");
            System.out.println("Type Search : To Search Record");
            System.out.println("Type display : To Display Records");
            System.out.println("Type EXIT : To Display Records");
            System.out.println("Enter your selection ");
            try {
                Scanner sc = new Scanner(System.in);
                String selection = sc.next().toLowerCase();

                switch (selection) {
                    case "add":
                        addEmployee();
                        System.out.println("Employee Added Successfully!!");
                        break;
                    case "delete":
                        System.out.println("Enter Employee ID: ");
                        System.out.println("Employee" + (deleteEmployee(sc.nextInt()) ? "Not Found" : "Deleted Successfully"));
                        break;
                    case "search":
                        search();
                        break;
                    case "display":
                        display();
                        break;
                    case "EXIT":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid selection");
                }

            } catch (InputMismatchException e) {
                System.out.println("Kindly choose your options from the displayed dashboard!!");
            }
        }
    }
    // Comparator to sort Employee Object by Employee ID
    public static class OrderbyId implements Comparator<Employee> {
        @Override
        public int compare(Employee e1, Employee e2) {
            return e1.empID - e2.empID;
        }
    }
    // Comparator to sort Employee Object by Employee Name
    public static class OrderbyName implements Comparator<Employee> {
        @Override
        public int compare(Employee o1, Employee o2) {
            return o1.name.compareTo(o2.name);
        }
    }
    // Comparator to sort Employee Object by Employee emailAddress
    public static class OrderbyemailAddress implements Comparator<Employee> {
        @Override
        public int compare(Employee o1, Employee o2) {
            return o1.emailAddress.compareTo(o2.emailAddress);
        }
    }
    // Comparator to sort Employee Object by Employee Age
    static class OrderbyAge implements Comparator<Employee> {
        @Override
        public int compare(Employee o1, Employee o2) {
            return o1.age - o2.age;
        }
    }
    // Comparator to sort Employee Object by Employee Date of Birth
    static class OrderbyDOB implements Comparator<Employee> {
        @Override
        public int compare(Employee o1, Employee o2) {
            return o1.dateOfBirth.compareTo(o2.dateOfBirth);
        }
    }
    // Method to search Employee
    public static ArrayList<Employee> search() {
        // ArrayList to store Employee Details
        ArrayList<Employee> foundEmployee = new ArrayList<>();

        System.out.print("Enter Text to Search: ");
        BufferedReader queryReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String query = queryReader.readLine().toLowerCase();
            for(Employee emp : employeeList){
                // Converting Employee Details to String
                String empString = emp.getEmpID()+emp.getName()+emp.getemailAddress()+emp.getAge()+ simpleDateFormat.format(emp.getDateOfBirth());
                // Checking if Employee Details contains query
                if(empString.contains(query))
                    foundEmployee.add(emp);
            }

            System.out.println("Enter the option how you want to sort the records: ");
            System.out.println("Type id : To Sort the records by ID");
            System.out.println("Type name : To Sort the records  by Name");
            System.out.println("Type emailAddress : To Sort the records  by emailAddress");
            System.out.println("Type age : To Sort the records  by Age");
            System.out.println("Type dob : To Sort the records  by Date of Birth");
            String selection = sc.next().toLowerCase();
            // Sorting Employee Details
            switch (selection){
                case "id" :
                    foundEmployee.sort(new OrderbyId());
                    break;
                case "name":
                    foundEmployee.sort(new OrderbyName());
                    break;
                case "emailAddress":
                    foundEmployee.sort(new OrderbyemailAddress());
                    break;
                case "age":
                    foundEmployee.sort(new OrderbyAge());
                    break;
                case "dob":
                    foundEmployee.sort(new OrderbyDOB());
                    break;
                default:
                    System.out.println("Kindly select from the given options.");
            }
            System.out.println("Enter whether you want the data in increasing or decreasing order: ");
            System.out.println("Type asc : For Ascending");
            System.out.println("Type desc : For Descending");
            selection = sc.next().toLowerCase();

            // Printing Employee Details
            if("asc".equals(selection)){
                for(Employee emp : foundEmployee){
                    System.out.println(emp.toString());
                }
            } else {
                Collections.reverse(foundEmployee);
                for (Employee emp : foundEmployee){
                    System.out.printf(emp.toString());
                }
            }
            return foundEmployee;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // Method to delete Employee
    private static boolean deleteEmployee(int empId) {
        boolean flag = true;
        for(int index = 0; index<employeeList.size(); index++){
            // Looking for Employee ID
            if(employeeList.get(index).getEmpID() == empId){
                employeeList.remove(index);
                flag = false;
            }
        }
        writeFile();
        return flag;
    }
    public static void display(){
        // Printing Employee Details
        for(Employee emp : employeeList){
            System.out.println(emp.toString());
        }
    }
    public static void addEmployee(){
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(inputStreamReader);
        try {
            System.out.print("Enter Name: ");
            String name = br.readLine();
            String stringRegex =  "^[\\p{L} .'-]+$";
            // Checking if Name is valid
            if(name !=null && !name.matches(stringRegex)){
                System.out.println("Please Enter String");
                System.exit(0);
            }

            System.out.println("Enter emailAddress: ");
            String emailAddress =  br.readLine();
            String emailAddressRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";
            // Checking if emailAddress is valid
            if(emailAddress!=null && !emailAddress.matches(emailAddressRegex)){
                System.out.println("Please Enter Valid emailAddress Only");
                System.exit(0);
            }

            System.out.println("Enter age: ");
            int age = Integer.parseInt(br.readLine());
            // Checking if Age is valid
            System.out.println("Enter Date of Birth: ");
            String dateOfBirth = br.readLine();
            // Checking if Date of Birth is valid
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dobFormat = simpleDateFormat.parse(dateOfBirth);
            // Generating new Employee
            Employee newEmp =  new Employee(id,name,emailAddress,age,dobFormat);
            employeeList.add(newEmp);
            writeFile();

        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception e){
            System.out.println("Exception Happened!!!");
            e.printStackTrace();
        }
    }

    private static void writeFile() {
        try {
            // Writing Employee Details to File
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/Program2/Employee.txt"));
            for(Employee emp :employeeList){
                writer.write(emp.getEmpID()+","+emp.getName()+","+emp.getemailAddress()+","+emp.getAge()+","+Main.simpleDateFormat.format(emp.getDateOfBirth())+"\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

