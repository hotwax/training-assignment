package Employee_class;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Comparator;
import java.util.Scanner;


public class EmployeeOperation {
    static int id;
    private static List<Employee> employees = new ArrayList<Employee>();
    private static SimpleDateFormat sdf=new SimpleDateFormat("dd/mm/yyyy");
    
    public static void main (String[] args) throws ParseException{
        loadEmployeesFromFile();
        System.out.println(employees);
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println();
            System.out.println("Menu:");
            System.out.println("1. Add");
            System.out.println("2. Delete");
            System.out.println("3. Search");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    deleteEmployee();
                    break;
                case 3:
                    searchEmployee();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }
    
    public static void addEmployee()throws ParseException{
        Scanner sc=new Scanner(System.in);
        System.out.println("enter Name : ");
        String name=sc.nextLine();
        System.out.println("enter email : ");
        String email=sc.nextLine();
        System.out.println("enter age : ");
        int age=Integer.parseInt(sc.nextLine());
        System.out.println("enter date of birth (dd/MM/yyyy): ");
        String dobString=sc.nextLine();
        Date dob = sdf.parse(dobString);
        Employee employee = new Employee(name, email, age, dob);
        if(!employees.contains(employee)){
            employees.add(employee);
            saveEmployeesToFile();
        }
    }
    private static void saveEmployeesToFile() {
        try{
            FileWriter writer = new FileWriter("E:\\Work\\java work\\Module2\\src\\Employee_class\\Stored data.txt");
            BufferedWriter bw = new BufferedWriter(writer);
            for (Employee employee : employees) {
                System.out.println(employee);
                String line = employee.toString();
                bw.write(line);
                bw.newLine();
            }
            bw.close();
        }
        catch (Exception e) {
            System.out.println("Error saving employees to file: " + e.getMessage());
        }
    }

    
    //-------------------
    private static void deleteEmployee() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter employee email: ");
        String email = sc.nextLine();
        Iterator<Employee> itr = employees.iterator();
        while(itr.hasNext()){
            Employee emp = itr.next();
            if(emp.getEmailAddress().equals(email)){
                itr.remove();
                System.out.println("Employee deleted.");
                break;
            }
        }
        saveEmployeesToFile();
    }
    private static void loadEmployeesFromFile(){
        try{
            String line;
            int lastId=0;
            BufferedReader br = new BufferedReader(new FileReader("E:\\Work\\java work\\Module2\\src\\Employee_class\\Stored data.txt"));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(", ");
                int fetchedId=Integer.parseInt(data[0]);
                String name = data[1];
                String email = data[2];
                int age = Integer.parseInt(data[3]);
                Date dob = sdf.parse(data[4]);
                Employee employee = new Employee(name, email, age, dob);
                employees.add(employee);
                System.out.println(employee);
                lastId=employee.getId();
            }
            id=++lastId;
        }
        catch (Exception e) {
            System.out.println("Error loading employees from file: " + e.getMessage());
        }
    }
    private static void searchEmployee() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter query: ");
        String query = sc.nextLine();
        System.out.print("Enter sort order (name/age/dob): ");
        String sortOrder = sc.nextLine();
        System.out.print("Enter sort direction (asc/desc): ");
        String sortDirection = sc.nextLine();
        boolean isAscending = sortDirection.equals("asc");
//        boolean isAge=true;
//        try{
//            Integer.paresInt()
//            
//        }
//        catch{
//            
//        }
        System.out.println("Result :-");
        List<Employee> result = new ArrayList<Employee>();
        for (Employee employee : employees) {
            String search=employee.getName()+" "+employee.getEmailAddress()+" "+employee.getAge()+" "+employee.getDateOfBirth();
            if (search.contains(query)) {
                result.add(employee);
            }
        }

        if (sortOrder.equals("name")) {
            result.sort(
                    Comparator.comparing(Employee::getName,
                            isAscending ? Comparator.naturalOrder() : Comparator.reverseOrder()));
        } else if (sortOrder.equals("age")) {
            result.sort(
                    Comparator.comparing(Employee::getAge,
                            isAscending ? Comparator.naturalOrder() : Comparator.reverseOrder()));
        } else if (sortOrder.equals("dob")) {
            result.sort(Comparator.comparing(Employee::getDateOfBirth,
                    isAscending ? Comparator.naturalOrder() : Comparator.reverseOrder()));
        }

        for (Employee employee : result) {
            System.out.println(employee);
        }
    }

}
