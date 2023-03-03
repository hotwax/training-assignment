// Define the Address class
class Address {
    constructor(city, state, pinCode, country) {
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
        this.country = country;
    }
}

// Define the Student class
class Student {
    constructor(firstName, dateOfBirth, address) {
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }
}


const student1 = new Student('John', '1990-01-01', new Address('New York', 'NY', 10001, 'USA'));
const student2 = new Student('Sarah', '1995-02-14', new Address('Los Angeles', 'CA', 90001, 'USA'));
const student3 = new Student('Mike', '1998-05-20', new Address('Chicago', 'IL', 60601, 'USA'));
const student4 = new Student('Emily', '2000-12-31', new Address('Houston', 'TX', 77001, 'USA'));
// Put the students in an array
const students = [student1, student2, student3, student4];

// Serialize the students array to a file
const fs = require('fs');
const fileName1 = 'output1.json';
const fileName2 = 'output2.json';

fs.writeFileSync(fileName1, JSON.stringify(students));
fs.writeFileSync(fileName2, JSON.stringify(students));

// Deserialize the students array from a file
const data1 = fs.readFileSync(fileName1);
const data2 = fs.readFileSync(fileName2);

const students1 = JSON.parse(data1);
const students2 = JSON.parse(data2);
console.log("\n");
console.log('Data from ' + fileName1 + ':');
console.log(students1);
console.log("\n\n\n");

console.log('Data from ' + fileName2 + ':');
console.log(students2);