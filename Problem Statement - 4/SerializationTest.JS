/*
In JavaScript you can serialize an object to a JSON string by calling the 
function JSON. stringify() which is used in this program and to deserialize JSOn parse() is
used
*/

const prompt = require('prompt-sync')();
const fs = require('fs');

//creating a class Student
class Student{
    constructor(firstName, dateOfBirth, address ){
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth,
        this.address = address;
    }
}

//Creating address class
class Address{
    constructor(city, state, pinCode, country){
        this.city = city;
        this.State = state;
        this.pinCode = pinCode;
        this.country = country;
    }
}

// function to create 4 objects of student place them in list and and serialize (JSON stringify)
const createObjectsAndStringify = () => {

    //Creating student instances with address instance
    const student1 = new Student("Sanskar", new Date(19/03/2001), new Address("Bamhori", "Madhya Pradesh", 464672, "India"));
    const student2 = new Student("Sumit", new Date(14/03/2002), new Address("Chindwara", "Madhya Pradesh", 464472, "India"));
    const student3 = new Student("Shubham", new Date(12/08/2001), new Address("Indore", "Madhya Pradesh", 464671, "India"));
    const student4 = new Student("Somya", new Date(01/02/2001), new Address("Mandsaur", "Madhya Pradesh", 464692, "India"));

    //Student objects list
    const students = [student1, student2, student3, student4];

    //Taking input from the user FILENAME
    const FILENAME = prompt("Enter the file name : ");

    //Creating a file and writing stringified students list
    try {
        fs.writeFileSync(FILENAME, JSON.stringify(students)); 
    } catch (error) {
        //Error Handling
        console.log("Error while eriting in a file " + FILENAME + " : " + error);
    }

    //Printing success message
    console.log("File Created Successfully and Stringified the data.");
}

//Calling the function to execute the logic
createObjectsAndStringify();