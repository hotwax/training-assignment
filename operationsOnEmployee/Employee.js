// Employee class 
class Employee {
  constructor(name, age, email, dob) {
    this.name = name;
    this.age = age;
    this.email = email;
    this.dateOfBirth = dob;
  }

  toString() {
    return "name: " + this.name + ", age: " + this.age + ", email: " + this.email + ", date of birth: " + this.dateOfBirth;
  }
}

module.exports = Employee;