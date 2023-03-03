// Test serialization
const file = require('fs'); //for reading file and writing to a file
const readline = require("readline"); //for taking input from user.

// Student class
class Student {
  constructor(firstName, dateOfBirth, address) {
    this.firstName = firstName;
    // this.dateOfBirth = new String(dateOfBirth); //old format of date
    this.dateOfBirth = new Date(dateOfBirth);//updated format of date 
    this.address = address;
  }
}

// Address class
class Address {
  constructor(city, state, pinCode, country) {
    this.city = city;
    this.state = state;
    this.pinCode = pinCode;
    this.country = country;
  }
}

// to read file and write file from standard input/output devices.
const read = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

//function for serializing data in a given file
function serialization(filename) {
  // Create a student object
  const student = [
    new Student('Akash', '1990-01-01', new Address("Indore", "MP", 452001, "India")),
    new Student('Dev', '1992-02-02', new Address("Bhopal", "MP", 452045, "India")),
    new Student('Ishaan', '2001-01-01', new Address("Ujjain", "MP", 452076, "India")),
    new Student('Aarav', '2001-01-01', new Address("Dewas", "MP", 452087, "India"))
  ];
  // Serialize the student object to JSON
  const storedObjects = JSON.stringify(student); //serializing the data
  try {
    file.writeFileSync(filename, storedObjects); //writting the data into file
  } catch (exception) {
    console.log("Something went wrong with file");
  }
  console.log("Serialization done succesfully ..");
  enterChoice();// calling menu function again
}
//function to deserialize a given file
function deserialization(filename) {
  let storedObjects;
  try {
    storedObjects = file.readFileSync(filename, 'utf8');//reading data from it
  } catch (exception) {
    console.log("File not found");
  }
  const deserializedObject = JSON.parse(storedObjects);// deserializing the data

  // Convert the plain JavaScript objects to Student instances

  const result = deserializedObject.map(value =>
    new Student(value.firstName, value.dateOfBirth,
      new Address(
        value.address.city,
        value.address.state,
        value.address.pinCode,
        value.address.country)));

  console.log("Deserialization done succesfully ..");
  console.log(result); //printing final results
  enterChoice();
}
function enterChoice() {
  // displaying menu 
  console.log('\nPlease choose an option:');
  console.log('1. serialize file');
  console.log('2. deserialize file');
  console.log('3. Exit');
  //getting user's choice 
  read.question('\nEnter your choice: ', (option) => {
    switch (option) {
      case '1':
        //getting file name and calling the function
        read.question('\nEnter the file name: ', (filename) => {
          serialization(filename);
        });
        break;
      case '2':
        //getting file name and calling the function
        read.question('\nEnter the file name: ', (filename) => {
          deserialization(filename);
        });
        break;
      case '3':
        //closing the read object and terminating the program
        read.close();
        process.exit(1);
        break;
      default:
        //if user does'nt enter valid choice
        console.log('\nInvalid choice');
        enterChoice();
        break;
    }
  });
}
enterChoice();