const fs = require('fs');
const prompt = require('prompt-sync')();


//employee class whose object we will create later.
class Employee {
    constructor(name, email, age, dob) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.dob = dob;
    }
}

// function to read data present in employee.txt file.
function readEmployeesFromFile() {
    //reading the file.
    const fileContent = fs.readFileSync('employees.txt', 'utf-8');
    //the file I am trying to read is using CRLF line endings, meaning that each line ends with the \r\n sequence.
    //that why removing that and then spliting each url.
    const lines = fileContent.toString().replace(/\r/g, "").split("\n")
    //taking entries one by one and creating an object of Employee class after spliting each entry.
    const employees = lines.map(line => {
        const [name, email, age, dob] = line.split(',');
        return new Employee(name, email, age, dob);
    });
    //return that newly created object.
    return employees;
}

function menu() {
    //firstly loading all data present in employee.txt file to employees array.
    const employees = readEmployeesFromFile();
    console.log(`There are ${employees.length} employees in the system.`);
    console.log('1. Add employee');
    console.log('2. Delete employee');
    console.log('3. Search employees');
    console.log('4. Exit');
    const choice = prompt('Enter your choice: ');
    switch (choice) {
        case '1':
            //calling method to add employee
            addEmployee(employees);
            break;
        case '2':
            //calling method to delete employee
            deleteEmployee(employees);
            break;
        case '3':
            //calling method to search employee
            searchEmployees(employees);
            break;
        case '4':
            //exiting the program
            console.log('Exiting...');
            break;
        default:
            //calling method to add employee
            console.log('Invalid choice.');
            menu();
            break;
    }

}


function addEmployee(employees) {
    //taking the name of employee from user
    let name = prompt('Enter employee name: ');
    //regex to match if user has entered wrong name
    const nameRegex=/^[A-Za-z]+(?:\s+[A-Za-z]+)*$/;
    //loop until user don't enter right name in provided format
    while(!nameRegex.test(name)) {
        name = prompt('Invalid name. ENTER AGAIN: ');
      }

    //taking the email of employee from user
    let email = prompt('Enter employee email: ');
    //regex to match if user has entered wrong email
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    //loop until user don't enter right email in provided format
    while(!emailRegex.test(email)) {
        email = prompt('Invalid email. ENTER AGAIN: ');
    }

    //taking the age of employee from user
    let age = parseInt(prompt('Enter age:'));
    //loop until user don't enter right age in range 0 to 120
    while(isNaN(age) || age < 0 || age > 120) {
        age = parseInt(prompt('Invalid age. ENTER AGAIN: '));
    }
    
    //taking the dob of employee from user
    let dob = prompt('Enter employee date of birth (DD/MM/YYYY): ');
    //regex to match if user has entered wrong name
    const dobRegex = /^(0[1-9]|[1-2][0-9]|3[0-1])\/(0[1-9]|1[0-2])\/\d{4}$/;
    //loop until user don't right date of birth in provided format
    while(!dobRegex.test(dob)) {
        dob = prompt('Invalid date of birth. ENTER AGAIN: ');
    }
    //creting a new employee object using above data.
    const employee = new Employee(name, email, age, dob);
    console.log(employees);
    //finding the index of an entry if it is present with same email ID.
    const index = employees.findIndex(employee => employee.email === email);
    // if the entry with same email is not present then
    if(index==-1){
        //add the new employee object to the employees array
        employees.push(employee);
        console.log(employees);
        //again save the updated data to file
        saveEmployeesToFile(employees);
        console.log('Employee added successfully.');
    }
    else{
        //dont do anything if the employee is already present
        console.log('Employee already present with same Email ID.');
    }
    //calling the menu method again.
    menu();
}

//function to delete employee.
function deleteEmployee(employees) {
    //storing the email id entered by user
    const id = prompt('Enter employee Email ID to delete: ');
    // finding if it is present. if present store their index in index variable. 
    const index = employees.findIndex(employee => employee.email === id);
    //if id is not found.
    if (index === -1) {
        console.log('Employee not found.');
    } else {
        //delete that employee from employees array.
        employees.splice(index, 1);
        //save the updated employees to file.
        saveEmployeesToFile(employees);
        console.log('Employee deleted successfully.');
    }
    menu();
}

//function to search employee and sort accordingly
function searchEmployees(employees) {
    //taking the query they want to search in entries.
    const query = prompt('Enter search query: ');
    //taking the field using which the want to sort the results.
    const sortBy = prompt('Sort by (name, email, age, dob): ');
    //taking the ascending or descending order by user.
    const sortOrder = prompt('Sort order (asc, desc): ');
    //filter the employee according to query.
    //search for query in every field
    const filteredEmployees = employees.filter(employee => {
        // console.log(employee);
        return (
            employee.name.includes(query) ||
            employee.email.includes(query) ||
            employee.age.includes(query) ||
            employee.dob.includes(query)
        );
    });

    //sorting the filtered employee using the order provided by user.
    const sortedEmployees = filteredEmployees.sort((a, b) => {
        //sorting in ascending order.
        if (sortOrder === 'asc') {
            return a[sortBy] > b[sortBy] ? 1 : -1;
        }
        //sorting in descending order.
        else {
            return a[sortBy] < b[sortBy] ? 1 : -1;
        }
    });
    console.log(`Found ${sortedEmployees.length} employees:`);
    //printing the searched and sorted employees in table format.
    console.table(sortedEmployees);
    //calling the menu function again.
    menu();
}

//function to save employees into the file. 
function saveEmployeesToFile(employees) {
    console.log(employees);
    //lines will store the forma of each entry
    const lines = employees.map(employee => {
        return `${employee.name},${employee.email},${employee.age},${employee.dob}`;
    });
    //joining the lines using '\n' and storing the final joined content into the fileContent variable
    const fileContent = lines.join('\n');
    //finally writing the fileContent into the employee.txt file
    fs.writeFileSync('employees.txt', fileContent, { flag: 'w' });
    console.log(lines);
}

//calling the menu function to start the program.
menu();