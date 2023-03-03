//student calss implementation
class Student {
  constructor(firstName, dateOfBirthStr, address) {
    this.firstName = firstName;
    this.dateOfBirth = new Date(dateOfBirthStr);
    this.address = address;
  }
}

module.exports = Student;