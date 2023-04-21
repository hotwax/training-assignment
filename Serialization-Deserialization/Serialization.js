import fileSystem from 'fs';
import readline from 'readline';

import { Student } from './Student.js';
import { Address } from './Address.js';


const read = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
  });


//function for serializing data in a given file
function serialization(filename) {

// Create four Student objects with different data.
const student1 = new Student('Ramlal', '1999-01-01', new Address('New York', 'NY', 10001, 'USA'));
const student2 = new Student('Raju', '2000-02-02', new Address('Los Angeles', 'CA', 90001, 'USA'));
const student3 = new Student('Parth', '2001-03-03', new Address('Chicago', 'IL', 60007, 'USA'));
const student4 = new Student('Shubendu', '2002-04-04', new Address('Houston', 'TX', 77001, 'USA'));

// Store the four Student objects in an array.
const students = [student1, student2, student3, student4];

// If the filename does not end with the .ser extension, display an error message and exit the script.
if (!filename.endsWith('.ser')) {
  console.log('Invalid file extension. File must have .ser extension.');
  process.exit(1);
}

// Convert the array of Student objects to a JSON string.
const data = JSON.stringify(students);

// Write the JSON string to a file with the specified filename.
fileSystem.writeFileSync(filename, data);
    console.log("Serialization done succesfully ..");

    enterChoice();
}


//function to deserialize a given file
function deserialization(filename) {
  if (!filename.endsWith('.ser')) {
    console.log('Invalid file extension. File must have .ser extension.');
    process.exit(1);
  }
  
  // Read the contents of the file with the specified filename.
  try{
  // Read the contents of the file with the specified filename.
  const data = fileSystem.readFileSync(filename);
  const students = JSON.parse(data);
  // Display the contents of the file.
  console.log(students);
  
  }
  catch(err){
    if (err.code === 'ENOENT') {
      console.error('file does not exist:' + filename);
    }
    else{
      console.error('error reading file:' + filename + ","  + err);
    }}
    enterChoice();
}

//function to display menu and get user's choice
function enterChoice() {
  // displaying menu 
  console.log('\nPlease choose an option:');
  console.log('1. Serialize file');
  console.log('2. Deserialize file');
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
        process.exit(0);
      default:
        //if user does'nt enter valid choice
        console.log('\nInvalid choice');
        enterChoice();
        break;
    }
  });
}

//calling the function to display menu
enterChoice();