const fs = require('fs');
const Student = require('./Student');
const Address = require('./Address');

const fileName = process.argv[2];

fs.readFile(fileName, (err, data) => {
  if (err) throw err;
  const students = JSON.parse(data);

  for (const student of students) {
    console.log(`Name: ${student.firstName}`);
    console.log(`Date of Birth: ${student.dateOfBirth}`);
    console.log(`Address: ${student.address.city}, ${student.address.state} ${student.address.pinCode}, ${student.address.country}`);
    console.log('---');
  }
});