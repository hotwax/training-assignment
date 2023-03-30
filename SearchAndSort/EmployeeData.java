package SearchAndSort;

import java.util.PriorityQueue;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.regex.Pattern;

public class EmployeeData {

    Map<Integer, Employee> hashEmployeeDetail;

    // Employee Class
    static class Employee {
        private int employeeId;
        private String employeeName;
        private String employeeEmail;
        private int employeeAge;
        private LocalDate dateOfBirth;

        // default Constructor
        Employee() {

        }

        // Parameter Constructor
        Employee(int empId, String empName, String empEmail, int empAge, LocalDate date) {
            this.employeeId = empId;
            this.employeeEmail = empEmail;
            this.employeeAge = empAge;
            this.employeeName = empName;
            this.dateOfBirth = date;
        }

    }

    Employee employee;

    EmployeeData() throws DateTimeParseException {
        this.employee = new Employee();
        hashEmployeeDetail = new LinkedHashMap<>();
        try {

            File Obj = new File("SearchAndSort/Employee.txt");
            if (Obj.exists()) {
                Scanner Reader = new Scanner(Obj);
                while (Reader.hasNextLine()) {
                    String data = Reader.nextLine();
                    String empData[] = data.split(",");

                    int id = Integer.parseInt(empData[0]);
                    String name = empData[1];
                    String email = empData[2];
                    int age = Integer.parseInt(empData[3]);
                    String date = empData[4];

                    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-d");

                    LocalDate date1 = LocalDate.parse(date, format);

                    Employee e = new Employee(id, name, email, age, date1);
                    hashEmployeeDetail.put(id, e);
                }
                Reader.close();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    // Set the Employee Id
    public void setId(int id) {
        employee.employeeId = id;
    }

    // Get the Employee Id
    public int getId() {
        return employee.employeeId;
    }

    // Set the Employee Name
    public void setName(String name) {
        employee.employeeName = name;
    }

    // Get the Employee Name
    public String getName() {
        return employee.employeeName;
    }

    // Set the Employee Email
    public void setEmail(String email) {
        employee.employeeEmail = email;
    }

    // Get the Employee Email
    public String getEmail() {
        return employee.employeeEmail;
    }

    // Set the Employee Age
    public void setAge(int age) {
        employee.employeeAge = age;
    }

    // Get the Employee Age
    public int getAge() {
        return employee.employeeAge;
    }

    // Set the Date
    public void setDate(LocalDate date) {
        employee.dateOfBirth = date;
    }

    // get the Date

    public LocalDate getDate() {
        return employee.dateOfBirth;
    }

    // Validate the employee email address

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

    // Validate the employee name
    public static boolean isValidName(String name) {
        String nemaeRegex = "[A-Za-z]*";

        Pattern pat = Pattern.compile(nemaeRegex);
        if (name == null)
            return false;
        return pat.matcher(name).matches();
    }

    // Validate the employee Age and Dob
    public static boolean isValidAgeAndDob(int Age, LocalDate Dob) {

        LocalDate Today = LocalDate.now(ZoneId.of("Asia/Kolkata"));
        if (Dob.isAfter(Today)) {
            return false;
        } else {
            int age = (int) ChronoUnit.YEARS.between(Dob, Today);
            if (age == Age) {
                return true;
            }
        }
        return false;
    }

    // Add Employee in the File
    public void employeeAdd() {

        if (hashEmployeeDetail.containsKey(getId())) {
            System.out.println("Employee is Alredy Present");
            return;
        }

        if (!isValidEmail(getEmail())) {
            System.out.println("Email is not Valid");
            return;
        }
        if (!isValidName(getName())) {
            System.out.println("Name is not valid");
            return;
        }

        if (getAge() < 18) {
            System.out.println("Age should be greter then equal to 18 ");
            return;
        }
        if (!isValidAgeAndDob(getAge(), getDate())) {
            System.out.println("Age or Dob is not valid");
            return;
        }

        try {

            FileWriter fileWritter = new FileWriter("SearchAndSort/Employee.txt", true);
            BufferedWriter bw = new BufferedWriter(fileWritter);
            String empdetail = getId() + "," + getName() + "," + getEmail() + "," + getAge() + "," + getDate();
            bw.write(empdetail);
            bw.newLine();
            bw.close();
            hashEmployeeDetail.put(getId(), new Employee(getId(), getName(), getEmail(), getAge(), getDate()));

        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    // Delete the Employee detail by Id
    public void deleteDetails(int empid) {

        if (hashEmployeeDetail.containsKey(empid)) {
            hashEmployeeDetail.remove(empid);
            try {
                FileWriter filew = new FileWriter("SearchAndSort/Employee.txt", false);
                try (BufferedWriter b = new BufferedWriter(filew)) {
                    b.write("");
                }
                for (Map.Entry<Integer, Employee> map : hashEmployeeDetail.entrySet()) {
                    FileWriter fileWritter = new FileWriter("SearchAndSort/Employee.txt", true);
                    BufferedWriter bw = new BufferedWriter(fileWritter);
                    Employee emp = map.getValue();
                    String value = emp.employeeId + "," + emp.employeeName + ","
                            + emp.employeeEmail + "," + emp.employeeAge + ","
                            + map.getValue().dateOfBirth;
                    bw.write(value);
                    bw.newLine();
                    bw.close();
                }
            } catch (IOException exception) {
                System.out.println(exception);
            }

        } else {
            System.out.println("Employee is not Present ");

        }

    }

    // Search the Employee By Id
    public void searchById(int empId) {
        if (hashEmployeeDetail.containsKey(empId)) {
            Employee emp = hashEmployeeDetail.get(empId);
            System.out.println();
            System.out.println("Employee ID - " + emp.employeeId);
            System.out.println("Employee Name - " + emp.employeeName);
            System.out.println("Employee Email - " + emp.employeeEmail);
            System.out.println("Employee Age - " + emp.employeeAge);
            System.out.println("Date  - " + emp.dateOfBirth);
        } else {

            System.out.println("Employee is not Present");

        }

    }

    // Show the All Employee Details
    public void showEmployeesDetail() {
        if (hashEmployeeDetail.size() == 0) {
            System.out.println("No Employee is present");
            return;
        }
        int count = 1;

        for (Map.Entry<Integer, Employee> map : hashEmployeeDetail.entrySet()) {
            System.out.println("Employee - " + count + "-----------------");
            System.out.println();
            System.out.println("EmployeeId : " + map.getValue().employeeId);
            System.out.println("Employee Name : " + map.getValue().employeeName);
            System.out.println("Employee Email : " + map.getValue().employeeEmail);
            System.out.println("Employee Age : " + map.getValue().employeeAge);
            System.out.println("Employee Age : " + map.getValue().dateOfBirth);
            System.out.println();
            System.out.println();
            count++;

        }

    }

    // Comparator for Sort the Detail By Id

    public Comparator<Employee> comparatorById(boolean order) {
        if (order) {
            return (e1, e2) -> Integer.compare(e1.employeeId, e2.employeeId);
        } else {
            return (e1, e2) -> Integer.compare(e2.employeeId, e1.employeeId);
        }
    }

    // Comparator for Sort the Detail By Name

    public Comparator<Employee> comparatorByName(boolean order) {
        if (order) {
            return (e1, e2) -> e1.employeeName.compareTo(e2.employeeName);
        } else {
            return (e1, e2) -> e2.employeeName.compareTo(e1.employeeName);
        }
    }

    // Comparator for Sort the Detail By Id

    public Comparator<Employee> comparatorByAge(boolean order) {
        if (order) {
            return (e1, e2) -> Integer.compare(e1.employeeAge, e2.employeeAge);
        } else {
            return (e1, e2) -> Integer.compare(e2.employeeAge, e1.employeeAge);
        }
    }

    // Comparator for Sort the Detail By Id

    public Comparator<Employee> comparatorByDate(boolean order) {
        if (order) {
            return (e1, e2) -> e1.dateOfBirth.compareTo(e2.dateOfBirth);
        } else {
            return (e1, e2) -> e2.dateOfBirth.compareTo(e1.dateOfBirth);
        }
    }

    void searchByText(String text, PriorityQueue<Employee> sortDetails) {
        if (hashEmployeeDetail.size() == 0) {
            System.out.println("No employee is present");
            return;
        }
        for (Map.Entry<Integer, Employee> map : hashEmployeeDetail.entrySet()) {
            Employee emp = map.getValue();
            String checkStr = emp.employeeId + "," + emp.employeeName + "," + emp.employeeEmail + "," + emp.employeeAge
                    + "," + emp.dateOfBirth;
            if (checkStr.contains(text)) {
                sortDetails.add(emp);
            }

        }

        System.out.println("EmployeeID      Name      Email      Age      Date");
        while (!sortDetails.isEmpty()) {
            Employee emp = sortDetails.poll();
            System.out.println();
            System.out.print(emp.employeeId + "      ");
            System.out.print(emp.employeeName + "      ");
            System.out.print(emp.employeeEmail + "      ");
            System.out.print(emp.employeeAge + "      ");
            System.out.print(emp.dateOfBirth);
            System.out.println();
        }

    }

    public static void main(String[] args) throws DateTimeParseException {

        EmployeeData employee = new EmployeeData();
        try (Scanner input = new Scanner(System.in)) {
            int choice = 0;
            do {
                System.out.println();
                System.out.println("Menu options");
                System.out.println("1. Add The Employee Detail");
                System.out.println("2.Delete the Empoyee Detail");
                System.out.println("3.Get all Employees Data");
                System.out.println("4.Search the Employee by id");
                System.out.println("5.Sort and Search the Employee By Text");
                System.out.println("6.Exit");
                if (input.hasNext()) {
                    choice = input.nextInt();
                }
                switch (choice) {
                    case 1:
                        System.out.println("Enter the Employee Id");

                        int id = input.nextInt();
                        employee.setId(id);

                        System.out.println("Enter the Employee Name");

                        String name = input.next();
                        employee.setName(name);

                        System.out.println("Enter the Employee Email");
                        String email = input.next();
                        employee.setEmail(email);

                        System.out.println("Enter the Employee Age");
                        int age = input.nextInt();
                        employee.setAge(age);

                        System.out.println("Enter the Date of birth(d-MMM-yyyy)");
                        String date = input.next();

                        DateTimeFormatter format = DateTimeFormatter.ofPattern("d-MMM-yyyy");

                        LocalDate date1 = LocalDate.parse(date, format);
                        employee.setDate(date1);

                        employee.employeeAdd();

                        break;

                    case 2:
                        System.out.println("Enter the EmployId which you want to deleted");
                        int emid = input.nextInt();
                        employee.deleteDetails(emid);
                        break;

                    case 3:
                        System.out.println("Employee Details");
                        System.out.println();
                        employee.showEmployeesDetail();
                        break;

                    case 4:
                        System.out.println("Enter the employee Id which you want to search");
                        int sEmpId = input.nextInt();
                        employee.searchById(sEmpId);
                        break;

                    case 5:
                        System.out.println("Enter Any text You want to search");
                        String text;
                        text = input.next();
                        // Use Priority Queue to store the Employee details in sorted(Assending or
                        // Descending) Order
                        PriorityQueue<Employee> sortDetails = new PriorityQueue<>();

                        boolean Order = false;
                        int orderChoice;
                        do {
                            System.out.println("Enter in which Order you want to sort the Employee");
                            System.out.println("1.Asscending");
                            System.out.println("2.Descending");
                            orderChoice = input.nextInt();

                            switch (orderChoice) {
                                case 1:
                                    Order = true;// for Asscending
                                    break;
                                case 2:
                                    Order = false;// for Descending
                                    break;

                                default:
                                    System.out.println("Please Enter Valid order");

                            }
                        } while (orderChoice != 1 && orderChoice != 2);

                        int parameterChoice;
                        do {

                            System.out.println("Enter the parameter Which you want to sort");
                            System.out.println("1. Sort the Employee on the basis of Employee Id");
                            System.out.println("2. Sort the Employee on the basis of employee Name");
                            System.out.println("3. Sort the employee on the basis of Age");
                            System.out.println("4. sort the employee on the basis of Date of Birth");
                            parameterChoice = input.nextInt();
                            switch (parameterChoice) {
                                case 1:
                                    // for Sort the Searched data by Id
                                    sortDetails = new PriorityQueue<>(employee.comparatorById(Order));

                                    break;
                                case 2:
                                    // for Sort the Searched data by Name
                                    sortDetails = new PriorityQueue<>(employee.comparatorByName(Order));
                                    break;
                                case 3:
                                    // sort the data by Age
                                    sortDetails = new PriorityQueue<>(employee.comparatorByAge(Order));
                                    break;
                                case 4:
                                    // sort the data by Date of Birth
                                    sortDetails = new PriorityQueue<>(employee.comparatorByDate(Order));
                                    break;

                                default:
                                    System.out.println("Please Enter the valid Parameter ");
                                    break;

                            }
                        } while (parameterChoice != 1 && parameterChoice != 2 && parameterChoice != 3
                                && parameterChoice != 4);

                        employee.searchByText(text, sortDetails);

                        break;

                    case 6:
                        System.out.println("Thankyou");
                        break;

                    default:
                        System.out.println("Invalid Choices");
                        break;
                }

            } while (choice != 6);
        } catch (InputMismatchException exception) {
            System.out.println(exception);
        }

    }
}
