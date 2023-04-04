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


 public static void main(String[] args) {
        try {
            // File to read Employee Details
            File file = new File("empl.txt");
            Scanner fileReader = new Scanner(file);
            // Reading Employee Details from File
            while (fileReader.hasNextLine()) {
                // Splitting Employee Details by comma
                String[] obj = fileReader.nextLine().split(",");
                // Parsing Employee Details
                int empid = Integer.parseInt(obj[0]);
                String name = obj[1];
                String email = obj[2];
                int age = Integer.parseInt(obj[3]);
                Date dob = simpleDateFormat.parse(obj[4]);
                // Creating Employee Object
                Employee emp = new Employee(empid, name, email, age, dob);
                // Adding Employee Object to ArrayList
                employeeList.add(emp);
                // Setting Employee ID
                id = 1 + Math.max(emp.getEmpID(), id);
            }
           } catch (FileNotFoundException e) {
            System.out.println("File not Found");
            e.printStackTrace();
        }
        catch (ParseException e){
            System.out.println("Given Proper Formatting");
        }
        catch (Exception e){
            System.out.println("Something went wrong");
        }
        while (true) {
            System.out.println("--------- MENU ---------");
            System.out.println("Type Add    : To Insert Entry");
            System.out.println("Type Delete : To Delete Entry");
            System.out.println("Type Search : To Search Entry");
            System.out.println("Type display : To Display Entries");
            System.out.println("Enter your choice : ");
            try {
                Scanner sc = new Scanner(System.in);
                String choice = sc.next().toLowerCase();

                switch (choice) {
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
                    case "exit":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Please Enter Mentioned Command Only 👆");
                }

            } catch (InputMismatchException e) {
                System.out.println("Enter Mentioned Command Only!!");
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
    // Comparator to sort Employee Object by Employee Email
    public static class OrderbyEmail implements Comparator<Employee> {
        @Override
        public int compare(Employee o1, Employee o2) {
            return o1.email.compareTo(o2.email);
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
                String empString = emp.getEmpID()+emp.getName()+emp.getEmail()+emp.getAge()+ simpleDateFormat.format(emp.getDateOfBirth());
                // Checking if Employee Details contains query
                if(empString.contains(query))
                    foundEmployee.add(emp);
            }

            System.out.println("Enter the Sorting Type: ");
            System.out.println("Type id : To Sort by ID");
            System.out.println("Type name : To Sort by Name");
            System.out.println("Type email : To Sort by Email");
            System.out.println("Type age : To Sort by Age");
            System.out.println("Type dob : To Sort by Date of Birth");
            String choice = sc.next().toLowerCase();
        // Sorting Employee Details
        switch (choice){
            case "id" :
               foundEmployee.sort(new OrderbyId());
                break;
            case "name":
              foundEmployee.sort(new OrderbyName());
                break;
            case "email":
                foundEmployee.sort(new OrderbyEmail());
                break;
            case "age":
                foundEmployee.sort(new OrderbyAge());
                break;
            case "dob":
                foundEmployee.sort(new OrderbyDOB());
                break;
            default:
                System.out.println("Please Enter Mentioned Field Only!!");
        }
            System.out.println("Enter the Direction Type: ");
            System.out.println("Type asc : For Ascending");
            System.out.println("Type desc : For Descending");
            choice = sc.next().toLowerCase();

            // Printing Employee Details
            if("asc".equals(choice)){
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
        for(int index = 0;index<employeeList.size();index++){
            // Looking for Employee ID
            if(employeeList.get(index).getEmpID() == empId){
                employeeList.remove(index);
                flag = !flag;
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
//        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter Name: ");
            String name = br.readLine();
            String stringRegex =  "^[\\p{L} .'-]+$";
            // Checking if Name is valid
            if(name !=null && !name.matches(stringRegex)){
                System.out.println("Please Enter String");
                System.exit(0);
            }

            // Checking if Email is valid
            System.out.println("Enter Email: ");
            String email =  br.readLine();
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                                "[a-zA-Z0-9_+&*-]+)*@" +
                                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                                "A-Z]{2,7}$";
            // Checking if Email is valid
            if(email!=null && !email.matches(emailRegex)){
                System.out.println("Please Enter Valid Email Only");
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
            Employee newEmp =  new Employee(id,name,email,age,dobFormat);
            employeeList.add(newEmp);
            writeFile();

        } catch (InputMismatchException e) {
            System.out.println("Please Enter Specified Type Only!!");
            e.printStackTrace();
        } catch (Exception e){
            System.out.println("Exception Occurred!!");
            e.printStackTrace();
        }
    }

    private static void writeFile() {
        try {
            // Writing Employee Details to File
            BufferedWriter writer = new BufferedWriter(new FileWriter("empl.txt"));
            for(Employee emp :employeeList){
                writer.write(emp.getEmpID()+","+emp.getName()+","+emp.getEmail()+","+emp.getAge()+","+RunEmployee.simpleDateFormat.format(emp.getDateOfBirth())+"\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }    
} 
