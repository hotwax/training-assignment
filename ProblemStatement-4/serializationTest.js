import fs from "fs"; // import the 'fs' module to work with file system
import { Student } from "./student.js"; 

const serializeObject = () => {
   const studentsObject = []; // create an empty array to store the instances of Student class
   // create the instances of Student class and store them in the 'studentsObject' array
  const student1 = new Student(
    "Gourav Rana",
    "03/11/2002",
    "Indore",
    "Madhya Pradesh",
    "452010",
    "India"
  );
  const student2 = new Student(
    "Yash Singhal",
    "02/17/2001",
    "Indore",
    "Madhya Pradesh",
    "452001",
    "India"
  );
  studentsObject.push(student1);
  studentsObject.push(student2);
  console.log("Students data before Serialization");
  console.log(studentsObject);

  //serialize the Objects and save them into .ser files
  const serializeStudent = JSON.stringify(studentsObject);
  try {
    fs.writeFileSync(process.argv[2], serializeStudent); // write the serialized data into a file whose name is given as an argument
    console.log(`Serialize the ${process.argv[2]}`);
  } catch (error) {
    console.log(error.message);
  }
};

// call the 'serializeObject' function
serializeObject();
