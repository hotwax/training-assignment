// Import the Node.js 'fs' module for file system operations.
import fs from 'fs';

// Import the Student and Address classes from the './Student.js' module.
import { Student } from './Student.js';
import { Address } from './Student.js';


// Create four Student objects with different data.
const student1 = new Student('John', '1999-01-01', new Address('New York', 'NY', 10001, 'USA'));
const student2 = new Student('Mary', '2000-02-02', new Address('Los Angeles', 'CA', 90001, 'USA'));
const student3 = new Student('Bob', '2001-03-03', new Address('Chicago', 'IL', 60007, 'USA'));
const student4 = new Student('Alice', '2002-04-04', new Address('Houston', 'TX', 77001, 'USA'));


// Store the four Student objects in an array.
const students = [student1, student2, student3, student4];

// Get the filename from the command-line arguments passed to the script.
const filename = process.argv[2];

// If the filename does not end with the .ser extension, display an error message and exit the script.
if (!filename.endsWith('.ser')) {
  console.log('Invalid file extension. File must have .ser extension.');
  process.exit(1);
}

// Convert the array of Student objects to a JSON string.
const data = JSON.stringify(students);

// Write the JSON string to a file with the specified filename.
fs.writeFileSync(filename, data);
