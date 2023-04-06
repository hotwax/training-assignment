
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Date;
import java.util.Comparator;
import java.util.Collections;
import java.util.InputMismatchException;

// main class
public class Employee {

    static int highestid = 0;

    static int incrementid() {
        highestid = highestid + 1;
        return highestid;
    }

    // creating the employeerecord class to contain the Employee data
    static class employeerecord extends Employee {
        int empid;
        String name;
        String email;
        int age;
        String dateofbirth;

        // constructor
        public employeerecord(int empid, String name, String email, int age, String dateofbirth) {
            this.empid = empid;
            this.name = name;
            this.email = email;
            this.age = age;
            this.dateofbirth = dateofbirth;

        }

        // method to get highest id
        public int gethigestid() {
            return highestid;
        }

        // method to set highest id
        public void sethighestid(int id) {
            // this.highestid = id;
        }

        // method to get employee id
        public int getempid() {
            return empid;
        }

        // method to set employee id
        public void setempid(int empid) {
            this.empid = empid;
        }

        // method to get name of employee
        public String getname() {
            return name;
        }

        // method to set name of the employee
        public void setname(String name) {
            this.name = name;
        }

        // method to get email of the employee
        public String getemail() {
            return email;
        }

        // method to set email of the employee
        public void setemail(String email) {
            this.email = email;
        }

        // method to get age of the employee
        public int getage() {
            return age;
        }

        // method to set the age of the employee
        public void setage(int age) {
            this.age = age;
        }

        // method to get the date of birth of the employee
        public String getdateofbirth() {
            return (String) dateofbirth;
        }

        // method to set the date of birth of the employee
        public void setdateofbirth(String dateofbirth) {
            this.dateofbirth = dateofbirth;
        }

        // method toString to convert the employee data in a string
        public String toString() {
            return "Employee { " + "empid=" + empid + ", name='" + name + '\'' + ", Email :" + email + ", age=" + age
                    + ", dateofbirth='" + dateofbirth + '\'' + '}';
        }
    }

    // method to display the data of employee in the array list
    public static void display(ArrayList<employeerecord> employeedata) {
        for (employeerecord empdata : employeedata) {
            System.out.println(empdata.toString());
        }
    }

    // This function is used to add a new employee record to an ArrayList of
    // employee data.
    public static void addemployee(ArrayList<employeerecord> employeedata) {
        int highid = highestid;
        Scanner scc = new Scanner(System.in); // System.in is a standard input stream
        // It prompts the user to enter the employee's name, email, date of birth, and
        // age.
        System.out.println("Enter a Name :");
        String nameinput = scc.nextLine();

        System.out.println("Enter a Email :");
        String emailinput = scc.nextLine();

        System.out.println("Enter the Date of Birth");
        String dateofbirth = scc.nextLine();

        System.out.println("Enter the Age");
        int ageinput = scc.nextInt();

        // It then validates the user input using regular expressions to ensure that the
        // input is in the correct format.

        String rfmail = "^(.+)@(.+)$";
        String rfname = "^[a-zA-Z\\s]+";
        String rfdob = "^(1[0-2]|0[1-9])/(3[01]" + "|[12][0-9]|0[1-9])/[0-9]{4}$";

        Pattern patternofname = Pattern.compile(rfname);
        Matcher matcherofname = patternofname.matcher((CharSequence) nameinput);

        Pattern patternofemail = Pattern.compile(rfmail);
        Matcher matcherofemail = patternofemail.matcher((CharSequence) emailinput);

        Pattern patternofdob = Pattern.compile(rfdob);
        Matcher matcherofdob = patternofdob.matcher((CharSequence) dateofbirth);

        boolean issafename = matcherofname.matches();
        boolean issafeemail = matcherofemail.matches();
        boolean issafedob = matcherofdob.matches();
        boolean issafeage = (ageinput > 0) && (ageinput < 120);

        // If the input is valid, it creates a new employee record object with a unique
        // ID and adds it to the ArrayList.
        // If the input is invalid, it prints an error message indicating which input is
        // invalid.

        if (!issafename) {
            System.out.println("Invalid Name");
        } else if (!issafeemail) {
            System.out.println("Invalid Email");
        } else if (!issafedob) {
            System.out.println("Invalid Date of birth");
        } else if (!issafeage) {
            System.out.println("Invalid Age");
        } else {
            int newid = highid;
            employeerecord newdata = new employeerecord(newid, nameinput, emailinput, ageinput, dateofbirth);
            employeedata.add(newdata);
            // Finally, it calls the writeFile function to write the updated ArrayList to a
            // file.
            writeFile(employeedata);
            highid = incrementid();
            System.out.println("Employee Added Successfully!!");
            System.out.println();
        }

    }

    public static void deletedata(ArrayList<employeerecord> employeedata) {
        Scanner sc = new Scanner(System.in);
        // asking for employee id
        System.out.println("Enter Employee ID: ");
        int empidinput = sc.nextInt();

        boolean isfound = false;
        // Traversing arraylist and find the employee id and if found then remove it
        // from the arraylist and rewrite the file
        for (int i = 0; i < employeedata.size(); i++) {
            if (employeedata.get(i).getempid() == empidinput) {
                employeedata.remove(i);
                System.out.println("Data deleted");
                isfound = true;

            }
        }
        // if data found then write deleted data in file
        if (isfound) {
            writeFile(employeedata);
        } else {
            System.out.println("Invalid id");
        }

    }

    public static void searchdata(ArrayList<employeerecord> employeedata) {
        Scanner sc = new Scanner(System.in);
        // asking to enter the text
        System.out.println("Enter the text to Search ");
        Scanner newsc = new Scanner(System.in);
        String query = newsc.nextLine();
        // creating another array list to store the matched records in a list
        ArrayList<employeerecord> searcheddata = new ArrayList<>();

        for (employeerecord emp : employeedata) {
            // converting the data in a single string
            String temp = emp.getempid() + emp.getname() + emp.getemail() + emp.getage() + emp.dateofbirth;
            // if string is equal to the query string
            if (temp.contains(query)) {
                // add that data in a another created list
                searcheddata.add(emp);
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
                searcheddata.sort(new OrderbyId());
                break;
            case "name":
                searcheddata.sort(new OrderbyName());
                break;
            case "emailAddress":
                searcheddata.sort(new OrderbyemailAddress());
                break;
            case "age":
                searcheddata.sort(new OrderbyAge());
                break;
            case "dob":
                searcheddata.sort(new OrderbyDOB());
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
            for (employeerecord emp : searcheddata) {
                System.out.println(emp.toString());
            }
        } else { // else reverse the data and then print
            Collections.reverse(searcheddata);
            for (employeerecord emp : searcheddata) {
                System.out.println(emp.toString());
            }
        }
    }

    // method to write a data in a file
    public static void writeFile(ArrayList<employeerecord> employeedata) {
        try {
            // geting the current working directory
            String dir = System.getProperty("user.dir");

            // Writing Employee Details to File
            BufferedWriter writer = new BufferedWriter(new FileWriter(dir + "/src/Employeedata.txt"));
            for (employeerecord emp : employeedata) {
                writer.write(emp.getempid() + "," + emp.getname() + "," + emp.getemail() + "," + emp.getage() + ","
                        + emp.getdateofbirth() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // sort the data of arraylist based on id using Comparator
    public static class OrderbyId implements Comparator<employeerecord> {

        @Override
        public int compare(employeerecord o1, employeerecord o2) {

            return o1.empid - o2.empid;
        }

    }

    // sort the data of arraylist based on name using Comparator
    public static class OrderbyName implements Comparator<employeerecord> {

        @Override
        public int compare(employeerecord o1, employeerecord o2) {

            return o1.name.compareTo(o2.name);
        }

    }

    // sort the data of arraylist based on email using Comparator
    public static class OrderbyemailAddress implements Comparator<employeerecord> {

        @Override
        public int compare(employeerecord o1, employeerecord o2) {

            return o1.email.compareTo(o2.email);
        }

    }

    // sort the data of arraylist based on age using Comparator
    public static class OrderbyAge implements Comparator<employeerecord> {

        @Override
        public int compare(employeerecord o1, employeerecord o2) {

            return o1.age - o2.age;
        }

    }

    // sort the data of arraylist based on date of birth using Comparator
    public static class OrderbyDOB implements Comparator<employeerecord> {

        @Override
        public int compare(employeerecord o1, employeerecord o2) {

            return o1.dateofbirth.compareTo(o2.dateofbirth);
        }

    }

    public static void main(String[] args) {
        // creating the arraylist to store the employee data in it
        ArrayList<employeerecord> employeedata = new ArrayList<>();
        // Creating object of Employee
        Employee mainemp = new Employee();

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
                employeerecord emp = new employeerecord(emp_id, name, email, age, date_of_birth);
                // adding the data in a arraylist
                employeedata.add(emp);
                // increment the highest id
                mainemp.incrementid();

            }
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
                        addemployee(employeedata);
                        break;

                    case "delete":

                        deletedata(employeedata);
                        break;

                    case "search":
                        searchdata(employeedata);

                        break;

                    case "display":
                        // calling the display method to display the data
                        display(employeedata);
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
