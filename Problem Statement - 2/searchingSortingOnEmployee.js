/*
In this program instead of making a class for employee i 
have created json object for employee
*/

const prompt = require('prompt-sync')();
const fs = require('fs');
const moment = require('moment');

//filename
const FILENAME = "employees.txt";

// for storing all employees
let employeeList = [];

// creating a function for menu
const showMenu = async () => {

    console.log("Enter 1 For Add Employee to the file.");
    console.log("Enter 2 For Delete Employee From the file");
    console.log("Enter 3 For Searching from From the file");
    console.log("Enter 4 For show all employee from From the file");
    console.log("Enter any other number to Exit");

    let answer = prompt('');

    switch (answer) {
        case '1':
            addEmployee();
            break;
        case '2':
            deleteEmployee();
            break;
        case '3':
            searchEmployee();
            break;
        case '4':
            showAll();
            break;
        default:
            break;
    }
}

// for employee addition
const addEmployee = () => {

    //for employee name
    let name = prompt("Enter the Name : ");

    let nameRegex = /^[a-zA-Z ]+$/;
    while (!name.match(nameRegex)) {
      console.log("\nEnter a valid name!");
      name = prompt("Enter the Name : ");
    }

    //for email
    let email = prompt("Enter the Email : ")

    // For checking if the data entered by the user is valid or not
    let emailRegex = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;

    while (!email.match(emailRegex)) {
      console.log("\nEnter a valid Email!");
      email = prompt("Enter the Email : ")
    }

    //for age
    let age = prompt("Enter the Age : ");
    while(age <= 5 || age > 100 || isNaN(age+0)){
        console.log("\nEnter a valid Age!");
        age = prompt("Enter the Age : ");
    }

    // for date of birth
    console.log("Enter the date of birth : ");

    let day = prompt("Enter the day of birth : ");

    while(day < 1 || day > 31 || isNaN(day+0)){
        console.log("\nEnter a valid day!");
        day = prompt("Enter the day : ");
    }
    let month = prompt("Enter the month of birth : ");

    while(month < 1 || month > 12 || isNaN(month+0)){
        console.log("\nEnter a valid month!");
        month = prompt("Enter the month : ");
    }

    let year = prompt("Enter the year of birth : ");
    while(year > new Date().getFullYear() || isNaN(year+0)){
        console.log("\nEnter a valid year!");
        year = prompt("Enter the year : ");
    }
    
    //converting it in date format
    let dobInString = `${year}-${month}-${day}`;
    let dob = new Date(dobInString);

    //employee json object
    let emp = {
      name: name,
      email: email,
      age: age,
      dob: dob
    };

    // Adding employee to the list
    employeeList.push(emp);

    // ReWriting in a file --
    rewriteTheFile();

    //print statement
    console.log("\nEmployee Added SuccessFully\n");

    // Recalling the menu function
    showMenu();
}

// function to write in a file

const rewriteTheFile = () => {
    // Writing in a file --
    try {
        const lines = employeeList.map((employee) => {
            return `${employee.name},${employee.email},${employee.age},${new Date(employee.dob).toLocaleString().split(',')[0]}`;
        });
        const data = lines.join("\n"); // adding newLine character to data and inserting in file
        fs.writeFileSync(FILENAME, data, { flag: "w" });
    } catch (error) {
        console.error("Error writing file:", error);
    }
}

// Read all employees from file
const readEmployeesFromFile = () => {
    let employees = [];
    try {
        const data = fs.readFileSync(FILENAME, "utf8");

        // spliting data with newLine character and removing space
        const lines = data.trim().split("\n");

        //traversing through lines and making an object
        lines.forEach(line => {
            const [name, email, age, dob] = line.trim().split(",");
            employees.push({
                name: name,
                email: email,
                age: age,
                dob: Date(dob)
            });
        })
    } catch (error) {
        if(error.code = "ENOENT"){
            console.log(FILENAME + " File does not exist!");
            exit();
        }else{           
            console.log("Erro while reading file " + FILENAME + " : " + error);
        }
    }

    // return the created object list
    return employees;
}


//Function to delete employee
const deleteEmployee = () => {
    // Taking input from the user
    const empEmail = prompt('Enter the Employee Email to remove the employee : ');

    //removing the employee one with the given email
    employeeList = employeeList.filter(employee => {
        return employee.email != empEmail;
    })

    // calling rewrite file
    rewriteTheFile();

    //acknowledgement
    console.log("\nEmployee deleted Successfully\n");

    //For menu
    showMenu();
}

// for search operation
const searchEmployee = () => {
    //Taking input from the user (searches from all properties of emp)
    const query = prompt("Enter the query(text) to search for employee : ");

    //search result variable
    let searchResult = [];

    //searching
    employeeList.forEach(emp => {
        const searchString = emp.name + emp.email + emp.age + emp.dob;
        if(searchString.toLowerCase().includes(query.toLowerCase())){
            newEmp = emp;
            newEmp.dob = new Date(emp.dob).toLocaleString().split(',')[0];
            searchResult.push(newEmp);
        }
    })

    //sorting and asking how to sort
    console.log(
        '\nHow do you want to order the results - \n' + 
        'Enter 1 to Order By Name \n' +
        'Enter 2 to Order By Email \n' +
        'Enter 3 to Order By Age \n' +
        'Enter 4 to Order By Date of Birth \n'
        );
    let keyToSort = prompt('');
    while(keyToSort < 1 || keyToSort > 4){
        console.log('\nEnter a Valid Input!');
        keyToSort = prompt('');
    }

    //For Direction of sorting
    console.log('\nEnter the Direction to Sort\nEnter 1 for Ascending or 2 for Descending');
    let direction = prompt('');
    while(direction < 1 || direction > 2){
        console.log('\nEnter a Valid Input!');
        direction = prompt('');
    }

    searchResult = sortData(keyToSort, direction, searchResult);

    //printing the results
    console.log("\n Your Search Result : ");
    console.table(searchResult);
    //if no result found
    if(!searchResult.length){
        console.log("\nNo result found\n");
    }

    // for menu
    showMenu();
}

// show all function
const showAll = () => {
    //printing the all employees
    let searchResult = [];
    //searching
    employeeList.forEach(emp => {
        newEmp = emp;
        newEmp.dob = new Date(emp.dob).toLocaleString().split(',')[0];
        searchResult.push(newEmp);
    })
    console.table(searchResult);
    // for menu
    showMenu();
}

//Creating a function which sorts data in array on the basis arg 1 which is key
// and arg 2 which is direction and arg 3 the array which is data
const sortData = (key, direction, data) => {
    let sortedData = [];
    switch (key) {
        case '1':
            sortedData = data.sort((emp1, emp2) => {
                return direction = 1 ? (emp1.name > emp2.name ? 1 : -1) : (emp1.name < emp2.name ? 1 : -1);
            })
            break;
        case '2':
            sortedData = data.sort((emp1, emp2) => {
                return direction = 1 ? (emp1.email > emp2.email ? 1 : -1) : (emp1.email < emp2.email ? 1 : -1);
            })
            break;
        case '3':
            sortedData = data.sort((emp1, emp2) => {
                return direction = 1 ? (emp1.age > emp2.age ? 1 : -1) : (emp1.age < emp2.age ? 1 : -1);
            })
            break;
        case '4':
            sortedData = data.sort((emp1, emp2) => {
                return direction = 1 ? (emp1.dob > emp2.dob ? 1 : -1) : (emp1.dob < emp2.dob ? 1 : -1);
            })
            break;
        default:
            break;
    }

    return sortedData;
}

// Starting the login from here
employeeList = readEmployeesFromFile();

// Showing menu for User
showMenu();

