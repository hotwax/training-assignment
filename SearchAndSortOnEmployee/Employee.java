import java.sql.Date;

class Employee {
  private String name;
  private int age;
  private String email;
  private Date dateOfBirth;

  Employee() {

  }

  // setters

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setDate(String dateString) {
    Date dateObject = Date.valueOf(dateString);
    this.dateOfBirth = dateObject;
  }

  // getters
  public String getName() {
    return this.name;
  }

  public int getAge() {
    return this.age;
  }

  public String getEmail() {
    return this.email;
  }

  public Date getDateOfBirth() {
    return this.dateOfBirth;
  }

}
