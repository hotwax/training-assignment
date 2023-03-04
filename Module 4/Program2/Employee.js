const fs = require("fs");
const readline = require("readline");

//class Employee with constructor for its initialization
class Employee {
    constructor(name, email, age, dob) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.dob = dob;
    }
    // overriding it to produce meaningful output
    toString() {
        return `Name: ${this.name}, Email: ${this.email}, Age: ${this.age}, DOB: ${this.dob}`;
    }
}

const FILENAME = "employees.txt";

//creating read object to read from a file all over in this .js file
const read = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});


// Read all employees from file
function readDataFromFile() {
    let employees = [];
    try {
        const data = fs.readFileSync(FILENAME, "utf8");
        // spliting data with newLine character and removing space
        const lines = data.trim().split("\n");
        for (let line of lines) {
            const [name, email, age, dob] = line.trim().split(",");
            const employee = new Employee(name, email, parseInt(age), new Date(dob).toLocaleDateString().split(' ')[0]);
            employees.push(employee);
        }
    } catch (err) {
        console.error("Error in reading file:", err);
    }
    return employees;
}
//function to display all over data we have in the file
function displayShowAll() {
    return new Promise(() => {
        console.log(readDataFromFile());
        choice();
    }
    );
}

// Write employees to file
function writeDataToFile(employees) {
    try {
        const lines = employees.map((employee) => {
            return `${employee.name},${employee.email},${employee.age},${employee.dob}`;
        });
        const data = lines.join("\n"); // adding newLine character to data and inserting in file
        fs.writeFileSync(FILENAME, data, { flag: "w" });
    } catch (err) {
        console.error("Error writing file:", err);
    }
}
// Adding employees to file
function addEmployeeInfoToFile() {
    read.question('\nEnter the employee name: ', (name) => {
        read.question('Enter the email address: ', (email) => {
            read.question('Enter the employee age: ', (age) => {
                read.question('Enter the date of birth (YYYY-MM-DD): ', (dob) => {
                    const employee = new Employee(name, email, age, dob);
                    //storing all data to a variable and appending it to the file
                    const employeeData = `${employee.name},${employee.email},${employee.age},${employee.dob}\n`;
                    fs.appendFile('employees.txt', employeeData, (err) => {
                        if (err) throw err;
                        //message for succesfull insertion
                        console.log(`\n${employee.name} added to employees.txt`);
                        choice();
                    });
                });
            });
        });
    });
}
// Delete employee
function deleteEmployeeFromFile() {
    read.question("Enter employee ID: ", (id) => {
        const employees = readDataFromFile();
        // filtering the email which is not equal to required email
        const filteredEmployees = employees.filter((employee) => {
            return employee.email !== id;
        });
        //if both are not equal it means email not found
        if (filteredEmployees.length < employees.length) {
            writeDataToFile(filteredEmployees);
            console.log("Employee deleted successfully");
        } else {
            console.log("Employee not found");
        }
        choice();
    });
}

// Search employee
function searchEmployeeFromFile() {
    read.question('\nEnter the text to search: ', (query) => {
        read.question('Order by (name, email, age, dob): ', (sortBy) => {
            read.question('Sort direction (asc, desc): ', (sortDirection) => {
                const employees = readDataFromFile();
                //filtering those employees which have the query 
                const results = employees.filter((employees) => {
                    return Object.keys(employees).some((val) => employees[val] === query);
                }).sort((first, second) => {
                    let sortOrder = sortDirection === 'asc' ? 1 : -1;
                    if (first[sortBy] < second[sortBy]) {  //sorting them accordingly 
                        return -1 * sortOrder;
                    } else if (first[sortBy] > first[sortBy]) {
                        return 1 * sortOrder;
                    } else {
                        return 0;
                    }
                });
                console.log(results); //result printing
                choice();
            });
        });
    });
}


// Dashboard 
async function choice() {
    console.log('\nPlease choose an option:');
    console.log('1. Add employee to file');
    console.log('2. Delete employee from the file');
    console.log('3. Search employee from the file');
    console.log('4. Display all the data of employee');
    console.log('5. Exit');
    read.question('\nEnter your choice: ', (option) => {
        switch (option) {
            case '1':
                addEmployeeInfoToFile();
                break;
            case '2':
                deleteEmployeeFromFile();
                break;
            case '3':
                searchEmployeeFromFile();
                break;
            case '4':
                displayShowAll();
                break;
            case '5':
                read.close();
                process.exit(1);
                break;
            default:
                console.log('\nInvalid option. Kindly choose from the given options');
                break;
        }
    });
}

choice();