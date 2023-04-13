// Address class
class Address {
  constructor(city, state, pinCode, country) {
    this.city = city;
    this.state = state;
    this.pinCode = pinCode;
    this.country = country;
  }
}

// // Student class
// class Student {
//   constructor(firstName, dateOfBirth, address) {
//     this.firstName = firstName;
//     this.dateOfBirth = dateOfBirth;
//     this.address = address;
//   }
// }
// Student class

//Updated Student Class
class Student {
  constructor(firstName, dateOfBirth, address) {
    this.firstName = firstName;
    this.dateOfBirth = new Date(dateOfBirth);
    this.address = address;
  }
}

// SerializationTest class
class SerializationTest {
  static serializeToFile(filename, students) {
    const fs = require('fs');
    const data = JSON.stringify(students);
    fs.writeFileSync(filename, data);
  }
}

// creating sample Address objects
const address1 = new Address('Mumbai', 'Maharashtra', 400001, 'India');
const address2 = new Address('Kolkata', 'West Bengal', 700001, 'India');
const address3 = new Address('Bangalore', 'Karnataka', 560001, 'India');
const address4 = new Address('Chennai', 'Tamil Nadu', 600001, 'India');

// creating sample Student objects
const student1 = new Student('Aarav', '1995-09-10', address1);
const student2 = new Student('Sneha', '1990-10-18', address2);
const student3 = new Student('Rohan', '1988-07-05', address3);
const student4 = new Student('Ananya', '1997-11-22', address4);

// serializing and saving students to files
SerializationTest.serializeToFile('output1.ser', [student1, student2]);
SerializationTest.serializeToFile('output2.ser', [student3, student4]);

