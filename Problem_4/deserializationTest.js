//importing the fs module for file handling
import fs from 'fs';

try{
// read the content of .ser file 
const deserializedData = fs.readFileSync('Output2.ser','utf-8');

//parsed the content of the file as JSON array and stored it
const parse = JSON.parse(deserializedData);
//iterate over the each elements of array and printing it
parse.forEach(element => {
    console.log(element.firstName) + console.log(element.dateOfBirth) 
    + console.log(element.address.city)
    + console.log(element.address.state)
    + console.log(element.address.pinCode) + console.log(element.address.country);    
});
//print the entire parsed data to the console
console.log(parse);
}
catch (error)
{
    console.log("File not Found");
}