const file = require('fs');
const prompt = require('prompt-sync')()

// to read file and write file from standard input/output devices.
const readline = require("readline").createInterface({
    input: process.stdin,
    output: process.stdout,
});
var employeeData = []; // Global List 

// Employee Class
class Employee {
    constructor(id, name, age, email, dob) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.dob = dob;
    }
    toString() {
        return `Name: ${this.name}, Email: ${this.email}, Age: ${this.age}, DOB: ${this.dob}`;
    }
}
// function to insert the data of the list into file
function writeInFile() {
    try {
        const writer = file.createWriteStream("EmployeeSearchingSorting/Employee.txt");
        for (const employee of employeeData) {
            if (employee.name != undefined && employee.age != undefined && employee.email != undefined) {
                writer.write(`${employee.id},${employee.name},${employee.age},${employee.email},${employee.dob}\n`);
            }
        }
        writer.close();
    }
    catch (error) {
        console.log("Not able to write in the file.", error);
    }
}
// function to insert the data of the file into list
function insertFileDataIntoList() {
    collectData = [];
    try {
        const fileData = file.readFileSync("EmployeeSearchingSorting/Employee.txt", "utf8");
        const lines = fileData.split("\n");
        lines.forEach((line) => {
            //   console.log(line);
            const recordList = line.split(",");
            const employee = new Employee(
                recordList[0],
                recordList[1],
                recordList[2],
                recordList[3],
                recordList[4]
            );
            collectData.push(employee);
        });
    } catch (err) {
        console.log("Not able to read the file or File Not Exists");
        console.log("Program Will Create new file");
    }
    return collectData;
}
function checkIdInData(id) {
    for (const employee of employeeData) {
        if (employee.id == id) 
            return true;
    }
    return false;
}
// check whether the email already present in the Array 
function whetherEmailExists(email) {
    return employeeData.filter((emp) => emp.email == email).length == 1;
}
// Add the employee in the list 
function addEmployeeInfoToFile() {
    console.log();
    readline.question("Enter the id of the employee: ", (id) => {
        while(!id) { // Id Field is empty 
            console.log("\nEmployee id Shouldn't be empty ");
            id = prompt("Enter Employee Id "); }

        while (isNaN(id)) {  // Id not numeric Value 
            console.log("\nEmployee id Should be a Numeric Value");
            id = prompt("Enter another Id ");
        }
        while (checkIdInData(id)) { // check if id already exist or not
            console.log("\nEmployee id", id, "Already Exists");
            id = prompt("Enter another Id ");
        }
        
        readline.question("Enter the name of the employee: ", (name) => {
            let nameRegex = /^[a-zA-Z ]+$/;
            while (!name.match(nameRegex)) 
            {
                console.log("\nEnter a valid name!");
                name = prompt("Enter the Name : ");
            }
            while(!name)
            {
                console.log("\nName field shouldn't be empty");
                name = prompt("Enter Name ");
            }
            readline.question("Enter the age of the employee: ", (age) => {
                while(!age) {
                    console.log("\nAge Shouldn't be empty ");
                    age = prompt("Enter age "); }
                while(isNaN(age))
                {
                    console.log("\nAge should be Numeric ");
                    age = prompt("Enter age again ");
                }
                readline.question("Enter the email of the employee: ", (email) => {
                    // Validation for email
                    while(!whetherEmailExists(email))
                    {
                        console.log("\nEmail Already Exists");
                        email = prompt("Enter Email Again ");
                    }
                    while (!email.match("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
                        console.log("\nInvalid Email Format , Sample Email : abc@gmail.com");
                        email = prompt("Enter Email Again ");
                    }
                   
                    readline.question("Enter the date of birth of the employee: ", (dob) => {
                        // Validation for dob
                        while (!dob.match("^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-2][0-9])?[0-9][0-9]$")) {
                            console.log("\nInvalid Date of Birth Format");
                            console.log("Sample Date of Birth : 01/01/2023 ");
                            dob = prompt("Enter Date of Birth again ");
                        }

                        const employee = new Employee(id, name, age, email, dob);
                        employeeData.push(employee);
                        console.log(name, "added successfully.");
                        console.log();
                        dashBoard();
                        writeInFile(); // insert data of list into file

                    });
                });
            });
        });
    });
}

// Delete the employee
function deleteEmployee() {
    console.log();
    readline.question("Enter the id of the employee: ", (emp_id) => {
        console.log();
        if (!checkIdInData(emp_id)) {  // check if id exist or not 
            console.log("\nEmployee data does not exist.");
        } else {
            for (const employee of employeeData) {
                if (employee.id == emp_id) {
                    const index = employeeData.indexOf(employee);
                    removed_employee = employeeData[index].name;
                    employeeData.splice(index, 1);
                    writeInFile();
                    console.log(removed_employee, "deleted successfully.");
                }
            }
        }
        console.log();
        dashBoard();
    });
}
// Search employee
function searchEmployeeFromFile() {

    readline.question('\nEnter the Value to Search: ', (query) => {
        let criteria = ["Id","id","Name","name","Email","email","DOB","dob","Dob","Age","age"];
        readline.question('Sort By : \nId \nName \nEmail\nDOB\nAge\n', (sortBy) => {
            while(!criteria.includes(sortBy))
            {
                console.log("\nInvalid Criteria To sort the Records");
                sortBy = prompt("Enter Sort By criteria Again\nId \nName \nEmail\nDOB\nAge\n"); 
            }
            readline.question('Sort Order:\nAscending\nDescending\n', (sortDirection) => {
                let order = ["desc", "asc" , "ascending" , "descending","Asc","Desc"];
                while(!order.includes(sortDirection)) {
                    console.log("\nInvalid Order Input");
                    sortDirection = prompt("Enter Sorted Order Again:\nAsc or Ascending\nDesc or Descending\n"); 
                }

                employeeData.filter((employeeData) => {
                    return Object.keys(employeeData).forEach(key => {
                        if (employeeData[key] === undefined) {
                            delete employeeData[key];
                        }
                    })
                });
                // Sort the employees
                const results = employeeData.filter((employeeData) => {
                    return Object.keys(employeeData).some((val) => employeeData[val].toLowerCase() === query.toLowerCase());
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
                console.log("============================================================================");
                if(results.length == 0)
                    console.log("\nEmployee Not Found");
                else
                    console.table(results);
                console.log("============================================================================");
                dashBoard();
            });
        });
    });
}
function displayShowAll() {  
    // function to display all the records of the file
    console.log("============================================================================");
    if(employeeData.length == 0)
    {
        console.log("There is No data in the File");
    }
    else
    {
        console.table(employeeData);
    }
    console.log("============================================================================");

    dashBoard();
}
function dashBoard() 
{
    // Options  
    console.log('\n    Main Menu  ');
    console.log('Please choose an option:');
    console.log('1. Add employee to file');
    console.log('2. Delete employee from the file');
    console.log('3. Search employee from the file');
    console.log('4. Display all the data of employee');
    console.log('5. Exit');

    readline.question('\nEnter your choice: ', (option) => { // take input using readline 
        switch (option) {
            case '1':
                addEmployeeInfoToFile();
                break;
            case '2':
                deleteEmployee();
                break;
            case '3':
                searchEmployeeFromFile();
                break;
            case '4':
                displayShowAll();
                break;
            case '5':
                console.log("EXIT");
                readline.close();
                process.exit(1);
            default:
                console.log('\nInvalid option. Kindly choose from the given options');
                dashBoard();
                break;
        }
    });
}

// ==================================================================================================================================

// Creating a main Function Which Call first When Program runs and Transfer all the data of File into a List 
function main() {
    employeeData = insertFileDataIntoList(); // Insert all the data of the file into List 
     
    employeeData.filter((employeeData) => {  // Filter the List if there is Any undefined value in List 
        return Object.keys(employeeData).forEach(key => {
            if (employeeData[key] === undefined) {
                delete employeeData[key];
            }
        })
    });
    dashBoard(); // Dashboard Where Main menu exist
}

// Main function of the Program
main();