
// Importing packages required in the program.
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Comparator;
import java.util.Collections;
import java.util.InputMismatchException;

// main class
public class Employee {
    // To store highest id value to employee data
    static int highestId = 0;

    static int incrementId() {
        highestId = highestId + 1;
        return highestId;
    }

    // creating the employeeRecord class to contain the Employee data
    static class employeeRecord extends Employee {
        int empId;
        String name;
        String email;
        int age;
        String dateOfBirth;

        // constructor
        public employeeRecord(int empid, String name, String email, int age, String dateOfBirth) {
            this.empId = empid;
            this.name = name;
            this.email = email;
            this.age = age;
            this.dateOfBirth = dateOfBirth;

        }

        // method to get highest id
        public int getHigestId() {
            return highestId;
        }

        // method to set highest id
        public void setHighestId(int id) {
            // this.highestid = id;
        }

        // method to get employee id
        public int getEmpId() {
            return empId;
        }

        // method to set employee id
        public void setEmpId(int empId) {
            this.empId = empId;
        }

        // method to get name of employee
        public String getName() {
            return name;
        }

        // method to set name of the employee
        public void setName(String name) {
            this.name = name;
        }

        // method to get email of the employee
        public String getEmail() {
            return email;
        }

        // method to set email of the employee
        public void setEmail(String email) {
            this.email = email;
        }

        // method to get age of the employee
        public int getAge() {
            return age;
        }

        // method to set the age of the employee
        public void setAge(int age) {
            this.age = age;
        }

        // method to get the date of birth of the employee
        public String getDateOfBirth() {
            return (String) dateOfBirth;
        }

        // method to set the date of birth of the employee
        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        // method toString to convert the employee data in a string
        public String toString() {
            return "Employee { " + "empid=" + empId + ", name='" + name + '\'' + ", Email :" + email + ", age=" + age
                    + ", dateofbirth='" + dateOfBirth + '\'' + '}';
        }
    }

    // method to display the data of employee in the array list
    public static void display(ArrayList<employeeRecord> employeeData) {
        for (employeeRecord empData : employeeData) {
            System.out.println(empData.toString());
        }
    }

    // This function is used to add a new employee record to an ArrayList of
    // employee data.
    public static void addEmployee(ArrayList<employeeRecord> employeedata) {
        int highId = highestId;
        Scanner scc = new Scanner(System.in); // System.in is a standard input stream
        // It prompts the user to enter the employee's name, email, date of birth, and
        // age.
        System.out.println("Enter a Name :");
        String name_input = scc.nextLine();

        System.out.println("Enter a Email :");
        String email_input = scc.nextLine();

        System.out.println("Enter the Date of Birth");
        String date_of_birth = scc.nextLine();

        System.out.println("Enter the Age");
        int age_input = scc.nextInt();

        // It then validates the user input using regular expressions to ensure that the
        // input is in the correct format.

        String rfMail = "^(.+)@(.+)$";
        String rfName = "^[a-zA-Z\\s]+";
        String rfDOB = "^(1[0-2]|0[1-9])/(3[01]" + "|[12][0-9]|0[1-9])/[0-9]{4}$";

        Pattern patternOfName = Pattern.compile(rfName);
        Matcher matcherOfName = patternOfName.matcher((CharSequence) name_input);

        Pattern patternOfEmail = Pattern.compile(rfMail);
        Matcher matcherOfEmail = patternOfEmail.matcher((CharSequence) email_input);

        Pattern patternOfDOB = Pattern.compile(rfDOB);
        Matcher matcherOfDOB = patternOfDOB.matcher((CharSequence) date_of_birth);

        boolean isSafeName = matcherOfName.matches();
        boolean isSafeEmail = matcherOfEmail.matches();
        boolean isSafeDOB = matcherOfDOB.matches();
        boolean isSafeAge = (age_input > 0) && (age_input < 120);

        // If the input is valid, it creates a new employee record object with a unique
        // ID and adds it to the ArrayList.
        // If the input is invalid, it prints an error message indicating which input is
        // invalid.

        if (!isSafeName) {
            System.out.println("Invalid Name");
        } else if (!isSafeEmail) {
            System.out.println("Invalid Email");
        } else if (!isSafeDOB) {
            System.out.println("Invalid Date of birth");
        } else if (!isSafeAge) {
            System.out.println("Invalid Age");
        } else {
            int newId = highId;
            employeeRecord newData = new employeeRecord(newId, name_input, email_input, age_input, date_of_birth);
            employeedata.add(newData);
            // Finally, it calls the writeFile function to write the updated ArrayList to a
            // file.
            writeFile(employeedata);
            highId = incrementId();
            System.out.println("Employee Added Successfully!!");
            System.out.println();
        }

    }

    public static void deleteData(ArrayList<employeeRecord> employeeData) {
        Scanner sc = new Scanner(System.in);
        // asking for employee id
        System.out.println("Enter Employee ID: ");
        int empIdInput = sc.nextInt();

        boolean isFound = false;
        // Traversing arraylist and find the employee id and if found then remove it
        // from the arraylist and rewrite the file
        for (int i = 0; i < employeeData.size(); i++) {
            if (employeeData.get(i).getEmpId() == empIdInput) {
                employeeData.remove(i);
                System.out.println("Data deleted");
                isFound = true;

            }
        }
        // if data found then write deleted data in file
        if (isFound) {
            writeFile(employeeData);
        } else {
            System.out.println("Invalid id");
        }

    }

    public static void searchData(ArrayList<employeeRecord> employeeData) {
        Scanner sc = new Scanner(System.in);
        // asking to enter the text
        System.out.println("Enter the text to Search ");
        Scanner newSc = new Scanner(System.in);
        String query = newSc.nextLine();
        // creating another array list to store the matched records in a list
        ArrayList<employeeRecord> searchedData = new ArrayList<>();

        for (employeeRecord emp : employeeData) {
            // converting the data in a single string
            String temp = emp.getEmpId() + emp.getName() + emp.getEmail() + emp.getAge() + emp.dateOfBirth;
            // if string is equal to the query string
            if (temp.contains(query)) {
                // add that data in a another created list
                searchedData.add(emp);
            }
        }
        // now asking for how to sort that found data or ordered by
        System.out.println("Enter the option how you want to sort the records: ");
        System.out.println("Type id : To Sort the records by ID");
        System.out.println("Type name : To Sort the records  by Name");
        System.out.println("Type emailAddress : To Sort the records  by emailAddress");
        System.out.println("Type age : To Sort the records  by Age");
        System.out.println("Type dob : To Sort the records  by Date of Birth");
        String select = sc.next().toLowerCase();
        // sort the data based on the selection by user
        switch (select) {
            case "id":
                searchedData.sort(new OrderbyId());
                break;
            case "name":
                searchedData.sort(new OrderbyName());
                break;
            case "emailAddress":
                searchedData.sort(new OrderbyemailAddress());
                break;
            case "age":
                searchedData.sort(new OrderbyAge());
                break;
            case "dob":
                searchedData.sort(new OrderbyDOB());
                break;
            default:
                System.out.println("Kindly select from the given options.");
        }

        // now asking from the user to order the data by asc or desc
        System.out.println("Enter whether you want the data in increasing or decreasing order: ");
        System.out.println("Type asc : For Ascending");
        System.out.println("Type desc : For Descending");
        String selection = sc.next().toLowerCase();
        // if asc is entered then show the data as it is.
        if ("asc".equals(selection)) {
            for (employeeRecord emp : searchedData) {
                System.out.println(emp.toString());
            }
        } else { // else reverse the data and then print
            Collections.reverse(searchedData);
            for (employeeRecord emp : searchedData) {
                System.out.println(emp.toString());
            }
        }
    }

    // method to write a data in a file
    public static void writeFile(ArrayList<employeeRecord> employeeData) {
        try {
            // geting the current working directory
            String dir = System.getProperty("user.dir");

            // Writing Employee Details to File
            BufferedWriter writer = new BufferedWriter(new FileWriter(dir + "/src/Employeedata.txt"));
            for (employeeRecord emp : employeeData) {
                writer.write(emp.getEmpId() + "," + emp.getName() + "," + emp.getEmail() + "," + emp.getAge() + ","
                        + emp.getDateOfBirth() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // sort the data of arraylist based on id using Comparator
    public static class OrderbyId implements Comparator<employeeRecord> {

        @Override
        public int compare(employeeRecord o1, employeeRecord o2) {

            return o1.empId - o2.empId;
        }

    }

    // sort the data of arraylist based on name using Comparator
    public static class OrderbyName implements Comparator<employeeRecord> {

        @Override
        public int compare(employeeRecord o1, employeeRecord o2) {

            return o1.name.compareTo(o2.name);
        }

    }

    // sort the data of arraylist based on email using Comparator
    public static class OrderbyemailAddress implements Comparator<employeeRecord> {

        @Override
        public int compare(employeeRecord o1, employeeRecord o2) {

            return o1.email.compareTo(o2.email);
        }

    }

    // sort the data of arraylist based on age using Comparator
    public static class OrderbyAge implements Comparator<employeeRecord> {

        @Override
        public int compare(employeeRecord o1, employeeRecord o2) {

            return o1.age - o2.age;
        }

    }

    // sort the data of arraylist based on date of birth using Comparator
    public static class OrderbyDOB implements Comparator<employeeRecord> {

        @Override
        public int compare(employeeRecord o1, employeeRecord o2) {

            return o1.dateOfBirth.compareTo(o2.dateOfBirth);
        }

    }

    public static void main(String[] args) {
        // creating the arraylist to store the employee data in it
        ArrayList<employeeRecord> employeeData = new ArrayList<>();
        // Creating object of Employee
        Employee mainEmp = new Employee();

        try {
            // getting current working directory
            String dir = System.getProperty("user.dir");
            // Create file objects for urls.txt and words.txt
            File file = new File(dir + "/src/Employeedata.txt");
            Scanner fileReader;
            fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                // spliting the word based on the comma
                String[] data = fileReader.nextLine().split(",");

                // extracting each data
                int emp_id = Integer.parseInt(data[0]);
                String name = data[1];
                String email = data[2];
                int age = Integer.parseInt(data[3]);
                String date_of_birth = data[4];
                // insert data in a single object emp
                employeeRecord emp = new employeeRecord(emp_id, name, email, age, date_of_birth);
                // adding the data in a arraylist
                employeeData.add(emp);
                // increment the highest id
                incrementId();

            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        // adding the menu driven functionality

        while (true) {
            System.out.println("Choose an option from the menu given below:");
            System.out.println("Type Add    : To Insert Record");
            System.out.println("Type Delete : To Delete Record");
            System.out.println("Type Search : To Search Record");
            System.out.println("Type display : To Display Records");
            System.out.println("Enter your selection ");
            try {
                Scanner sc = new Scanner(System.in);
                String selection = sc.next().toLowerCase();

                switch (selection) {
                    case "add":
                        // adding
                        addEmployee(employeeData);
                        break;

                    case "delete":

                        deleteData(employeeData);
                        break;

                    case "search":
                        searchData(employeeData);

                        break;

                    case "display":
                        // calling the display method to display the data
                        display(employeeData);
                        break;
                    case "exit":
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

}
