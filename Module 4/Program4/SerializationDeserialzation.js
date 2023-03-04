class Address {
    constructor(city, state, pinCode, country) {
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
        this.country = country;
    }
}

class Student {
    constructor(firstName, dateOfBirth, address) {
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }
}

const students = [
    new Student("John", "2000-01-01", new Address("New York", "NY", 12345, "USA")),
    new Student("Jane", "2001-02-02", new Address("London", "LDN", 67890, "UK")),
    new Student("Bob", "1999-03-03", new Address("Paris", "PRS", 24680, "France")),
    new Student("Alice", "1998-04-04", new Address("Tokyo", "TKO", 13579, "Japan")),
];

const fs = require("fs");

// Serialization
const output1 = "output1.json";
const output2 = "output2.json";

fs.writeFileSync(output1, JSON.stringify(students));
console.log(`\nSerialization successful. Results saved in file: ${output1}`);

fs.writeFileSync(output2, JSON.stringify(students));
console.log(`\nSerialization successful. Results saved in file: ${output2}`);

// Deserialization
const input1 = "output1.json";
const input2 = "output2.json";

const data1 = JSON.parse(fs.readFileSync(input1));
console.log(`\nDeserialization successful. Results from file ${input1}:`);
console.log(data1);

const data2 = JSON.parse(fs.readFileSync(input2));
console.log(`\nDeserialization successful. Results from file ${input2}:`);
console.log(data2);
