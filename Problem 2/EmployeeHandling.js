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
const createEmployee = () => {
  return new Promise((resolve) => {
    reader.question('Enter name: ', (name) => {
      reader.question('Enter email: ', (email) => {
        reader.question('Enter age: ', (age) => {
          reader.question('Enter dob: ', (dob) => {
            const employee = new Employee(name, email, age, dob);
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
};

const showAllEmployees = () => {
  console.log('\n All Employees: \n-------------------------------');
  console.table(employees);
  choice();
};
const addEmployee = () => {
  createEmployee().then((employee) => {
    employees.push(employee);
    fs.appendFileSync(PATH, employee.convertToString() + '\n');
  });
  choice();
};
const deleteEmployee = () => {
  reader.question('Enter email of employee to delete: ', (email) => {
    reader.question('Enter name of employee to delete: ', (name) => {
      const index = employees.findIndex(
        (employee) => employee.email === email && employee.name === name
      );
      if (index !== -1) {
        employees.splice(index, 1);
        fs.writeFileSync(PATH, '');
        employees.forEach((employee) => {
          fs.appendFileSync(PATH, employee.convertToString() + '\n');
        });
      } else {
        console.log('Employee not found');
      }
      choice();
    });
  });
};
const searchEmployee = () => {
  read.question('\nEnter the text to search: ', (query) => {
    read.question('Order by (name, email, age, dob): ', (sortBy) => {
      read.question('Sort direction (asc, desc): ', (sortDirection) => {
        //filtering those employees which have the query
        const results = employees
          .filter((employees) => {
            return Object.keys(employees).some(
              (val) => employees[val] === query
            );
          })
          .sort((first, second) => {
            let sortOrder = sortDirection === 'asc' ? 1 : -1;
            if (first[sortBy] < second[sortBy]) {
              return -1 * sortOrder;
            } else if (first[sortBy] > first[sortBy]) {
              return 1 * sortOrder;
            } else {
              return 0;
            }
          });
        console.log(results);
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
        read.close();
        process.exit(1);
      default:
        console.log('\nInvalid Choice :(');
        break;
    }
  });
}
getEmployeesFromFile();
