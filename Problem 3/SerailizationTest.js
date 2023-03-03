import fs from 'fs';
import Address from './Address.js';
import Student from './Student.js';

const students = [
  new Student(
    'Rishabh',
    '2022-05-11',
    new Address('Indore', 'MP', 452001, 'India')
  ),
  new Student(
    'Rishabh',
    '2022-05-11',
    new Address('Indore', 'MP', 452001, 'India')
  ),
  new Student(
    'Rishabh',
    '2022-05-11',
    new Address('Indore', 'MP', 452001, 'India')
  ),
  new Student(
    'Rishabh',
    '2022-05-11',
    new Address('Indore', 'MP', 452001, 'India')
  ),
  new Student(
    'Rishabh',
    '2022-05-11',
    new Address('Indore', 'MP', 452001, 'India')
  ),
  new Student(
    'Rishabh',
    '2022-05-11',
    new Address('Indore', 'MP', 452001, 'India')
  ),
];
const FILENAME = 'output1.json';

const data = JSON.stringify(students);

fs.writeFile(FILENAME, data, (error) => {
  if (error) throw error;
  console.log(`Data written to ${FILENAME}`);
});
