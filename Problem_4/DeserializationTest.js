// Importing file system and readline module
const fs = require("fs");
const readline = require("readline");
// Creating readline interface
var rl = readline.createInterface(process.stdin, process.stdout);
// Prompting user to enter a file name to read data from
rl.question("Enter the file name : ", (filename)=>{
    try {
        // Reading file content synchronously and storing it in 'data' variable
        const data = fs.readFileSync('./'+filename+'.ser', 'utf8');
        // Converting file data from JSON string to objects and storing it in 'toobjects' variable
        const toobjects  = JSON.parse(data);
        // Iterating through each object and printing its properties on the console
        toobjects.forEach(item => {
            console.log(item.name+" "+ item.dob+" "+item.address.city+ " "+ item.address.state+" "+item.address.pincode+" "+item.address.country);
        });
        // Closing readline interface after successful file read
        rl.close();
      } catch (err) {
        // Printing error message on console in case of error
        console.error("Unable to read a file : ");
        rl.close();
      }
})