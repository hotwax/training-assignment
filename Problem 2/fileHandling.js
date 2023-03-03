import fs from 'fs';
import readline from 'readline';

// creating a read interface for reading input from console
const read = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
//
//class Employee with constructor for its initialization
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
  // getName = () => this.name;
  // getEmail = () => this.email;
  // getAge = () => this.age;
  // getDob = () => this.dob;
}

// Read all employees from file
const fileSync = () => {
  let employees = [];
  try {
    const data = fs.readFileSync('employees.txt', 'utf8');
    // spliting data with newLine character and removing space
    const lines = data.trim().split('\n');
    for (let line of lines) {
      const [name, email, age, dob] = line.trim().split(',');
      const employee = new Employee(
        name,
        email,
        parseInt(age),
        new Date(dob).toLocaleDateString().split(' ')[0]
      );
      employees.push(employee);
    }
  } catch (err) {
    console.error('Error in reading file:', err);
  }
  return employees;
};
//function to display all over data we have in the file
function showAllEmployee() {
  return new Promise(() => {
    const fileData = fileSync.bind(Employee)();
    console.log('\n All Employees: \n-------------------------------');
    //printing all the data
    // fileData.forEach((employee) => {
    //   console.log(employee.convertToString());
    // });
    console.table(fileData);
    console.log('-------------------------------\n');
    choice();
  });
}

// Write employees to file
function writeDataToFile(employees) {
  try {
    const lines = employees.map((employee) => {
      return `${employee.name},${employee.email},${employee.age},${employee.dob}`;
    });
    const data = lines.join('\n'); // adding newLine character to data and inserting in file
    fs.writeFileSync('employees.txt', data, {flag: 'w'});
  } catch (err) {
    console.error('Error writing file:', err);
  }
}
// Adding employees to file
const addEmployee = () => {
  read.question('\nEnter the employee name : ', (name) => {
    read.question('Enter the email address : ', (email) => {
      read.question('Enter the employee age : ', (age) => {
        read.question('Enter the date of birth (YYYY-MM-DD): ', (dob) => {
          const employee = new Employee(name, email, age, dob);
          console.log(employee);
          const employeeData = Object.values(employee).join(',') + '\n';
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
};
// Delete employee
const deleteEmployee = () => {
  read.question('Enter employee ID: ', (id) => {
    const employees = fileSync();
    // filtering the email which is not equal to required email
    const filteredEmployees = employees.filter((employee) => {
      return employee.email !== id;
    });
    //if both are not equal it means email not found
    if (filteredEmployees.length < employees.length) {
      writeDataToFile(filteredEmployees);
      console.log('Employee deleted successfully');
    } else {
      console.log('Employee not found');
    }
    choice();
  });
};

// Search employee
const searchEmployee = () => {
  read.question('\nEnter the text to search: ', (query) => {
    read.question('Order by (name, email, age, dob): ', (sortBy) => {
      read.question('Sort direction (asc, desc): ', (sortDirection) => {
        const employees = fileSync();
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

// Menu
function choice() {
  console.log('------------ MENU ------------');
  console.log('Press 1 : Add employee to file');
  console.log('Press 2 : Delete employee from the file');
  console.log('Press 3 : Search employee from the file');
  console.log('Press 4 : Display all the data of employee');
  console.log('Press 5 : Exit');
  read.question('\nEnter your choice: ', (choice) => {
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
        showAllEmployee();
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

choice();
