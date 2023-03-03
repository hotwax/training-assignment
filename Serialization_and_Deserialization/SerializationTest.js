const fs = require('fs');
const Student = require('./Student');
const Address = require('./Address');

//creating four objects and storing it into an Student constant.
const students = [
  new Student('Dhruv', new Date('1990-01-01'), new Address('Indore', 'mp', 452011, 'india')),
  new Student('Sadhana', new Date('1995-02-02'), new Address('Bhopal', 'mp', 452013, 'india')),
  new Student('Sanskar', new Date('2000-03-03'), new Address('China', 'mp', 132424, 'china')),
  new Student('Rishabh', new Date('2005-04-04'), new Address('indore', 'mp', 456034, 'india'))
];

//storing the file name provided by user in arguments.
const fileName = process.argv[2];
//when sending data to a server, the data has to be a string.
//that's why converting the object into a string and storing it in data.
const data = JSON.stringify(students);

//writing the stringfied object to file that are provided by user.
fs.writeFile(fileName, data, err => {
  if (err) throw err;
  console.log(`Serialized data saved to ${fileName}`);
});