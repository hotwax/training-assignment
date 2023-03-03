import fs from 'fs';

const FILENAME = 'output1.json';
fs.readFile(FILENAME, 'utf-8', (data, err) => {
  if (err) {
    console.log('error');
    throw err;
  }
  const students = JSON.parse(data);
  console.table(students);
});
