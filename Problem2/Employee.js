const files = require("fs");
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
function readEmployee() {
    let employees = [];
    try {
        const data = files.readFileSync(FILENAME, "utf8");
        // spliting data with newLine character and removing space
        const lines = data.trim().split("\n");
        for (let line of lines) {
            const [name, email, age, dob] = line.trim().split(",");
            const employee = new Employee(name, email, parseInt(age), new Date(dob).toLocaleDateString().split(' ')[0]);
            employees.push(employee);
        }
    } catch (error) {
        console.error("Error reading file:", error);
    }
    return employees;
}
//function to display all over data we have in the file
function ShowAll() {
    return new Promise(() => {
        console.table(readEmployee());
        enterChoice();
    }
    );
}

// Write employees to file
function writeEmployee(employees) {
    try {
        const lines = employees.map((employee) => {
            return `${employee.name},${employee.email},${employee.age},${employee.dob}`;
        });
        const data = lines.join("\n"); // adding newLine character to data and inserting in file
        files.writeFileSync(FILENAME, data, { flag: "w" });
    } catch (error) {
        console.error("Error writing file:", error);
    }
}

function takeName() {
    return new Promise((resolve) => {
        read.question('\nEnter the employee name: ', (name) => {
            if (!name) {
                console.log('Invalid input. Please enter a name.');
                resolve(takeName());
            }
            resolve(name);
        });
    });
}

function takeEmail() {
    return new Promise((resolve) => {
        read.question('Enter the email address: ', (email) => {
            if (!email || !/\S+@\S+\.\S+/.test(email)) {
                console.log('Invalid input. Please enter a valid email address.');
                resolve(takeEmail());
            }
            resolve(email);
        });
    });
}

function takeAge() {
    return new Promise((resolve) => {
        read.question('Enter the employee age: ', (age) => {
            if (!age || isNaN(age)) {
                console.log('Invalid input. Please enter a valid age.');
                resolve(takeAge());
            }
            resolve(age);
        });
    });
}

function takeDOB() {
    return new Promise((resolve) => {
        read.question('Enter the date of birth (YYYY-MM-DD): ', (dob) => {
            if (!dob || !/^([0-9][0-9][0-9][0-9])-(0[0-9]||1[0-2])-([0-2][0-9]||3[0-1])$/.test(dob)) {
                console.log('Invalid input. Please enter a valid date of birth (YYYY-MM-DD).');
                resolve(takeDOB());
            }
            resolve(dob);
        });
    });
}

function addEmployee() {
    takeName().then((name) => {
        takeEmail().then((email) => {
            takeAge().then((age) => {
                takeDOB().then((dob) => {
                    const employee = new Employee(name, email, age, dob);
                    //storing all data to a variable and appending it to the file
                    const employeeData = `${employee.name},${employee.email},${employee.age},${employee.dob}\n`;
                    files.appendFile('employees.txt', employeeData, (error) => {
                        if (error) console.log("somthing went wrong");
                        //message for succesfull insertion
                        console.log(`\n${employee.name} added to employees.txt`);
                        enterChoice();
                    });
                });
            });
        });
    });
}

// Delete employee
function deleteEmployee() {
    read.question("Enter employee ID: ", (id) => {
        const employees = readEmployee();
        // filtering the email which is not equal to requried email
        const filteredEmployees = employees.filter((employee) => {
            return employee.email !== id;
        });
        //if both are not equal it means email not found
        if (filteredEmployees.length < employees.length) {
            writeEmployee(filteredEmployees);
            console.log("Employee deleted successfully");
        } else {
            console.log("Employee not found");
        }
        enterChoice();
    });
}

// Search employee
function searchEmployee() {
    read.question('\nEnter the text to search: ', (query) => {
        read.question('Order by (name, email, age, dob): ', (sortBy) => {
            read.question('Sort direction (asc, desc): ', (sortDirection) => {
                const employees = readEmployee();
                const results = employees.filter((employee) => {
                    return Object.keys(employee).some((key) => employee[key] == query);
                }).sort((first, second) => {
                    let sortOrder = sortDirection === 'asc' ? 1 : -1;
                    if (first[sortBy] < second[sortBy]) {
                        return -1 * sortOrder;
                    } else if (first[sortBy] > second[sortBy]) {
                        return 1 * sortOrder;
                    } else {
                        return 0;
                    }
                });
                console.table(results);
                enterChoice();
            });
        });
    });
}


async function enterChoice() {
    console.log('\nPlease choose an option:');
    console.log('1. Add employee');
    console.log('2. Delete employee');
    console.log('3. Search employee');
    console.log('4. ShowAll employee');
    console.log('5. Exit');
    read.question('\nEnter your choice: ', (option) => {
        switch (option) {
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
                ShowAll();
                break;
            case '5':
                read.close();
                console.log("Thank you");
                process.exit(1);
                break;
            default:
                console.log('\nInvalid choice.');
                enterChoice();
                break;
        }
    });
}
enterChoice();