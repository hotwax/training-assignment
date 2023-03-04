class Student {
  constructor(firstName, dateOfBirth, address) { // old format
    this.firstName = firstName;
    this.dateOfBirth = dateOfBirth;
    this.address = address;
  }

  // constructor(firstName, dateOfBirth, address) { // new format
  //   this.firstName = firstName;
  //   this.dateOfBirth = new Date(dateOfBirth);
  //   this.address = address;
  // }
}

module.exports = Student;