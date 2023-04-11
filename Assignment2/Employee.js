// Importing the 'fs' module for file system operations and the 'prompt-sync' module for user input
const fs = require('fs');
const prompt = require('prompt-sync')();

// Employee class with name, email, age, and dob properties
class Employee {
  constructor(name, email, age, dob) {
    this.name = name;
    this.email = email;
    this.age = age;
    this.dob = dob;
  }
}

// Function to read employees from a file
function readEmployeesFromFile() {
  // Reading file content synchronously and converting it to a string
  const fileContent = fs.readFileSync('employees.txt', 'utf-8');
  // Removing carriage returns and splitting lines into an array
  const lines = fileContent.toString().replace(/\r/g, "").split("\n");
  // Mapping each line to an Employee object and returning an array of employees
  const employees = lines.map(line => {
    const [name, email, age, dob] = line.split(',');
    return new Employee(name, email, age, dob);
  });
  return employees;
}
function displayMenu() {
  // read the list of employees from a file
  const employees = readEmployeesFromFile();

  // display the number of employees and the menu options to the user
  console.log(`There are ${employees.length} employees in the system.`);
  console.log('1. Add Employee');
  console.log('2. Delete Employee');
  console.log('3. Search Employees');
  console.log('4. Exit');

  // prompt the user for their choice
  const choice = prompt('Enter your choice: ');

  // based on the user's choice, call the corresponding function or exit the program
  switch (choice) {
    case '1':
      addEmployee(employees);
      break;
    case '2':
      deleteEmployee(employees);
      break;
    case '3':
      searchEmployees(employees);
      break;
    case '4':
      console.log('Exiting...');
      break;
    default:
      console.log('Invalid choice.');
      // if the user enters an invalid choice, display the menu again
      displayMenu();
      break;
  }
}

// This function allows the user to add an employee to the system.
// It takes an array of employees as input and modifies it by adding the new employee.
function addEmployee(employees) {
  // Prompt the user to enter the employee's name and validate it using a regular expression.
  let name = prompt('Enter employee name: ');
  const nameRegex = /^[A-Za-z]+(?:\s+[A-Za-z]+)*$/;
  while(!nameRegex.test(name)) {
    name = prompt('Invalid name. ENTER AGAIN: ');
  }

  // Prompt the user to enter the employee's email and validate it using a regular expression.
  let email = prompt('Enter employee email: ');
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  while(!emailRegex.test(email)) {
    email = prompt('Invalid email. ENTER AGAIN: ');
  }

  // Prompt the user to enter the employee's age and validate it as a positive integer.
  let age = parseInt(prompt('Enter age:'));
  while(isNaN(age) || age < 0 || age > 120) {
    age = parseInt(prompt('Invalid age. ENTER AGAIN: '));
  }

  // Prompt the user to enter the employee's date of birth and validate it using a regular expression.
  let dob = prompt('Enter employee date of birth (DD/MM/YYYY): ');
  const dobRegex = /^(0[1-9]|[1-2][0-9]|3[0-1])\/(0[1-9]|1[0-2])\/\d{4}$/;
  while(!dobRegex.test(dob)) {
    dob = prompt('Invalid date of birth. ENTER AGAIN: ');
  }

  // Create a new employee object with the entered information.
  const employee = new Employee(name, email, age, dob);

  // Check if an employee with the same email already exists in the system.
  // If not, add the new employee to the array and save the updated employee list to a file.
  // Otherwise, display an error message.
  const index = employees.findIndex(emp => emp.email === email);
  if(index === -1) {
    employees.push(employee);
    saveEmployeesToFile(employees);
    console.log('Employee added successfully.');
  } else {
    console.log('Employee already present with same Email ID.');
  }
  // Display the main menu after adding the employee.
  displayMenu();
}

// This function allows the user to delete an employee from the system.
// It takes an array of employees as input and modifies it by deleting the specified employee.
function deleteEmployee(employees) {
  // Prompt the user to enter the email of the employee to be deleted.
  const email = prompt('Enter employee Email ID to delete: ');

  // Find the index of the employee with the specified email in the array.
  // If the employee is found, remove it from the array and save the updated employee list to a file.
  // Otherwise, display an error message.
  const index = employees.findIndex(emp => emp.email === email);
  if (index === -1) {
    console.log('Employee not found.');
  } else {
    employees.splice(index, 1);
    saveEmployeesToFile(employees);
    console.log('Employee deleted successfully.');
  }
  // Display the main menu after deleting the employee.
  displayMenu();
}


//function to save employees to employees.txt file
function saveEmployeesToFile(employees) {
    let fileContent = '';
    employees.forEach(employee => {
        fileContent += `${employee.name},${employee.email},${employee.age},${employee.dob}\n`;
    });
    fs.writeFileSync('employees.txt', fileContent);
}

// function to search employee and sort accordingly
function searchEmployees(employees) {
  // display search options
  console.log('1. Search by Name');
  console.log('2. Search by Email');
  console.log('3. Search by Age');
  console.log('4. Search by Date of Birth');
  console.log('5. Back to Menu');
  
  // get user choice
  const choice = prompt('Enter your choice: ');

  switch (choice) {
      case '1':
          // search by name
          const name = prompt('Enter name to search: ');
          const filteredByName = employees.filter(employee => employee.name.includes(name));
          sortAndPrintResults(filteredByName);
          break;
      case '2':
          // search by email
          const email = prompt('Enter email to search: ');
          const filteredByEmail = employees.filter(employee => employee.email.includes(email));
          sortAndPrintResults(filteredByEmail);
          break;
      case '3':
          // search by age
          const age = parseInt(prompt('Enter age to search: '));
          const filteredByAge = employees.filter(employee => employee.age === age);
          sortAndPrintResults(filteredByAge);
          break;
      case '4':
          // search by date of birth
          const dob = prompt('Enter date of birth (DD/MM/YYYY) to search: ');
          const filteredByDob = employees.filter(employee => employee.dob === dob);
          sortAndPrintResults(filteredByDob);
          break;
      case '5':
          // back to main menu
          displayMenu();
          break;
      default:
          // invalid choice, prompt again
          console.log('Invalid choice.');
          searchEmployees(employees);
          break;
  }
}

//function to sort and print employee search results
function sortAndPrintResults(employees) {
  if (employees.length === 0) {
      console.log('No employees found.');
  } else {
      console.log(`Showing ${employees.length} employee(s):`);
      employees.sort((a, b) => a.name.localeCompare(b.name)); // Sorts the employee array alphabetically by name.
      employees.forEach(employee => console.log(`Name: ${employee.name}, Email: ${employee.email}, Age: ${employee.age}, DOB: ${employee.dob}`)); // Prints the employee details in a formatted way.
  }
  searchEmployees(employees); // Calls the searchEmployees function with the same array of employees.
}
displayMenu(); // Calls the displayMenu function to start the program.


