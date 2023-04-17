// imported fs module for file handling
import fs from 'fs';
// imported student class from Student.js file
import {Student} from "./Student.js";

// created an instance of Stundent class and stored them in 'students' objects array.
const students=[
new Student("Raj","12/15/2001", "Indore","Madhya Pradesh", "455002","India"),
new Student("Pranav","01/01/2004","Ujjain","Madhya Pradesh","453111","India"),
];

// Serialise the objects and write them in .ser file
try{fs.writeFileSync('Output2.ser', JSON.stringify(students));
// printing serialised data on console.
console.log(students);
console.log("Data Serialized Successfully");
}
catch(error){
    console.log("Unable to write in File.");
}

try{
// read file in which serialized data in stored
fs.readFileSync('Output2.ser','utf-8');
}
catch(error){
    console.log("Unable to read file");
}
