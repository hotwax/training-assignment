package Program2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Code {
    // Employee class with properties name, email, age, date of birth following Java
    // Bean standards
    public class Employee {
        private String name; // To get name of the employee.
        private String email; // to get email of the employee.
        private int age; // for age of the employee.
        private LocalDate dateOfBirth; // to get DOB of the employee.

        // Constructor:
        public Employee(String name, String email, int age, LocalDate dateOfBirth) {
            this.name = name;
            this.email = email;
            this.age = age;
            this.dateOfBirth = dateOfBirth;
        }

        // Getter method to get the name.
        public String getName() {
            return name;
        }

        // Setter method to set the name.
        public void setName(String name) {
            this.name = name;
        }

        // Getter method to get the email.
        public String getEmail() {
            return email;
        }

        // Setter method to set the email.
        public void setEmail(String email) {
            this.email = email;
        }

        // getter method to get the age.
        public int getAge() {
            return age;
        }

        // Setter method to set the age.
        public void setAge(int age) {
            this.age = age;
        }

        // Getter method to get the DOB.
        public LocalDate getDateOfBirth() {
            return dateOfBirth;
        }

        // Setter method to set DOB.
        public void setDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Email: " + email + ", Age: " + age + ", Date of Birth: " + dateOfBirth;
        }
    }

    public class Main extends Employee {
        private static final String FILE_NAME = "employees.txt";
        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        private static List<Employee> employees = new ArrayList<>();

        public static void main(String[] args) {
            loadDataFromFile();
            Scanner sc = new Scanner(System.in);
            int choice = 0;
            do {
                System.out.println("1. Add");
                System.out.println("2. Delete");
                System.out.println("3. Search");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        addEmployee(sc);
                        break;
                    case 2:
                        deleteEmployee(sc);
                        break;
                    case 3:
                        searchEmployee(sc);
                        break;
                    case 4:
                        System.out.println("Exiting the program...");
                        break;
                    default:
                        System.out.println("Invalid choice, try again...");
                }
            } while (choice != 4);
            sc.close();
        }

        // method

    }
}
