const fs = require("fs");
const readline = require("readline").createInterface({
  input: process.stdin,
  output: process.stdout,
});

// Employee class
class Employee {
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
      const writer = fs.createWriteStream("Employee.txt");
      for (const employee of employeeData) {
        writer.write(
          `${employee.id}, ${employee.name}, ${employee.age}, ${employee.email}, ${employee.dob}\n`
        );
      }
      writer.close();
    } catch (error) {
      console.log("Not able to write in the file.", error);
    }
  }

  // Method to check the id is already present in the Array or not
  checkIdInData(id) {
    const employee = employeeData.find((employee) => employee.id == id);
    if (employee == undefined) {
      return false;
    } else {
      return true;
    }
  }

  // Method to add the employee data in the Array
  addEmployee() {
    console.log();
    readline.question("Enter the id of the employee: ", (id) => {
      readline.question("Enter the name of the employee: ", (name) => {
        readline.question("Enter the age of the employee: ", (age) => {
          readline.question("Enter the email of the employee: ", (email) => {
            readline.question(
              "Enter the date of birth of the employee: ",
              (dob) => {
                console.log();
                if (this.checkIdInData(id)) {
                  console.log("Employee data already exists.");
                } else {
                  const employee = new Employee(id, name, age, email, dob);
                  employeeData.push(employee);
                  this.writeInFile();
                  console.log("Employee data added successfully.");
                }
                console.log();
                this.main();
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
      if (!this.checkIdInData(emp_id)) {
        console.log("Employee data does not exist.");
      } else {
        for (const employee of employeeData) {
          if (employee.id == emp_id) {
            const index = employeeData.indexOf(employee);
            employeeData.splice(index, 1);
            this.writeInFile();
            console.log("Employee data deleted successfully.");
          }
        }
      }
      console.log();
      this.main();
    });
  }

  // Method to show all the employee data from the Array
  showAllEmployee() {
    () => {
      for (const employee of this.employeeData) {
        console.log("");
        console.log(`Id: ${employee.id}`);
        console.log(`Name: ${employee.name}`);
        console.log(`Age: ${employee.age}`);
        console.log(`Email: ${employee.email}`);
        console.log(`Date of birth: ${employee.dob}`);
      }
    };
    then(() => {
      console.log();
      this.main();
    });
  }

  // Method to search the employee data from the Array
  searchEmployeeData() {
    console.log();
    readline.question("Enter the id of the employee: ", (id) => {
      const employee = employeeData.find((emp) => emp.id == id);
      console.log();
      if (employee == undefined) {
        console.log("Employee data does not exist.");
      } else {
        console.log(`Id: ${employee.id}`);
        console.log(`Name: ${employee.name}`);
        console.log(`Age: ${employee.age}`);
        console.log(`Email: ${employee.email}`);
        console.log(`Date of birth: ${employee.dob}`);
        console.log("");
      }
      console.log();
      this.main();
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
  lines.forEach((line) => {
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





