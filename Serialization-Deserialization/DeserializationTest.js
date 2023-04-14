// Import the Node.js 'fs' module for file system operations.
import fs from 'fs';

// Get the filename from the command-line arguments passed to the script.
const filename = process.argv[2];

// If the filename does not end with the .ser extension, display an error message and exit the script.
if (!filename.endsWith('.ser')) {
  console.log('Invalid file extension. File must have .ser extension.');
  process.exit(1);
}

// Read the contents of the file with the specified filename.
const data = fs.readFileSync(filename);

// Parse the contents of the file as a JSON string and convert it back to an array of objects.
const students = JSON.parse(data);

// Display the array of objects to the console.
console.log(students);
