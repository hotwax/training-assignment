class Employee {
    int empID;
    String name;
    String email;
    int age;
    Date dateOfBirth;
    // Constructor
      public Employee(int empID,String name, String email, int age, Date dateOfBirth) {
        this.empID = empID;
        this.name = name;
        this.email = email;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
    }
    // Method to get Employee ID
    public int getEmpID() {
        return empID;
    }
    // Method to set Employee ID
    public void setEmpID(int empID) {
        this.empID = empID;
    }
    // Method to get Employee Name
    public String getName() {
        return name;
    }
    // Method to set Employee Name
    public void setName(String name) {
        this.name = name;
    }
    // Method to get Employee Email
    public String getEmail() {
        return email;
    }
    // Method to set Employee Email
    public void setEmail(String email) {
        this.email = email;
    }
    // Method to get Employee Age
    public int getAge() {
        return age;
    }
    // Method to set Employee Age
    public void setAge(int age) {
        this.age = age;
    }
    // Method to get Employee Date of Birth
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    // Method to set Employee Date of Birth
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    // Overriding toString() method to print Employee Details
    @Override
    public String toString() {
        return "Employee{" +
                "empID=" + empID +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", dateOfBirth='" + RunEmployee.simpleDateFormat.format(dateOfBirth) + '\'' +
                '}';
    }
}