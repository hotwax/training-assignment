// Import required modules
const fs = require('fs'); 
const readline = require('readline') 

// Define the Employee class
class Employee {
  constructor(name, email, age, dob) {

    // Validate the input parameters
    if (!/^[a-zA-Z ]+$/.test(name)) {
        throw new Error('Name should only contain letters.');
      }
      if (!/\S+@\S+\.\S+/.test(email)) {
        throw new Error('Email is not valid.');
      }
      if (!Number.isInteger(age) ||age < 18 || age > 65) {
        throw new Error('Age should be and between 18 and 65.');
      }
      if (!isValidDOB(dob)) {
        throw new Error('Invalid date of birth.');
      }  

    // Set the instance variables
    this.name = name;
    this.email = email;
    this.age = age;
    this.dob = dob;
  }
}

// readline interface to read user input
const read = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

// Add an employee to the file
function addEmployee(employee) {
  const employeeData = `${employee.name},${employee.email},${employee.age},${employee.dob}`;

  fs.appendFileSync('employees.txt', `\n${employeeData}`, (err) => {
    if (err) throw err;
    console.log(`\n${employee.name} added successfully to employee.txt.`);
  });
}

// Delete an employee with the given email address
function deleteEmployee(email) {
  if (!/\S+@\S+\.\S+/.test(email)) {
    console.log('Email is not valid.');
    return;
  }
  const employees = loadEmployeesFromFile('employees.txt');
  const index = employees.findIndex((employee) => employee.email === email);
  if (index !== -1) {
    employees.splice(index, 1);
    writeEmployeesToFile(employees);
    console.log('Employee deleted successfully.');
  } else {
    console.log('Employee not found.');
  }
}


// Search for employees with the given query string
function searchEmployees(query, orderBy, direction) {
  
  // Validate the orderBy and direction parameters
  let validOrderBy = ['name', 'email', 'age', 'dob'];
  if (!validOrderBy.includes(orderBy)) {
    throw new Error('Invalid orderBy.');
  }

  let validDirection = ['asc', 'desc'];
  if (!validDirection.includes(direction)) {
    throw new Error('Invalid direction.');
  }

  const employees= loadEmployeesFromFile();
  let results = employees.filter((employee) =>
    JSON.stringify(employee).toLowerCase().includes(query.toLowerCase())
  );
  if (orderBy) {
    results = results.sort((a, b) => {
      const aProp = a[orderBy];
      const bProp = b[orderBy];
      if (typeof aProp === 'string') {
        return direction === 'asc'
          ? aProp.localeCompare(bProp)
          : bProp.localeCompare(aProp);
      } else {
        return direction === 'asc' ? aProp - bProp : bProp - aProp;
      }
    });
  }
  return results;
}

// Display all employees
function loadEmployeesFromFile(){
  let employees = [];

  try{
  const data = fs.readFileSync('employees.txt', 'utf8');
  employees = data.split('\n').map((line) => {
    const [name, email, age, dob] = line.split(',');
    return new Employee(name, email, parseInt(age), dob);
  });
  return employees;}
  catch(err){
    if (err.code === 'ENOENT') {
      console.log('File not found.');
      return [];
    }
    else{
    console.log("Error in reading file", err);
    return [];}
  }
}
    
// Write employees to the file
function writeEmployeesToFile(employees) {
  try{
  const data = employees
    .map((employee) => {
      const { name, email, age, dob } = employee;
      
      return `${name},${email},${age},${dob}`;
    })
    .join('\n');
  fs.writeFileSync('employees.txt', data);}
  catch(err){

    console.log("Error in writing file", err);
  }
}

// Validate the date of birth
function isValidDOB(dob){

  if (!/^\d{4}-\d{2}-\d{2}$/.test(dob)) {
  console.log('\nDate of birth in the format YYYY-MM-DD.');
  return false;
}

const [year, month, day] = dob.split('-').map(Number);

if (isNaN(year) || isNaN(month) || isNaN(day)) {
  console.log('\nDate of birth should contain only numbers.');
  return false;
}

if (month < 1 || month > 12) {
  console.log('\nMonth should be between 1 and 12.');
  return false;
}

if (day < 1 || day > 31) {
  console.log('\nDay should be between 1 and 31.');
  return false;
}

return true;

}      
function displayEmployees() {

  console.log('Employees:');
  console.table(loadEmployeesFromFile());
}
  
function employeeMenu(){
// Define the main program
loadEmployeesFromFile();

read.setPrompt('Menu:\n1. Add employee\n2. Delete employee\n3. Search employees\n4. Display \n5.Exit\nEnter your choice:');
read.prompt();

read.on('line', (choice) => {
  switch (choice.trim()) {
    case '1':
      read.question('Enter name:', (name) => {
        read.question('Enter email:', (email) => {
          read.question('Enter age:', (age) => {
            age = parseInt(age);
            read.question('Enter date of birth (YYYY-MM-DD):', (dobStr) => {
              // const dob = new Date(dobStr);
              const employee = new Employee(name, email, age, dobStr);
              addEmployee(employee);
              console.log('Employee added successfully.');
              read.prompt();
            });
          });
        });
      });
      break;

      case '2':
        read.question('Enter employee email to delete:', (emailToDelete) => {
          deleteEmployee(emailToDelete);
          read.prompt();
        });
        break;

    case '3':
      read.question('Enter search query:', (query) => {
        read.question('Enter sort order (name,age,email,dob):', (orderBy) => {
          read.question('Enter sort direction (asc/desc):', (direction) => {
            const results = searchEmployees(query, orderBy, direction);
            console.table(results);
            read.prompt();
          });
        });
      });
      break;

    case '5':
      console.log('Exiting program.');
      read.close();
      break;

      case '4':
        displayEmployees();
        read.prompt();
        break;

    default:
      console.log('Invalid choice.');
      read.prompt();
      break;
  }
}); 
}
employeeMenu();