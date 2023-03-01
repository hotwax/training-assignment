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
function readEmployeesFromFile() {
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
        console.error("Error reading file:", err);
    }
    return employees;
}
//function to display all over data we have in the file
function ShowAll() {
    return new Promise(() => {
        console.log(readEmployeesFromFile());
        askOption();
    }
    );
}

// Write employees to file
function writeEmployeesToFile(employees) {
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

function addEmployeefile() {
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
                        askOption();
                    });
                });
            });
        });
    });
}
// Delete employee
function deleteEmployeefile() {
    read.question("Enter employee ID: ", (id) => {
        const employees = readEmployeesFromFile();
        // filtering the email which is not equal to requried email
        const filteredEmployees = employees.filter((employee) => {
            return employee.email !== id;
        });
        //if both are not equal it means email not found
        if (filteredEmployees.length < employees.length) {
            writeEmployeesToFile(filteredEmployees);
            console.log("Employee deleted successfully");
        } else {
            console.log("Employee not found");
        }
        askOption();
    });
}

// Search employee
function searchEmployee() {
    read.question('\nEnter the text to search: ', (query) => {
        read.question('Order by (name, email, age, dob): ', (sortBy) => {
            read.question('Sort direction (asc, desc): ', (sortDir) => {
                const employees = readEmployeesFromFile();
                //filtering those employees which have the query 
                const results = employees.filter((employees) => {
                    return Object.keys(employees).some((val) => employees[val] === query);
                }).sort((a, b) => {
                    let sortOrder = sortDir === 'asc' ? 1 : -1;
                    if (a[sortBy] < b[sortBy]) {  //sorting them accordingly 
                        return -1 * sortOrder;
                    } else if (a[sortBy] > b[sortBy]) {
                        return 1 * sortOrder;
                    } else {
                        return 0;
                    }
                });
                console.log(results); //result printing
                askOption();
            });
        });
    });
}

async function askOption() {
    console.log('\nPlease choose an option:');
    console.log('1. Add employee');
    console.log('2. Delete employee');
    console.log('3. Search employee');
    console.log('4. ShowAll employee');
    console.log('5. Exit');
    read.question('\nEnter your choice: ', (option) => {
        switch (option) {
            case '1':
                addEmployeefile();
                break;
            case '2':
                deleteEmployeefile();
                break;
            case '3':
                searchEmployee();
                break;
            case '4':
                ShowAll();
                break;
            case '5':
                read.close();
                process.exit(1);
                break;
            default:
                console.log('\nInvalid option. Please try again.');
                break;
        }
    });
}
askOption();