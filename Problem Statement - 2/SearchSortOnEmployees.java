import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

// Creating an employee class

class Employee {
    private int id;
    private String name;
    private String email;
    private int age;
    private Date dob;

    // Constructor --

    Employee(int id, String name, String email, int age, Date dob) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.dob = dob;
    }

    // Getters and Setters

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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    // for printing purpose

    public String toString() {
        return "Id : " + this.id + ", Name : " + this.name + ", email : " + this.email + ", age : " + this.age
                + ", DOB : "
                + SearchSortOnEmployees.simpleDate.format(this.dob);
    }

}

public class SearchSortOnEmployees {

    public static List<Employee> employeeList = new ArrayList<>(); // Employee list
    public static int idCounter = 1; // for keeping track of employee id
    public static Scanner input = new Scanner(System.in); // Create a Scanner object
    public static SimpleDateFormat simpleDate = new SimpleDateFormat("dd/mm/yyyy"); // for date formatting

    public static void main(String args[]) {

        // Creating file

        try {

            File Obj = new File("employees.txt");

            // Reading from the file and adding all the content to the list

            Scanner Reader = new Scanner(Obj);
            while (Reader.hasNextLine()) {
                String data = Reader.nextLine();
                Employee emp = createObject(data);
                employeeList.add(emp);
                idCounter = Math.max(emp.getId(), idCounter) + 1;
            }

            Reader.close();
        } catch (IOException exception) {
            System.out.println("Exception occurred" + exception);
        } catch (Exception exception) {
            System.out.println("Exception occurred" + exception);
        }

        // Creating Menu --

        Boolean onMenu = true;

        while (onMenu) {

            System.out.println("Enter 1 For Add Employee to the file.");
            System.out.println("Enter 2 For Delete Employee From the file");
            System.out.println("Enter 3 For Searching from From the file");
            System.out.println("Enter any other number to Exit");
            // Taking input from user

            int menuInput = 1;

            try {
                menuInput = input.nextInt();

            } catch (InputMismatchException e) {
                // TODO: handle exception

                System.out.println("Enter a Valid Input");

                System.exit(-1);
            }

            switch (menuInput) {
                case 1:
                    try {
                        addEmployee();
                    } catch (ParseException exception) {
                        // TODO: handle exception
                        System.out.println("Exception Occurred" + exception);
                    } catch (IOException exception) {
                        System.out.println("Exception Occurred" + exception);
                    }
                    break;
                case 2:
                    deleteEmployee();
                    break;
                case 3:
                    try {
                        search();
                    } catch (IOException exception) {
                        System.out.println("Exception Occurred" + exception);
                    } catch (Exception exception) {
                        System.out.println("Exception Occurred" + exception);
                    }
                    break;
                default:
                    onMenu = false;
                    break;
            }
        }

    }

    // function to create object by giving a informatic string

    public static Employee createObject(String str) throws Exception {

        List<String> list = Arrays.asList(str.split("\\s*,\\s*"));

        // System.out.println(list);

        int id = Integer.parseInt(list.get(0).trim());

        String name = list.get(1).trim();
        String email = list.get(2).trim();
        int age = Integer.parseInt(list.get(3).trim());
        Date date = simpleDate.parse(list.get(4).trim());

        // Creating Object by calling constructor

        Employee emp = new Employee(id, name, email, age, date);

        return emp;

    }

    // function to add employee in file

    public static void addEmployee() throws IOException, ParseException {

        try {

            // Taking all the inputs

            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader bufferReader = new BufferedReader(isr);

            System.out.println("Enter the Name : ");

            String name = bufferReader.readLine();

            // For checking if the data entered by the user is valid or not

            if (!isValidName(name)) {
                System.out.println("Enter a Valid Name \n");
                System.exit(0);
            }

            System.out.println("Enter the Email : ");

            String email = bufferReader.readLine();

            // For checking if the data entered by the user is valid or not

            if (!isValidEmail(email)) {
                System.out.println("Enter a Valid Email \n");
                System.exit(0);
            }

            System.out.println("Enter the Age : ");

            Integer age = input.nextInt();

            System.out.println("Enter the Date Of Birth : ");

            String dobInString = bufferReader.readLine();

            Date dob = simpleDate.parse(dobInString);

            Employee emp = new Employee(idCounter, name, email, age, dob);

            // Adding employee to the list

            employeeList.add(emp);

            // increasing id

            idCounter++;

            // ReWriting in a file --

            rewriteTheFile();

        } catch (IOException exception) {
            System.out.println("Enter a Valid Input \n" + exception);
        } catch (InputMismatchException exception) {
            System.out.println("Enter a Valid Input \n" + exception);
        } catch (ParseException exception) {
            System.out.println("Enter a Valid Input \n" + exception);
        }

        // Giving output that employee is created and added to the file

        System.out.println("\n Employee added successfully \n");

    }

    // for employee deletion

    public static void deleteEmployee() {
        System.out.println("Enter the id to delete : ");

        int toDeleteId = input.nextInt();

        // looping through the list for finding the element
        Iterator<Employee> iterator = employeeList.iterator();

        while (iterator.hasNext()) {
            Employee emp = iterator.next();
            if (emp.getId() == toDeleteId) {
                iterator.remove();
                rewriteTheFile();
            }
        }
    }

    // rewriting the file

    public static void rewriteTheFile() {
        // Writing in a file --

        try {

            // Open given file in append mode by creating an
            // object of BufferedWriter class
            BufferedWriter out = new BufferedWriter(
                    new FileWriter("employees.txt"));

            // Writing on output stream

            // looping through the list and writing the objects

            for (Employee emp : employeeList) {
                out.write(emp.getId() + ", " + emp.getName() + ", " + emp.getEmail() + ", " + emp.getAge() + ", "
                        + simpleDate.format(emp.getDob()) + "\n");
            }

            // Closing the connection

            out.close();
        }

        // Catch block to handle the exceptions
        catch (IOException e) {

            // Display message when exception occurs
            System.out.println("exception occurred" + e);
        }
    }

    // function for searching text

    public static void search() throws Exception {

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader bufferReader = new BufferedReader(isr);

        System.out.println("Enter the text to search : ");

        // Making it lowercase so that it will not be case sensitive

        String textToSearch = bufferReader.readLine().toLowerCase();

        List<Employee> searchResult = new ArrayList<Employee>();

        // Looping through employee for finding the matching text objects

        for (Employee emp : employeeList) {

            // creating an employee string so that we can search for text in it
            String employeeStringToSearch = objectToString(emp).toLowerCase();

            if (employeeStringToSearch.contains(textToSearch)) {
                searchResult.add(emp);
            }

        }

        // Asking how user wants to sort content

        System.out.println("How do you want to sort the content -- ?? ");
        System.out.println("Enter 1 to sort by ID");
        System.out.println("Enter 2 to sort by Name");
        System.out.println("Enter 3 to sort by email");
        System.out.println("Enter 4 to sort by Age");
        System.out.println("Enter 5 to sort by DOB");

        int inputForSortingOpeartion = input.nextInt();

        // Asking user for the direction of sorting ascending or descending

        System.out.println("Direction for sorting - ");
        System.out.println("Enter 1 for Ascending and 2 for Descending ");

        int inputForSortingDirection = input.nextInt();

        // soryting the list by calling the sortlist method

        searchResult = sortList(searchResult, inputForSortingOpeartion, inputForSortingDirection == 1 ? true : false);

        // Printing Users search result --

        System.out.println("Your Result - ");

        // if no results exists

        if (searchResult.size() <= 0) {
            System.out.println("No Results Found");
        }

        // if results exists

        for (Employee emp : searchResult) {
            System.out.println(emp);
        }

    }

    // for converting an object into a string

    public static String objectToString(Employee emp) {
        return emp.getId() + emp.getName() + emp.getEmail() + emp.getAge() + simpleDate.format(emp.getDob());
    }

    // for applying order sort on list

    public static List<Employee> sortList(List<Employee> listToSort, int sortBy, Boolean isAscending) {

        // Creating different Comparartors --

        // By Id comparator --
        Comparator<Employee> sortByIDComparator = Comparator.comparing(
                Employee::getId,
                isAscending ? Comparator.naturalOrder() : Comparator.reverseOrder());

        // By Name comparator

        Comparator<Employee> sortByNameComparator = Comparator.comparing(
                Employee::getName,
                isAscending ? Comparator.naturalOrder() : Comparator.reverseOrder());

        // By Email comparator

        Comparator<Employee> sortByEmailComparator = Comparator.comparing(
                Employee::getEmail,
                isAscending ? Comparator.naturalOrder() : Comparator.reverseOrder());

        // By Age comparator

        Comparator<Employee> sortByAgeComparator = Comparator.comparing(
                Employee::getAge,
                isAscending ? Comparator.naturalOrder() : Comparator.reverseOrder());

        // By DOB comparator

        Comparator<Employee> sortByDOBComparator = Comparator.comparing(
                Employee::getDob,
                isAscending ? Comparator.naturalOrder() : Comparator.reverseOrder());

        // Switch case for based on different sorting sitiuation like name ... id etc

        switch (sortBy) {
            case 1:
                Collections.sort(listToSort, sortByIDComparator);
                break;
            case 2:
                Collections.sort(listToSort, sortByNameComparator);
                break;
            case 3:
                Collections.sort(listToSort, sortByEmailComparator);
                break;
            case 4:
                Collections.sort(listToSort, sortByAgeComparator);
                break;
            case 5:
                Collections.sort(listToSort, sortByDOBComparator);
                break;
            default:
                System.out.println("Enter a valid Number for Valid Operation!!");
        }

        // returning the sorted list

        return listToSort;
    }

    // For checking if string is a valid email or not

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    // for checking if string contains only letters alphabets

    public static boolean isValidName(String name) {
        String nameRegex = "[a-zA-Z]+";

        Pattern pat = Pattern.compile(nameRegex);
        if (name == null)
            return false;
        return pat.matcher(name).matches();
    }
}
