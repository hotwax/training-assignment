// Address Class
class Address {
    constructor(city, state, pinCode, country) {
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
        this.country = country;
    }
}

// Student Class
class Student {
    constructor(firstName, dateOfBirth, address) {
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }
}


// Creating Student objects 
const students = [
    new Student("John", "2000-01-01", new Address("New York", "NY", 12345, "USA")),
    new Student("Jane", "2001-02-02", new Address("London", "LDN", 67890, "UK")),
    new Student("Bob", "1999-03-03", new Address("Paris", "PRS", 24680, "France")),
    new Student("Alice", "1998-04-04", new Address("Tokyo", "TKO", 13579, "Japan")),
];

// This module allows you to work with the file system on your computer
const fs = require("fs");

// Serialization
const output1 = "output1.json";
const output2 = "output2.json";


// Serializing the student object to JSON

try {
    fs.writeFileSync(output1, JSON.stringify(students));
    console.log(`\nSerialization successful. Results saved in file: ${output1}`);
} catch (err) {
    console.log("Not able to write in the file.");
}

// Serializing the student object to JSON

try {
    fs.writeFileSync(output2, JSON.stringify(students));
    console.log(`\nSerialization successful. Results saved in file: ${output2}`);
} catch (err) {
    console.log("Not able to write in the file.");
}

// Deserialization

const input1 = "output1.json";
const input2 = "output2.json";

// Converting the plain JS objects to Student instances.
try {
    const data1 = JSON.parse(fs.readFileSync(input1));
    console.log(`\nDeserialization successful. Results from file ${input1}:`);
    // Showing final results.
    console.log(data1);
    console.log("\n\n\n");
} catch (err) {
    console.log("Not able to read from the file.")
}

// Converting the plain JS objects to Student instances.

try {
    const data2 = JSON.parse(fs.readFileSync(input2));
    console.log(`\nDeserialization successful. Results from file ${input2}:`);
    // Showing final results.
    console.log(data2);
} catch (err) {
    console.log("Not able to read from the file.")
}
