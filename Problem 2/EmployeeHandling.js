import fs from 'fs';
import readline from 'readline';

// Creating readline interface to read input from console
const reader = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

class Employee {
  constructor(name, email, age, dob) {
    this.name = name;
    this.email = email;
    this.age = age;
    this.dob = dob;
  }
  convertToString() {
    return `${this.name},${this.email},${this.age},${this.dob}`;
  }
}

// Function to check if the age is valid
const checkAge = () => {
  // Returning a promise to test the age
  return new Promise((resolve) => {
    // Reading the age from console
    reader.question('Enter age: ', (age) => {
      // Checking if the age is valid
      // If age is invalid, calling the function again
      if (!age || isNaN(age)) {
        console.log('Invalid age');
        resolve(checkAge());
      } else if (age < 0 || age > 100) {
        console.log('Invalid age');
        resolve(checkAge());
      }
      // If age is valid, resolving the promise
      resolve(age);
    });
  });
};
const checkName = () => {
  // Returning a promise to test the name
  return new Promise((resolve) => {
    // Reading the name from console
    reader.question('\nEnter the employee name: ', (name) => {
      // Checking if the name is valid
      // If name is invalid, calling the function again
      if (!name) {
        console.log('Invalid Name');
        resolve(checkName);
      }
      // If name is valid, resolving the promise
      resolve(name);
    });
  });
};
const checkEmail = () => {
  // Returning a promise to test the email
  return new Promise((resolve) => {
    // Reading the email from console
    reader.question('Enter email: ', (email) => {
      // Regex for email validation
      const regex =
        /^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$/;
      // Checking if the email is valid
      // If email is invalid, calling the function again
      if (!email || (email && !regex.test(email))) {
        console.log('Invalid email');
        resolve(checkEmail());
      }
      // If email is valid, resolving the promise
      resolve(email);
    });
  });
};
const checkDob = () => {
  // Returning a promise to test the dob
  return new Promise((resolve) => {
    reader.question('Enter dob: ', (dob) => {
      // Regex for date validation
      const regex =
        /^([0-9][0-9][0-9][0-9])-(0[0-9]||1[0-2])-([0-2][0-9]||3[0-1])$/;
      // Checking if the dob is valid
      // If dob is invalid, calling the function again
      if (!dob || (dob && !regex.test(dob))) {
        console.log('Invalid date');
        resolve(checkDob());
      }
      // If dob is valid, resolving the promise
      resolve(dob);
    });
  });
};

const createEmployee = () => {
  // Returning a promise to create the employee
  return new Promise((resolve) => {
    // Calling the functions to check the name, email, age and dob
    checkName().then((name) => {
      checkEmail().then((email) => {
        checkAge().then((age) => {
          checkDob().then((dob) => {
            // Creating the employee object
            const employee = new Employee(name, email, parseInt(age), dob);
            // Resolving the promise
            resolve(employee);
          });
        });
      });
    });
  });
};

//Assuming the employees.txt file is in the same directory as this file
// Otherwise use
const PATH = process.cwd() + '\\employees.txt';

let employees = [];
const getEmployeesFromFile = () => {
  // Reading the file and storing the employees in the employees array
  try {
    fs.readFileSync(PATH, 'utf8')
      .trim()
      .split('\n')
      .forEach((line) => {
        const [name, email, age, date] = line.trim().split(',');
        const employee = new Employee(
          name,
          email,
          parseInt(age),
          new Date(date).toLocaleDateString()
        );
        employees.push(employee);
      });
    choice();
  } catch (err) {
    console.log('File not found');
    choice();
  }
};

const showAllEmployees = () => {
  // Showing all the employees
  console.log('\n All Employees: \n-------------------------------');
  // Showing the employees in a table format
  console.table(employees);
  // Calling the choice function to ask the user for the next choice
  choice();
};
const addEmployee = () => {
  // Calling the createEmployee function to create the employee
  createEmployee().then((employee) => {
    // Adding the employee to the employees array
    console.log('Employee added successfully');
    // Writing the employee to the file
    fs.appendFileSync(PATH, employee.convertToString() + '\n');
    // Calling the choice function to ask the user for the next choice
    choice();
  });
};
const deleteEmployee = () => {
  // Reading the email and name of the employee to delete
  reader.question('Enter email of employee to delete: ', (email) => {
    // Finding the index of the employee to delete
    const index = employees.findIndex((employee) => employee.email == email);
    // If the employee is found, deleting the employee
    if (index !== -1) {
      employees.splice(index, 1);
      fs.writeFileSync(PATH, '');
      employees.forEach((employee) => {
        fs.appendFileSync(PATH, employee.convertToString() + '\n');
      });
      console.log('Employee Deleted Sucessfully!');
    } else {
      console.log('Employee not found');
    }
    // Calling the choice function to ask the user for the next choice
    choice();
  });
};
const searchEmployee = () => {
  // Reading the query and the field to search
  reader.question('\nEnter the text to search: ', (query) => {
    reader.question('Order by (name, email, age, dob): ', (sortBy) => {
      reader.question('Sort direction (asc, desc): ', (direction) => {
        //filtering those employees which have the query
        const results = employees
          .filter((employees) => {
            return Object.keys(employees).some(
              (val) => employees[val] == query
            );
          })
          .sort((a, b) => {
            let order = direction == 'asc' ? 1 : -1;
            if (a[sortBy] < b[sortBy]) {
              return -1 * order;
            } else if (a[sortBy] > a[sortBy]) {
              return 1 * order;
            } else {
              return 0;
            }
          });
        // Showing the results in a table format
        console.table(results);
        // Calling the choice function to ask the user for the next choice
        choice();
      });
    });
  });
};
function choice() {
  console.log('------------ MENU ------------');
  console.log('Press 1 : Add employee to file');
  console.log('Press 2 : Delete employee from the file');
  console.log('Press 3 : Search employee from the file');
  console.log('Press 4 : Display all the data of employee');
  console.log('Press 5 : Exit');
  reader.question('\nEnter your choice: ', (choice) => {
    switch (choice) {
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
        showAllEmployees();
        break;
      case '5':
        reader.close();
        process.exit(1);
      default:
        console.log('\nInvalid Choice :(');
        choice();
        break;
    }
  });
}
getEmployeesFromFile();
