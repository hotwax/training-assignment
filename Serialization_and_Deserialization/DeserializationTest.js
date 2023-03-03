const fs = require('fs');
const Student = require('./Student');
const Address = require('./Address');

//storing the file name provided by user as an argument into fileName varaible.
const fileName = process.argv[2];

//reading from the povided file name.
fs.readFile(fileName, (err, data) => {
  if (err) throw err;
  //when receiving data from web server (in our case its from file), the data is always a string.
  //thats why converting that string into an object name students.
  const students = JSON.parse(data);

  //loging the data fetched inside students object
  for (const student of students) {
    console.log(`Name: ${student.firstName}`);
    console.log(`Date of Birth: ${student.dateOfBirth}`);
    console.log(`Address: ${student.address.city}, ${student.address.state} ${student.address.pinCode}, ${student.address.country}`);
    console.log('---');
  }
});