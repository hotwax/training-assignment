const prompt = require('prompt-sync')();
const fs = require('fs');
const { exit } = require('process');

// creating a fucntion to read data from the file and parse it
const readFromFile = () => {

    //taking Input from the user
    const FILENAME = prompt("Enter the filename : ")

    //Reading from File
    let data;
    try {
        data = fs.readFileSync(FILENAME);
    } catch (error) {

        if(error.code = "ENOENT"){
            console.log(FILENAME + " File does not exist!");
            exit();
        }else{           
            console.log("Erro while reading file " + FILENAME + " : " + error);
        }

    }

    //if data does not exist
    if(!data.length){
        console.log("Data does not exist in a file!");
        exit();
    }

    //student list from the data (Parsing the data) 
    try {
        const students = JSON.parse(data);
    } catch (error) {
        console.log(error);
        exit();
    }

    //Printing the data i.e. Students List
    console.log(students);
}

//Calling the function for logic created
readFromFile();