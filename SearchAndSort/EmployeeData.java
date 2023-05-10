package SearchAndSort;
import java.util.PriorityQueue;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Comparator;
import java.util.HashSet;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.regex.Pattern;

//EmployeeData class
public class EmployeeData {
    Map<Integer, Employee> employeesDetails;
    HashSet<String> employeesEmail;
    File empFile=new File("SearchAndSort", "Employee.txt");

    // Employee Class
    class Employee {
        private int employeeId;
        private String employeeName;
        private String employeeEmail;
        private int employeeAge;
        private LocalDate dateOfBirth;

        // Set the Employee Id
        public void setId(int id) {
            this.employeeId = id;
        }

        // Get the Employee Id
        public int getId() {
            return this.employeeId;
        }

        // Set the Employee Name
        public void setName(String name) {
            this.employeeName = name;
        }

        // Get the Employee Name
        public String getName() {
            return this.employeeName;
        }

        // Set the Employee Email
        public void setEmail(String email) {
            this.employeeEmail = email;
        }

        // Get the Employee Email
        public String getEmail() {
            return this.employeeEmail;
        }

        // Set the Employee Age
        public void setAge(int age) {
            this.employeeAge = age;
        }

        // Get the Employee Age
        public int getAge() {
            return this.employeeAge;
        }

        // Set the Date
        public void setDate(LocalDate date) {
            this.dateOfBirth = date;
        }

        // get the Date
        public LocalDate getDate() {
            return this.dateOfBirth;
        }
    }

    Employee employee;

    EmployeeData() {
        employeesDetails = new LinkedHashMap<>();
        employee = new Employee();
        employeesEmail = new HashSet<>();
        try {
            File Obj = new File(empFile.getAbsolutePath());
            if (Obj.exists()) {
                Scanner Reader = new Scanner(Obj);
                // Read the file line by line
                while (Reader.hasNextLine()) {
                    String data = Reader.nextLine();
                    String empData[] = data.split(",");
                    int id = Integer.parseInt(empData[0]);
                    String name = empData[1];
                    String email = empData[2];
                    int age = Integer.parseInt(empData[3]);
                    String date = empData[4];
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate dob = LocalDate.parse(date, format);
                    // create the employee object
                    Employee employee = new Employee();
                    employee.setId(id);
                    employee.setName(name);
                    employee.setEmail(email);
                    employee.setAge(age);
                    employee.setDate(dob);
                    //get all the employees data is present in the file
                    employeesDetails.put(id, employee);
                    employeesEmail.add(email);
                }
                Reader.close();
            }
        } catch (DateTimeParseException exception) {
            System.out.println(exception);
        } catch (FileNotFoundException exception) {
            System.out.println(exception);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    // validate the employee Id
    public boolean isValidId(String id) {
        String idRegex = "^[0-9]*$";
        Pattern pattern = Pattern.compile(idRegex);
        return pattern.matcher(id).matches();
    }

    // Validate the employee email address
    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    // Validate the employee name
    public boolean isValidName(String name) {
        String nemaeRegex = "[A-Za-z]*";
        Pattern pattern = Pattern.compile(nemaeRegex);
        return pattern.matcher(name).matches();
    }

    // Validate the employee Age
    public boolean isValidAge(String age) {
        String ageRegex = "^[0-9]*$";
        Pattern pattern = Pattern.compile(ageRegex);
        return pattern.matcher(age).matches();
    }

    // validate the employee Date of Birth
    public boolean isValidDob(String dob) {
        String dateRegex = "\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|[3][01])";
        Pattern pattern = Pattern.compile(dateRegex);
        return pattern.matcher(dob).matches();
    }

    // Validate the employee Age and Dob
    public boolean isValidAgeAndDob(int employeeAge, LocalDate employeeDob) {
        LocalDate todaysDate = LocalDate.now(ZoneId.of("Asia/Kolkata"));
        if (employeeDob.isAfter(todaysDate)) {
            return false;
        } else {
            int age = (int) ChronoUnit.YEARS.between(employeeDob, todaysDate);
            if (age == employeeAge) {
                return true;
            }
        }
        return false;
    }

    // Add Employee in the File
    public void employeeAdd() {
        // check the employee Id is unique or not
        if (employeesDetails.containsKey(employee.getId())) {
            System.out.println("Employee Id is already Exist");
            return;
        }

        // check the employee email is unique or not
        if (employeesEmail.contains(employee.getEmail())) {
            System.out.println("Employee Email is already Exist");
            return;
        }

        // check the employee is correct or not
        if (employee.getAge() < 18) {
            System.out.println("Age should be greter then equal to 18");
            return;
        }
        if (employee.getAge() >= 100) {
            System.out.println("Age should be less then 100");
            return;
        }

        // check the age and dob is matched
        if (!isValidAgeAndDob(employee.getAge(), employee.getDate())) {
            System.out.println("Age or Dob is not valid");
            return;
        }
        try {
            // append the employee data to given file
            FileWriter fileWritter = new FileWriter(empFile.getAbsolutePath(), true);
            BufferedWriter bufferWriter = new BufferedWriter(fileWritter);
            String empdetail = employee.getId() + "," + employee.getName() + "," + employee.getEmail() + ","
                    + employee.getAge() + "," + employee.getDate();
            bufferWriter.write(empdetail);
            bufferWriter.newLine();
            bufferWriter.close();
            Employee emp = new Employee();
            emp.setId(employee.getId());
            emp.setName(employee.getName());
            emp.setEmail(employee.getEmail());
            emp.setAge(employee.getAge());
            emp.setDate(employee.getDate());
            employeesDetails.put(employee.getId(), emp);
            System.out.println("Employee added ");
        } catch (FileNotFoundException exception) {
            System.out.println(exception);
        } catch (IOException exception) {
            System.out.println(exception);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    // Delete the Employee detail by Id
    public void deleteDetails(int empid) {
        // check employee Id is present in employees data or not
        if (employeesDetails.containsKey(empid)) {
            employeesDetails.remove(empid);
            try {
                FileWriter filew = new FileWriter(empFile.getAbsolutePath(), false);
                BufferedWriter bufferWriter = new BufferedWriter(filew);
                bufferWriter.write("");
                bufferWriter.close();
                for (Map.Entry<Integer, Employee> map : employeesDetails.entrySet()) {
                    FileWriter fileWritter = new FileWriter(empFile.getAbsolutePath(), true);
                    BufferedWriter bufWriter = new BufferedWriter(fileWritter);
                    Employee emp = map.getValue();
                    String value = emp.employeeId + "," + emp.employeeName + ","
                            + emp.employeeEmail + "," + emp.employeeAge + ","
                            + emp.dateOfBirth;
                    bufWriter.write(value);
                    bufWriter.newLine();
                    bufWriter.close();
                }
                System.out.println("Employee deleted Successfully");
            } catch (FileNotFoundException exception) {
                System.out.println(exception);
            } catch (IOException exception) {
                System.out.println(exception);
            } catch (Exception exception) {
                System.out.println(exception);
            }
        } else {
            System.out.println("Employee is not Present ");
        }
    }

    // Search the Employee By Id
    public void searchById(int empId) {
        if (employeesDetails.containsKey(empId)) {
            // get the employee by Id
            Employee emp = employeesDetails.get(empId);
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
        // employees file is empty
        if (employeesDetails.size() == 0) {
            System.out.println("No Employee is present");
            return;
        }

        // show employees data in tabular form
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("| %5s | %11s | %12s | %21s | %7s | %11s |%n", "Index", "EMPLOYEE ID", "NAME", "EMAIL", "AGE",
                "DOB");
        System.out.println("--------------------------------------------------------------------------------------");
        int count = 1;
        for (Map.Entry<Integer, Employee> map : employeesDetails.entrySet()) {
            Employee emp = map.getValue();
            System.out.format("| %6d| %12s| %13s| %22s| %8d| %12s|%n", count, emp.employeeId, emp.employeeName,
                    emp.employeeEmail, emp.employeeAge, emp.dateOfBirth);
            count++;
        }
        System.out.println("--------------------------------------------------------------------------------------");
    }

    // Comparator for Sort the Detail By Id
    public Comparator<Employee> comparatorById(boolean order) {
        if (order) {
            return (firstValue, secondValue) -> Integer.compare(firstValue.employeeId, secondValue.employeeId);
        } else {
            return (firstValue, secondValue) -> Integer.compare(secondValue.employeeId, firstValue.employeeId);
        }
    }

    // Comparator for Sort the Detail By Name
    public Comparator<Employee> comparatorByName(boolean order) {
        if (order) {
            return (firstValue, secondValue) -> firstValue.employeeName.compareTo(secondValue.employeeName);
        } else {
            return (firstValue, secondValue) -> secondValue.employeeName.compareTo(firstValue.employeeName);
        }
    }

    // Comparator for Sort the Detail By Id
    public Comparator<Employee> comparatorByAge(boolean order) {
        if (order) {
            return (firstValue, secondValue) -> Integer.compare(firstValue.employeeAge, secondValue.employeeAge);
        } else {
            return (firstValue, secondValue) -> Integer.compare(secondValue.employeeAge, firstValue.employeeAge);
        }
    }

    // Comparator for Sort the Detail By Id
    public Comparator<Employee> comparatorByDate(boolean order) {
        if (order) {
            return (firstValue, secondValue) -> firstValue.dateOfBirth.compareTo(secondValue.dateOfBirth);
        } else {
            return (firstValue, secondValue) -> secondValue.dateOfBirth.compareTo(firstValue.dateOfBirth);
        }
    }

    // search the employees data by text and sort them by Id, Name, Age and Dob
    void searchByText(String text, PriorityQueue<Employee> sortDetails) {
        // employee file is empty
        if (employeesDetails.size() == 0) {
            System.out.println("No employee is present");
            return;
        }
        for (Map.Entry<Integer, Employee> employeeDetail : employeesDetails.entrySet()) {
            Employee emp = employeeDetail.getValue();
            String checkStr = emp.employeeId + "," + emp.employeeName + "," + emp.employeeEmail + "," + emp.employeeAge
                    + "," + emp.dateOfBirth;

            // check the text is present in employee data
            if (checkStr.contains(text)) {
                sortDetails.add(emp);
            }
        }

        // No employee is find related to text
        if (sortDetails.isEmpty()) {
            System.out.println("No employee is found");
            return;
        }

        // show the employees data in tabular form
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("| %5s | %11s | %12s | %21s | %7s | %11s |%n", "Index", "EMPLOYEE ID", "NAME", "EMAIL", "AGE",
                "DOB");
        System.out.println("--------------------------------------------------------------------------------------");
        int count = 1;
        while (!sortDetails.isEmpty()) {
            Employee emp = sortDetails.poll();
            System.out.format("| %6d| %12s| %13s| %22s| %8d| %12s|%n", count, emp.employeeId, emp.employeeName,
                    emp.employeeEmail, emp.employeeAge, emp.dateOfBirth);
            count++;
        }
        System.out.println("--------------------------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        EmployeeData employeeData = new EmployeeData();
        try (Scanner input = new Scanner(System.in)) {
            String choice;
            do {
                System.out.println();
                System.out.println("Menu options");
                System.out.println("1.Add The Employee Detail");
                System.out.println("2.Delete the Empoyee Detail");
                System.out.println("3.Get all Employees Data");
                System.out.println("4.Search the Employee by id");
                System.out.println("5.Sort and Search the Employee By Text");
                System.out.println("6.Exit");
                choice = input.next();
                switch (choice) {
                    case "1":
                        System.out.println("Enter the Employee Id");
                        String id = input.next();
                        // check the employee id is valid or not
                        while (!employeeData.isValidId(id)) {
                            System.out.println("Id is invalid");
                            System.out.println("->Enter valid Id");
                            id = input.next();
                        }
                        System.out.println("Enter the Employee Name -");
                        String name = input.next();
                        // check the employee name is valid or not
                        while (!employeeData.isValidName(name)) {
                            System.out.println("Name is invalid");
                            System.out.println("->Enter valid Name");
                            name = input.next();
                        }
                        System.out.println("Enter the Employee Email");
                        String email = input.next();
                        // check the employee email is valid or not
                        while (!employeeData.isValidEmail(email)) {
                            System.out.println("Email is invalid");
                            System.out.println("->Enter valid Email");
                            email = input.next();
                        }
                        System.out.println("Enter the Employee Age");
                        String age = input.next();
                        // check the employee age is valid or not
                        while (!employeeData.isValidAge(age)) {
                            System.out.println("Age is invalid");
                            System.out.println(">Enter the Valid age");
                            age = input.next();
                        }
                        System.out.println("Enter the Date of birth(yyyy-mm-dd)");
                        String date = input.next();
                        
                        // check the employee Dob is valid or not
                        while (!employeeData.isValidDob(date)) {
                            System.out.println("Dob is invalid");
                            System.out.println("->Enter the Valid Date of birth(yyy-mm-dd)");
                            date = input.next();
                        }
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate date1 = LocalDate.parse(date, format);
                        employeeData.employee.setDate(date1);
                        employeeData.employee.setId(Integer.parseInt(id));
                        employeeData.employee.setName(name);
                        employeeData.employee.setEmail(email);
                        employeeData.employee.setAge(Integer.parseInt(age));
                        employeeData.employeeAdd();
                        break;

                    case "2":
                        System.out.println("Enter the EmployId which you want to deleted");
                        String employeeId = input.next();
                        // check the employee Id is valid or not
                        while (!employeeData.isValidId(employeeId)) {
                            System.out.println("Id is invalid");
                            System.out.println("->Enter valid Id");
                            employeeId = input.next();
                        }
                        employeeData.deleteDetails(Integer.parseInt(employeeId));
                        break;

                    case "3":
                        System.out.println("Employee Details");
                        System.out.println();
                        // show all the employees Details
                        employeeData.showEmployeesDetail();
                        break;

                    case "4":
                        System.out.println("Enter the employee Id which you want to search");
                        int searchEmpById = input.nextInt();
                        // Search Employee By Id
                        employeeData.searchById(searchEmpById);
                        break;

                    case "5":
                        System.out.println("Enter Any text You want to search");
                        String textForSearch;
                        textForSearch = input.next();
                        // Use Priority Queue to store the Employee details in sorted(Assending or
                        // Descending) Order
                        PriorityQueue<Employee> sortDetails = new PriorityQueue<>();
                        boolean Order = false;
                        String orderChoice;
                        do {
                            System.out.println("Enter in which Order you want to sort the Employee");
                            System.out.println("1.Asscending");
                            System.out.println("2.Descending");
                            orderChoice = input.next();

                            switch (orderChoice) {
                                case "1":
                                    Order = true;// for Asscending
                                    break;
                                case "2":
                                    Order = false;// for Descending
                                    break;
                                default:
                                    System.out.println("Please Enter Valid order");
                            }
                        } while (!orderChoice.equals("1") && !orderChoice.equals("2"));

                        String parameterChoice;
                        do {
                            System.out.println("Enter the parameter Which you want to sort");
                            System.out.println("1. Sort the Employee on the basis of Employee Id");
                            System.out.println("2. Sort the Employee on the basis of employee Name");
                            System.out.println("3. Sort the employee on the basis of Age");
                            System.out.println("4. sort the employee on the basis of Date of Birth");
                            parameterChoice = input.next();
                            switch (parameterChoice) {
                                case "1":
                                    // for Sort the Searched data by Id
                                    sortDetails = new PriorityQueue<>(employeeData.comparatorById(Order));
                                    break;
                                case "2":
                                    // for Sort the Searched data by Name
                                    sortDetails = new PriorityQueue<>(employeeData.comparatorByName(Order));
                                    break;
                                case "3":
                                    // sort the data by Age
                                    sortDetails = new PriorityQueue<>(employeeData.comparatorByAge(Order));
                                    break;
                                case "4":
                                    // sort the data by Date of Birth
                                    sortDetails = new PriorityQueue<>(employeeData.comparatorByDate(Order));
                                    break;

                                default:
                                    System.out.println("Please Enter the valid Parameter ");
                                    break;
                            }
                        } while (!parameterChoice.equals("1") && !parameterChoice.equals("2")
                                && !parameterChoice.equals("3")
                                && !parameterChoice.equals("4"));
                        employeeData.searchByText(textForSearch, sortDetails);
                        break;

                    case "6":
                        System.out.println("Thankyou");
                        break;

                    default:
                        System.out.println("Invalid Choices");
                        break;
                }
            } while (!choice.equals("6"));
        } catch (DateTimeParseException exception) {
            System.out.println(exception);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}