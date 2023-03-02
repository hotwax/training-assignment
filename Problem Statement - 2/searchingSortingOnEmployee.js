import promptSync from 'prompt-sync';
import fs from 'fs';
// for current path
import path from 'path';
import { fileURLToPath } from 'url';
import { ReadLine } from 'readline'; 

// The __dirname and __filename global variables are defined in CommonJS 
// modules, but not in ES modules.
// We can fix the “__dirname is not defined in ES module scope” error by
// using certain functions to create a custom __dirname variable that
// works just like the global variable, containing the full path of
// the file’s current working directly.
const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);
const prompt = promptSync();
const readline = ReadLine.createInterface({
    input: process.stdin,
    output: process.stdout
});


// global variables --
let employeeArray = [];  // Employee list
let idCounter = 1; // for keeping track of employee id

    

// creating a function for menu
const showMenu = () => {

    console.log("Enter 1 For Add Employee to the file.");
    console.log("Enter 2 For Delete Employee From the file");
    console.log("Enter 3 For Searching from From the file");
    console.log("Enter any other number to Exit");

    const menuInput = prompt('');

    switch (menuInput) {
        case '1':
            addEmployee();
        break;
        case '2':
        console.log("1");
        break;
        case '3':
        console.log("3");
        break;
        default:
            break;
    }
     
}

const readFromEmployee = () => {
    const dataFromFile = fs.readFileSync(path.join(__dirname, 'employees.txt'), 'utf-8').split('\r\n');
    dataFromFile.forEach((data) => {
        const employeeObject = createObject(data);
        employeeArray.push(employeeObject);
        idCounter = Math.max(idCounter, employeeObject.id);
    })
}

// function to create object by giving a informatic string
function createObject(str){
    const tempArray = str.split("\\s*,\\s*");
    return {
        id: tempArray[0].trim(),
        name: tempArray[1].trim(),
        email: tempArray[2].trim(),
        age: tempArray[3].trim(),
        date: Date(tempArray[4].trim())
    }
}

async function addEmployee() {
    // Taking all the inputs
    console.log("Enter the Name : ");
    let name = await getInput();

    // For checking if the data entered by the user is valid or not
    let nameRegex = /^[a-zA-Z ]+$/;
    while (!name.match(nameRegex)) {
      console.log("\nEnter a valid name!! \n");
      name = await getInput();
    }

    console.log("Enter the Email : ");
    let email = await getInput();

    // For checking if the data entered by the user is valid or not
    let emailRegex = /^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$/;

    while (!email.match(emailRegex)) {
      console.log("\nEnter a valid Email!! \n");
      email = await getInput();
    }

    console.log("Enter the Age : ");
    let age = await getIntegerInput();

    console.log("Enter the Date Of Birth : ");
    let dobInString = await getInput();
    let dob = new Date(dobInString);
    let emp = {
      id: idCounter,
      name: name,
      email: email,
      age: age,
      dob: dob
    };

    // Adding employee to the list
    employeeArray.push(emp);

    // increasing id
    idCounter++;

    // ReWriting in a file --
    // rewriteTheFile();

}

function getInput() {
    return new Promise(resolve => {
        readline.question('', (answer) => {
        resolve(answer);
        });
    });
}

function getIntegerInput() {
    return new Promise(resolve => {
        readline.question('', async (answer) => {
        let intVal = parseInt(answer);
        if (isNaN(intVal)) {
            console.log("Enter a Valid Integer!!");
            intVal = await getIntegerInput(readline);
        }
        resolve(intVal);
        });
});
}

showMenu();