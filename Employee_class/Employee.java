package Employee_class;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Employee {

    private static int globalID = 0;
    private String name;
    private String emailAddress;
    private int age;
    private Date dateOfBirth;
    private int id;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");

    public Employee(String name, String email, int age, Date dob) {
        this.id = globalID++;
        this.name = name;
        this.emailAddress = email;
        this.age = age;
        this.dateOfBirth = dob;
    }

    //return the name
    public String getName() {
        return name;
    }

    //param name the name to set     
    public void setName(String name) {
        this.name = name;
    }

    //return the emailAddress
    public String getEmailAddress() {
        return emailAddress;
    }

    //param emailAddress the emailAddress to set     
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    //return the age
    public int getAge() {
        return age;
    }

    //param age the age to set
    public void setAge(int age) {
        this.age = age;
    }

    //return the dateOfBirth
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public int getId() {
        return id;
    }

    //param dateOfBirth the dateOfBirth to set
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    //Overriding toString() method

    @Override
    public String toString() {

        return getId() + ", " + getName() + ", " + getEmailAddress() + ", " + getAge() + ", "
                + sdf.format(dateOfBirth);
    }

    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true 
        if (o == this) {
            return true;
        }
        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Employee)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Employee c = (Employee) o;

        // Compare the data members and return accordingly
        return this.emailAddress.equals(c.emailAddress);
    }

    @Override
    public int hashCode() {
        return emailAddress.hashCode();
    }

}
