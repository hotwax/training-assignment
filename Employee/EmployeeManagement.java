import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

class Employee  {
    private int id;
    private String name;
    private String email;
    private int age;
    private String dob;
    
    public Employee(int id, String name, String email, int age, String dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.dob = dob;
    }

    public Employee(){}
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public String getDob() {
        return dob;
    }
    
    public void setDob(String dob) {
        this.dob = dob;
    }
    
    @Override
    public String toString() {
        // SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return "ID: " + id + ", Name: " + name + ", Email: " + email + ", Age: " + age + ", DOB: " + dob;
    }
}


public class EmployeeManagement {


    private static final String FILE_NAME = "EmployeeData.txt";
    private static List<Employee> employees;

    public static void main(String[] args) {
        employees = loadEmployeesFromFile();
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        while (!quit) {
            System.out.println("Enter your choice:");
            System.out.println("1. Add an employee");
            System.out.println("2. Delete an employee");
            System.out.println("3. Search employees");
            System.out.println("4. Display All employees");
            System.out.println("5. Quit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addEmployee(scanner);
                    break;
                case 2:
                    deleteEmployee(scanner);
                    break;
                case 3:
                    searchEmployees(scanner);
                    break;
                case 4:
                    displayAllEmployees();
                 break;
                case 5:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    private static List<Employee> loadEmployeesFromFile() {
        List<Employee> employees = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(FILE_NAME))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Employee employee = new Employee();
                    employee.setId(Integer.parseInt(parts[0]));
                    employee.setName(parts[1]);
                    employee.setEmail(parts[2]);
                    employee.setAge(Integer.parseInt(parts[3]));
                    employee.setDob(parts[4]);
                    employees.add(employee);
                }
            }
        } catch (FileNotFoundException e) {
            // If the file is not found, just return an empty list
        }
        return employees;
    }

    private static void saveEmployeesToFile(List<Employee> employees) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Employee employee : employees) {
                writer.println(employee.getId() + "," + employee.getName() + "," + employee.getEmail() + "," + employee.getAge() + "," + employee.getDob());
            }
        } catch (IOException e) {
            System.out.println("Error saving employees to file");
        }
    }

    private static void addEmployee(Scanner scanner) {
        Employee employee = new Employee();
        System.out.print("Enter name: ");
        employee.setName(scanner.nextLine());
        System.out.print("Enter email address: ");
        employee.setEmail(scanner.nextLine());
        System.out.print("Enter age: ");
        employee.setAge(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Enter date of birth (): ");
        employee.setDob(scanner.nextLine());
        employee.setId(generateEmployeeId());
        employees.add(employee);
        saveEmployeesToFile(employees);
        System.out.println("Employee added successfully");
    }

    private static int generateEmployeeId() {
        int maxId = 0;
        for (Employee employee : employees) {
            if (employee.getId() > maxId) {
                maxId = employee.getId();
            }
        }
        return maxId + 1;
    }

    public static void searchEmployees(Scanner scanner) {
    System.out.print("Enter search query: ");
    String query = scanner.nextLine().toLowerCase();
    
    System.out.println("Sort by:");
    System.out.println("1. ID");
    System.out.println("2. Name");
    System.out.println("3. Age");
    System.out.println("4. DOB");
    
    int sortBy = Integer.parseInt(scanner.nextLine());
    
    System.out.println("Sort direction:");
    System.out.println("1. Ascending");
    System.out.println("2. Descending");
    
    int sortDirection = Integer.parseInt(scanner.nextLine());
    
    Comparator<Employee> comparator = null;
    
    switch (sortBy) {
        case 1:
            comparator = Comparator.comparing(Employee::getId);
            break;
        case 2:
            comparator = Comparator.comparing(Employee::getName);
            break;
        case 3:
            comparator = Comparator.comparing(Employee::getAge);
            break;
        case 4:
            comparator = Comparator.comparing(Employee::getDob);
            break;
        default:
            System.out.println("Invalid sort option.");
            return;
    }
    
    if (sortDirection == 2) {
        comparator = comparator.reversed();
    }
    
    List<Employee> results = new ArrayList<>();
    
    for (Employee employee : employees) {
        if (employee.toString().toLowerCase().contains(query)) {
            results.add(employee);
        }
    }
    
    if (results.size() > 0) {
        results.sort(comparator);
        
        System.out.println("Search Results:");
        
        for (Employee employee : results) {
            System.out.println(employee);
        }
    } else {
        System.out.println("No results found.");
    }
}




  private static void deleteEmployee(Scanner scanner) {
    System.out.print("Enter employee ID: ");
    int idToDelete = scanner.nextInt();
    scanner.nextLine();
    boolean deleted = false;
    Iterator<Employee> iterator = employees.iterator();
    while (iterator.hasNext()) {
        Employee employee = iterator.next();
        if (employee.getId() == idToDelete) {
            iterator.remove();
            deleted = true;
            break;
        }
    }
    if (deleted) {
        saveEmployeesToFile(employees);
        System.out.println("Employee deleted successfully");
    } else {
        System.out.println("Employee not found");
    }
}


private static void displayAllEmployees() {
    if (employees.isEmpty()) {
        System.out.println("No employees found.");
        return;
    }

    System.out.println("List of employees:");
    for (Employee employee : employees) {
        System.out.println(employee.toString());
    }
}

}