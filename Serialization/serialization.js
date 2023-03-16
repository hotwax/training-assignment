
const file = require('fs');  //for read and Write File
const readline = require("readline"); //Taking input .

// Student class
class Student {
  constructor(firstName, dateOfBirth, address) {
    this.firstName = firstName;
    // this.dateOfBirth = dateOfBirth ; //initially , date was string 
    this.dateOfBirth = new Date(dateOfBirth); // updat date format 
    this.address = address;
  }
}

// Address class
class Address {
  // Constructor of Address Class
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

  // Create a students object
  const students = [
    new Student('Harry', '2002-12-10', new Address("Newyork", "Newyork", 452002, "US")),
    new Student('Robin', '1990-10-20', new Address("Hongkong", "Hongkong", 452045, "China")),
    new Student('Tim', '2001-01-01', new Address("paris", "paris", 452076, "france")),
    new Student('Jensen', '2005-06-21', new Address("Berlin", "berlin", 452087, "Germany"))
  ];
  // Serialize the students object to JSON
  const objectsList = JSON.stringify(students); //serializing the data
  try {
    file.writeFileSync(filename, objectsList); //writting the data into file
  } catch (exception) {
    console.log("Something went wrong with file");
  }
  console.log("Operation Done");
  Main();// calling menu function again
}
//function to deserialize a given file
function deserialization(filename) {
 
  let objectsList;
  try {
    objectsList = file.readFileSync(filename, 'utf8');//reading data from it
  } catch(err) {
      console.log("File Not Found");
    Main();
    return ;
  }
  const deserializedObject = JSON.parse(objectsList);// deserializing the data

  // Convert JavaScript objects to Student instances

  const result = deserializedObject.map(value =>
    new Student(value.firstName, value.dateOfBirth,
      new Address(
        value.address.city,
        value.address.state,
        value.address.pinCode,
        value.address.country)));

  console.log("Operation Done");
  console.log(result); //printing final results
  Main();
}

function Main() {

  // Main Menu 
  console.log('\nChoose an Option :');
  console.log('1. Serialization ');
  console.log('2. Deserialization ');
  console.log('3. Exit');
  // Choice 
  read.question('\nEnter your choice: ', (option) => {
    switch (option) {
      case '1':
        // Give Name to the File => output1 , output2
        read.question('\nEnter the file name: ', (filename) => {
          serialization(filename);
        });
        break;
      case '2':
        // Enter file Name to deserialize the file 
        read.question('\nEnter the file name: ', (filename) => {
          deserialization(filename);
        });
        break;
      case '3':
        console.log("Exit");
        read.close();
        process.exit(1);

      default:
        // Invalid choice if otherthan 1 2 or 3 
        console.log('\nInvalid choice');
        Main();
        break;
    }
  });
}
Main();