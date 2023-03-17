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

//this class provide all operration that can be performed on meployee class.
public class EmployeeOperation {

    static int GlobalID;
    //used to store list of employee
    private static List<Employee> employees = new ArrayList<Employee>();
    //date formater used to formate date in formate "dd/mm/yyyy"
    private static SimpleDateFormat dateFormater = new SimpleDateFormat("dd/mm/yyyy");

    public static void main(String[] args) throws ParseException {
        //firstly load all employee from file
        loadEmployeesFromFile();
        //System.out.println(employees);
        //exit flag to end the program
        boolean exit = false;

        while (!exit) {
            System.out.println();
            System.out.println("Menu:");
            System.out.println("1. Add");
            System.out.println("2. Delete");
            System.out.println("3. Search");
            System.out.println("4. Exit");
            System.out.println();
            System.out.print("Enter your choice: ");
            try {
                Scanner sc = new Scanner(System.in);
                int choice = sc.nextInt();
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
            } catch (Exception exception) {
                //used to catch the invalid input entered by user
                System.out.println("Invalid Input: Enter Integer");
            }
        }
    }

    //this method load all employee stored in file to list 
    private static void loadEmployeesFromFile() {
        try {
            String line;
            //this will store the id assigned to the lastly added employee
            int lastId = 0;
            //this buffered reader read all the data from "Stored data.txt" file.
            BufferedReader bufferedReader = new BufferedReader(new FileReader("E:\\Work\\java work\\Module2\\src\\Employee_class\\Stored data.txt"));
            //this loop will reun until all data is fetched from file 
            while ((line = bufferedReader.readLine()) != null) {
                //split the ',' seperated entries entries
                String[] data = line.split(", ");
                //this variable store ID of employee
                int fetchedId = Integer.parseInt(data[0]);
                //this variable store name of employee
                String name = data[1];
                //this variable store email of employee
                String email = data[2];
                //this variable store date of employee
                int age = Integer.parseInt(data[3]);
                //this variable store date of birth of employee in dd/mm/yy format
                Date dob = dateFormater.parse(data[4]);
                //using all above fields we create a new object of employee and add it to our list
                Employee employee = new Employee(name, email, age, dob);
                employees.add(employee);
                //last id got frequently updated
                lastId = employee.getId();
            }
            //new global id is created which is 1 greated than the id of last employee
            GlobalID = ++lastId;
        } catch (Exception exception) {
            //used to handle exceptions in this method
            System.out.println("Error loading employees from file: " + exception.getMessage());
        }
    }

    // this method is used to add employee from data provided by user
    public static void addEmployee() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter Name : ");
        String name = scanner.nextLine();
        //check for valid name
        //loop untill user did not enter a valid name
        while (!isValidName(name)) {
            System.out.println("Not a Valid Name: ENTER AGAIN !!!");
            name = scanner.nextLine();
        }

        System.out.println("enter email : ");
        String email = scanner.nextLine();
        //check for valid email
        //loop untill user did not enter a valid email
        while (!isValidEmail(email)) {
            System.out.println("Not a Valid Email: ENTER AGAIN !!!");
            email = scanner.nextLine();
        }
        System.out.println("enter age : ");
        String age = scanner.nextLine();
        //check for valid age
        //loop untill user did not enter a valid age
        while (!isValidAge(age)) {
            System.out.println("Not a Valid Age: ENTER AGAIN !!!");
            age = scanner.nextLine();
        }
        System.out.println("enter date of birth (dd/MM/yyyy): ");
        String dobString = scanner.nextLine();
        //check for valid date of birth
        //loop untill user did not enter a valid date of birth
        while (!isValidDate(dobString)) {
            System.out.println("Not a Valid Date: ENTER AGAIN !!!");
            dobString = scanner.nextLine();
        }
        //formate the date in our provided formate using dateFormater
        Date dob = dateFormater.parse(dobString);
        Employee employee = new Employee(name, email, Integer.parseInt(age), dob);
        //if the employee is not already present add it to the file
        if (!employees.contains(employee)) {
            employees.add(employee);
            //method used to save data from employee list to "stored data.txt" file.
            saveEmployeesToFile();
        }
    }

    //method to check valid name
    public static boolean isValidName(String name) {
        String regex = "[a-zA-Z ]*";
        name = name.trim();
        if (name.isEmpty()) {
            return false;
        }
        return name.matches(regex);
    }

    //method to check valid email
    public static boolean isValidEmail(String mail) {
        String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        mail = mail.trim();
        if (mail.isEmpty()) {
            return false;
        }
        return mail.matches(regex);
    }

    //method to check valid age
    public static boolean isValidAge(String age) {
        String regex = "[0-9]*";
        age = age.trim();
        if (age.isEmpty()) {
            return false;
        }
        return age.matches(regex);
    }

    //method to check valid date of birth format
    public static boolean isValidDate(String date) {
        String regex = "(^0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(\\d{4}$)";
        date = date.trim();
        if (date.isEmpty()) {
            return false;
        }
        if (date.matches(regex)) {
            dateFormater.setLenient(false);
            try {
                dateFormater.parse(date);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    //method used to store data from employee list to file
    private static void saveEmployeesToFile() {
        try {
            //creting a file writer for "stored data.txt" file.
            FileWriter writer = new FileWriter("E:\\Work\\java work\\Module2\\src\\Employee_class\\Stored data.txt");
            //pass that FileWriter object into the bufferedWriter for fast writing
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            //loop into employee list and add employee store in list to file in new line
            for (Employee employee : employees) {
                System.out.println(employee);
                String line = employee.toString();
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            //closing the bufferedWriter
            bufferedWriter.close();
        } catch (Exception e) {
            //used to catch exception in this method like fileNotFound
            System.out.println("Error saving employees to file: " + e.getMessage());
        }
    }

    //this method is used to delete user from list
    private static void deleteEmployee() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter employee email: ");
        String email = scanner.nextLine();
        boolean deletedFlag = false;
        Iterator<Employee> iterator = employees.iterator();
        //iterate into list to find the emplyee with matching email id
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.getEmailAddress().equals(email)) {
                iterator.remove();
                deletedFlag = true;
                System.out.println("Employee deleted.");
                break;
            }
        }
        //if no entry is deleted.
        if (!deletedFlag) {
            System.out.println("No matching entry found to delete");
        }
        //calling this method to update data into the file after deletion
        saveEmployeesToFile();
    }

    //this method is used to search and sort emplyee according to user preference.
    private static void searchEmployee() {
        //taking query from user to search in records
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter query: ");
        String query = scanner.nextLine();
        //taking order from of sorting from user
        System.out.print("Enter sort order (name/age/dob): ");
        String sortOrder = scanner.nextLine();
        //loop utill user enter wrong choice of order
        while (!(sortOrder.equals("name") || sortOrder.equals("age") || sortOrder.equals("dob"))) {
            System.out.println("Not a Valid Sort Order: it should be 'anme' or 'age' or 'dob'");
            System.out.println("Enter Again !!!");
            sortOrder = scanner.nextLine();
        }
        //taking direction of sorting from user
        System.out.print("Enter sort direction (asc/desc): ");
        String sortDirection = scanner.nextLine();
        //loop utill user enter wrong choice of direction
        while (!(sortDirection.equals("asc") || sortDirection.equals("desc"))) {
            System.out.println("Not a Valid Direction: it should be 'asc' or 'desc'");
            System.out.println("Enter Again !!!");
            sortDirection = scanner.nextLine();
        }
        //flag variable to check if user seleted ascending direction.
        boolean isAscending = sortDirection.equals("asc");

        List<Employee> result = new ArrayList<Employee>();
        for (Employee employee : employees) {
            //check the entered query into the emplyee entry
            String search = employee.getName() + " " + employee.getEmailAddress() + " " + employee.getAge() + " " + employee.getDateOfBirth();
            if (search.contains(query)) {
                result.add(employee);
            }
        }

        //sorting the employee according to name if user selet name
        if (sortOrder.equals("name")) {
            result.sort(
                    Comparator.comparing(Employee::getName,
                            isAscending ? Comparator.naturalOrder() : Comparator.reverseOrder()));
        } //sorting the employee according to age if user selet age
        else if (sortOrder.equals("age")) {
            result.sort(
                    Comparator.comparing(Employee::getAge,
                            isAscending ? Comparator.naturalOrder() : Comparator.reverseOrder()));
        } //sorting the employee according to dob if user selet dob
        else if (sortOrder.equals("dob")) {
            result.sort(Comparator.comparing(Employee::getDateOfBirth,
                    isAscending ? Comparator.naturalOrder() : Comparator.reverseOrder()));
        }

        //printing the results in sorted order.
        System.out.println("Result :-");
        for (Employee employee : result) {
            System.out.println(employee);
        }
    }
}
