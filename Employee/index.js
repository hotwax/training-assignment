// Importing modules
const fs = require("fs"); // For file system operations
const readline = require("readline").createInterface({ 
  input: process.stdin, 
  output: process.stdout, 
});

// Employee class
class Employee {
  // Constructor to initialize the class variables
  constructor(id, name, age, email, dob) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.email = email;
    this.dob = dob;
  }

  // Method to write the data in the file which is stored in the Array
  writeInFile() {
    try {
      const writer = fs.createWriteStream("Employee.txt"); // Creating a write stream
      employeeData.map((employee) => { // Looping through each employee in the array
        // Writing the employee data to the file
        writer.write(
          `${employee.id}, ${employee.name}, ${employee.age}, ${employee.email}, ${employee.dob}\n`
        );
      });
      writer.close(); // Closing the write stream
    } catch (error) {
      console.log("Not able to write in the file.", error);
    }
  }

  // Method to check the id is already present in the Array or not
  checkIdInData(id) {
    const employee = employeeData.find((employee) => employee.id == id); // Finding an employee with the given id
    if (employee == undefined) { // If employee not found
      return false;
    } else {
      return true;
    }
  }

  // Method to add the employee data in the Array
  addEmployee() {
    console.log();
    readline.question("Enter the id of the employee: ", (id) => {
      // check id is a number or not using regex
      if (!/^[0-9]+$/.test(id)) {
        console.log();
        console.log("Id should be a number.");
        console.log();
        this.main();
        return;
      }
      readline.question("Enter the name of the employee: ", (name) => {
        if (!/^[a-zA-Z]+$/.test(name)) {
          console.log();
          console.log("Name should be a string.");
          console.log();
          this.main();
          return;
        }
        readline.question("Enter the age of the employee: ", (age) => {
          if (!/^[0-9]+$/.test(age)) {
            console.log();
            console.log("Age should be a number.");
            console.log();
            this.main();
            return;
          }
          readline.question("Enter the email of the employee: ", (email) => {
            if (!/^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[A-Za-z]+$/.test(email)) {
              console.log();
              console.log("Email should be in proper format.");
              console.log();
              this.main();
              return;
            }
            readline.question(
              "Enter the date of birth of the employee: ",
              (dob) => {
                if (!/^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/.test(dob)) {
                  console.log();
                  console.log("Date of birth should be in proper format.");
                  console.log();
                  this.main();
                  return;
                }
                console.log();
                if (this.checkIdInData(id)) { // Checking if the given id is already present in the array
                  console.log("Employee id already exists.");
                } else {
                  const employee = new Employee(id, name, age, email, dob); // Creating a new employee object
                  employeeData.push(employee); // Adding the employee to the array
                  this.writeInFile(); // Writing the updated data to the file
                  console.log("Employee data added successfully.");
                }
                console.log();
                this.main(); // Returning to the main menu
              }
            );
          });
        });
      });
    });
  }

  // Method to delete the employee data from the Array
  deleteEmployee(id) {
    console.log();
    readline.question("Enter the id of the employee: ", (emp_id) => {
      console.log();
      if (!this.checkIdInData(emp_id)) { // Checking if the given id is present in the array
        console.log("Employee data does not exist.");
      } else {
        employee = employeeData.filter((employee) => employee.id == emp_id); // Filtering the employee with the given id
        console.log(employee);
        const index = employeeData.indexOf(employee); // Finding the index of the employee
        employeeData.splice(index, 1); // Removing the employee from the array
        this.writeInFile(); // Writing the updated data to the file
        console.log("Employee data deleted successfully.");
      }
      console.log();
      this.main(); // Returning to the main menu
    });
  }


  // Method to show all the employee data from the Array
  showAllEmployee() {
    if (employeeData.length == 0) {
      console.log("No employee data is present.");
    } else {
      console.table(employeeData);
    }
    console.log();
    this.main();
  }

  searchEmployeeData() {
    readline.question("Enter search query: ", (query) => {
      readline.question("Sort by (name, email, age, dob): ", (sortBy) => {
        readline.question("Sort order (asc, desc): ", (sortOrder) => {
          const filteredEmployees = employeeData.filter((employee) => {
            return (
              employee.name.includes(query) ||
              employee.email.includes(query) ||
              employee.age.includes(query) ||
              employee.dob.includes(query)
            );
          });
          const sortedEmployees = filteredEmployees.sort((a, b) => {
            if (sortOrder === "asc") {
              return a[sortBy] > b[sortBy] ? 1 : -1;
            } else {
              return a[sortBy] < b[sortBy] ? 1 : -1;
            }
          });
          console.log(`Found ${sortedEmployees.length} employees:`);
          console.table(sortedEmployees);
          this.main();
        });
      });
    });
  }


  main() {
    try {
      console.log("Choose an option: ");
      console.log("1. Add a new employee data.");
      console.log("2. Delete an employee data.");
      console.log("3. Search an employee data.");
      console.log("4. Show all employee data.");
      console.log("5. Exit");
      console.log("Enter your choice: ");
      readline.question("", (choice) => {
        switch (choice) {
          case "1":
            this.addEmployee();
            break;
          case "2":
            this.deleteEmployee();
            break;
          case "3":
            this.searchEmployeeData();
            break;
          case "4":
            this.showAllEmployee();
            break;
          case "5":
            break;
          default:
            console.log("Wrong input please choose number between 1 to 5.");
            this.main();
        }
      });
    } catch (e) {
      console.log("Some error occurred.", e);
    }
  }
}

var employeeData = [];
var employee = new Employee();

try {
  const data = fs.readFileSync("Employee.txt", "utf8");
  const lines = data.split("\n");
  lines.map((line) => {
    if (line != "") {
      const str = line.split(", ");
      const emp = new Employee(str[0], str[1], str[2], str[3], str[4]);
      employeeData.push(emp);
    }
  });
  employee.main();
} catch (err) {
  console.log("Not able to read the file. Exception occurred: " + err);
  employee.main();
}
