const fs = require('fs');
const Student = require('./Student');
const Address = require('./Address');

const students = [
  new Student('Dhruv', new Date('1990-01-01'), new Address('Indore', 'mp', 452011, 'india')),
  new Student('Sadhana', new Date('1995-02-02'), new Address('Bhopal', 'mp', 452013, 'india')),
  new Student('Sanskar', new Date('2000-03-03'), new Address('China', 'mp', 132424, 'china')),
  new Student('Rishabh', new Date('2005-04-04'), new Address('indore', 'mp', 456034, 'india'))
];

const fileName = process.argv[2];
const data = JSON.stringify(students);

fs.writeFile(fileName, data, err => {
  if (err) throw err;
  console.log(`Serialized data saved to ${fileName}`);
});