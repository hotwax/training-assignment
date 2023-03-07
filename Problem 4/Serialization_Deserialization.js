import fs from 'fs';
import Address from './Address.js';
import Student from './Student.js';
import readline from 'readline';
// creating reader interface for user input
const read = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

/*
  JavaScript is a loosely typed language.
  JSON has no date type. It has only string, number, boolean, array, object, null.
*/

// creating an array of objects
const students = [
  new Student(
    'Rishabh',
    '2000-05-01',
    new Address('Indore', 'MP', 452001, 'India')
  ),
  new Student(
    'Adtiya',
    '2005-05-07',
    new Address('Lucknow', 'UP', 341021, 'India')
  ),
  new Student(
    'Sanskar',
    '2001-03-01',
    new Address('New York', 'NYC', 122534, 'USA')
  ),
  new Student(
    'Satyam',
    '2002-07-11',
    new Address('Paris', 'Île-de-France', 75001, 'France')
  ),
  new Student(
    'Navneet',
    '2022-05-01',
    new Address('Rome', 'Lazio', 4526874, 'Italy')
  ),
];
// filename according to the problem statement
const FILENAME = 'output1.json';
// const FILENAME = 'output2.json';

// Seralization
// converting the array of objects to JSON string i.e. Serialization
const serialization = () => {
  const data = JSON.stringify(students);
  // writing the JSON string to a file
  try {
    fs.writeFileSync(FILENAME, data, 'utf-8');
  } catch (error) {
    console.log(error);
  }
  console.log(`Object Serialization in ${FILENAME}`);
  choice();
};
const deserialization = () => {
  // Deserialization
  // reading the JSON string from the file
  let data;
  try {
    data = fs.readFileSync(FILENAME, 'utf-8');
  } catch (err) {
    console.log('Error in reading file');
    throw err;
  }
  // converting the JSON string to an array of objects i.e. Deserialization
  JSON.parse(data).forEach((student) => {
    console.log(
      `name :${student.name} | dob :${student.dob} | address :[${student.address.city}, ${student.address.state}, ${student.address.pincode}, ${student.address.country}]`
    );
    /* In both the cases of taking dob as string and date object,
       the type of dob is string while deserailizing
       we have to explicitly convert it to date object */
    console.log(typeof student.dob);
  });
  choice();
};

// main function
const choice = () => {
  console.log(
    `\nPRESS 1 : Serialization\nPRESS 2 : Deserialization\nPRESS 3 : Exit`
  );
  read.question('Enter your choice : ', (choice) => {
    switch (choice) {
      case '1':
        serialization();
        break;
      case '2':
        deserialization();
        break;
      case '3':
        read.close();
        process.exit(0);
        break;
      default:
        console.log('Invalid choice');
        choice();
    }
  });
};
choice();
