// Importing file system and readline module
const fs = require("fs");
const readline = require("readline");

// Creating readline interface
var rl = readline.createInterface(process.stdin, process.stdout);
// Defining Student and Address classes
class Student {
  constructor(name, dob, address) {
    this.name = name;
    this.dob = dob;
    this.address = address;
  }
}

class Address {
  constructor(city, state, pincode, country) {
    this.city = city;
    this.state = state;
    this.pincode = pincode;
    this.country = country;
  }
}
// Defining main function
const main = () => {
    // Creating four student objects
  const Student1 = new Student(
    "Pushpraj Singh",
    new Date(10 / 03 / 2001),
    new Address("Indore", "Madhya Pradesh", 452001, "India")
  );
  const Student2 = new Student(
    "Anuj Pawar",
    new Date(15 / 04 / 2001),
    new Address("Bhopal", "Madhya Pradesh", 462801, "India")
  );
  const Student3 = new Student(
    "Gourav Singh",
    new Date(17 / 12 / 2001),
    new Address("Indore", "Madhya Pradesh", 452001, "India")
  );
  const Student4 = new Student(
    "Shivam Shukla",
    new Date(21 / 10 / 2002),
    new Address("Badnawar", "Madhya Pradesh", 452521, "India")
  );

  // Storing all students data in an array
  const data = [Student1, Student2, Student3, Student4];
  const content  = JSON.stringify(data);

  rl.question("Enter the File name : ", (filename) => {
    try {
        // Writing JSON string data to a file with given filename
      fs.writeFileSync("./"+filename+".ser",content);
      // file written successfully
      console.log("File written successfully")
      // Closing readline interface
      rl.close();
    } catch (err) {
        // Printing error message on console in case of error
      console.error("Unable to write data in a file");
      rl.close();
    }
  });
};
// Calling main function to start the program
main();
