const { match } = require("assert");
const fileHandler = require("fs");
const read = require("readline").createInterface({
  input: process.stdin,
  output: process.stdout,
});
const Employee = require("./Employee");  //importing Employee class 


const EMPLOYEE_FILE = "employees.txt"
var employeesData = [];  // an array of objects of employees 
/* [ { name: 'payal', age: '20', email: 'payalJain@gmail.com', dateOfBirth: '2002-02-02' },
  { name: 'nidhi', age: '20', email: 'nidhiPal@gmail.com', dateOfBirth: '2001-01-02'} ] */


// check whether the email already present in the Array 
function whetherEmailExists(email) {
  return employeesData.filter((emp) => emp.email == email).length == 1;
}


// check validation for email- syntax and existence in file
function enterEmail() {

  return new Promise((resolve) => {
    read.question("\nEnter email id of the employee: ", (email) => {
      if (!/\S+@\S+\.\S+/.test(email)) { // invalid email
        console.log("Please enter a valid email.");
        resolve(enterEmail())
      }
      if (whetherEmailExists(email)) {  // email already exists
        console.log("Email already exists. Please enter a different email.");
        resolve(enterEmail()) // rerun enterEmail() function
      }
      resolve(email)  // valid email, pass email to addAnEmployee() function
    });
  });


}

function enterName() {

  return new Promise((resolve) => {
    read.question("\nEnter name of the employee: ", (name) => {
      if(name.length==0){
      console.log("\nName is empty. Please enter a valid name.");
      resolve(enterName()) // rerun enterName() function
      }
      if (!new RegExp('^[A-z ]+$').test(name)) { // invalid age
        console.log("Please enter a valid name.");
        resolve(enterName()) // rerun enterName() function
      }
      resolve(name) // pass name to addAnEmployee() function 
    });
  });


}

// check validation for age- number
function enterAge() {

  return new Promise((resolve) => {
    read.question("\nEnter age of the employee: ", (age) => {
      if (!new RegExp('^[0-9]+$').test(age)) { // invalid age
        console.log("Please enter a valid age.");
        resolve(enterAge()) // rerun enterAge() function
      }
      resolve(age) //valid age, pass age to addAnEmployee() function
    });
  });


}

// check validation for email- yyyy-mm-dd format
function enterDateOfBirth() {

  return new Promise((resolve) => {
    read.question("\nEnter date of birth of the employee yyyy-mm-dd: ", (dateOfBirth) => { //invalid dateOfBirth
      if (!new RegExp('^([1-9][0-9][0-9][0-9])-(0[1-9]||1[0-2])-([0-2][1-9]||([1-2]0)||3[0-1])$').test(dateOfBirth)) {
        console.log("Please enter a valid date.");
        resolve(enterDateOfBirth()) // rerun enterDateOfBirth() function
      }
      resolve(dateOfBirth) // valid dateOfBirth, pass dateOfBirth to addAnEmployee() function
    });
  });


}

// add employee's data to 'employeesData' array and append the same to 'employees.txt' file
function addAnEmployee() {
  enterEmail().then((email) => {
    enterName().then((name) => {
      enterAge().then((age) => {
        enterDateOfBirth().then((dateOfBirth) => {
          const employee = new Employee(name, age, email, dateOfBirth)  // creating an employee object
          employeesData.push(employee); // adding obj to 'employeesData' array 
          fileHandler.appendFileSync(EMPLOYEE_FILE, employee.toString() + "\n") 
          console.log("\nEmployee's data added successfully.\n------------------------\n");
          enterChoice()
        })
      })
    })
  })

}


//  remove an employee's data from 'employeesData' array and then rewrite the changed 'employeesData' to file
function deleteAnEmployee() {
  read.question("Enter email of the employee: ", (email) => {

    if (whetherEmailExists(email)) { // email exists
      employeesData = employeesData.filter((employee) => employee.email != email) // returns all employees except one with entered email 
      rewriteEmployeesInFile() // now rewrite file with the filtered 'employeesData' array
      console.log("\nEmployee's data deleted successfully.\n------------------------\n");
      enterChoice()
    }
    else { // email doesn't exists
      console.log("This email doesn't exists. Please enter a different email.\n");
      deleteAnEmployee() // until correct email id is entered
    }
  });

}

function rewriteEmployeesInFile() {
  let employeesDataString = ""
  employeesData.forEach((employee) => { // making a combined string of all employees to write it to file 
    employeesDataString = employeesDataString.concat(employee.toString() + "\n")
  })
  fileHandler.writeFileSync(EMPLOYEE_FILE, employeesDataString);
}


// show all employee's data by looping through 'employeesData' array
function showAllEmployees() {
  console.log("\nEmployee's data:");
  if(employeesData.length==0){ // no employees added yet
    console.log("No data exists\n");
  } 
  else{
    employeesData.forEach((employee) => {  
      console.log(employee.toString());
    })
    console.log("------------------------\n");
  }
  enterChoice()
  
}


// search and sort employee's data 
function searchAndSortEmployees() {

  // enter any value(name, age, email, date of birth) of any existing employee 
  read.question("Enter employee's data (name or age or email or date of birth): ", (query) => {

    let matchedData= [];  // will store matched employees according to the entered query
    console.log();
    matchedData = employeesData.filter((employee) => {
      return Object.keys(employee).some((val) => employee[val] == query);
    }) // search the query in 'employeesData' array
    
    if (matchedData.length==0){
      console.log("No employee exists."); // No match found
      console.log("------------------------\n");
      enterChoice()
    } 

    enterSortBy().then((sortBY) => {
      enterOrder().then((order) => {
  
        console.log("Employee's data:");
        matchedData.sort((e1, e2) => {
          // for eg- if sortBy is 'name' then e1Field and e2Field will be two employee's name values
          let e1Field = e1[sortBY];  
          let e2Field = e2[sortBY];
  
          let i = (order == "ascending" ? 1 : -1); // 1 for ascending order and -1 for descending order(inversion)
          if (e1Field < e2Field) return -1 * i;
          else if (e1Field > e2Field) return 1 * i;
          else return 0;
        }).forEach((employee) => {
          console.log(employee.toString());  // print the resulted data
        })
  
        console.log("------------------------\n");
        enterChoice()
      })
    })
  });
}


// sort employees' data on the basis of entered field.
function enterSortBy() {

  return new Promise((resolve) => {

    // we will be not taking email because no emails can have the same value and it doesn't make sense to sort on the basis of emails.
    read.question('Enter the field to sort by (name or age or dateOfBirth): ', (sortBY) => {
      if (sortBY != "name" && sortBY != "age" && sortBY != "dateOfBirth") {  // invalid sortBy
        console.log("Please enter a valid field.\n");
        resolve(enterSortBy())  // rerun enterSortBy() function until a valid field is entered
      }
      resolve(sortBY) // valid sortBy, pass it to sortEmployees() function 
    });
  });


}

function enterOrder() {

  return new Promise((resolve) => {
    read.question('\nEnter the order of sorting (ascending or descending): ', (order) => {
      if (order != "ascending" && order != "descending") {  // invalid order
        console.log("Please enter a valid order.");
        resolve(enterOrder())  // rerun enterOrder() function until a valid order is entered
      }
      resolve(order) // valid order, pass it to sortEmployees() function 
    });
  });
}

function sortEmployees() {

  
}


function enterChoice() {
  
  console.log('Please choose an option:');
  console.log('1. Add an employee');
  console.log('2. Delete an employee');
  console.log("3. Show all employee's data");
  console.log('4. Search and sort employees');
  console.log('5. Exit the program');
  read.question('\nEnter your choice: ', (choice) => {
    switch (choice) {
      case '1':
        addAnEmployee();
        break;
      case '2':
        deleteAnEmployee();
        break;
      case '3':
        showAllEmployees();
        break;
      case '4':
        searchAndSortEmployees();
        break;
      case '5':
        read.close();
        process.exit(1);
      default:
        console.log('Please enter a valid input.\n----------------------\n');
        enterChoice()
    }
  });
}


// if already "employee.txt" file exists, then fetch its data first
function fetchEmployeesData() {
  if (fileHandler.existsSync(EMPLOYEE_FILE)) {
    try {

      // "employee.txt" file stores employees in this format-
      // name: nidhi, age: 20, email: nidhi@gmail.com, date of birth: 2002-01-01

      // so accordingly splitting is done-  '\n'  ->  ', '  ->  ': '

      fileHandler.readFileSync(EMPLOYEE_FILE, "utf8").split('\n').forEach((employee) => {
        const [name, age, email, dateOfBirth] = employee.split(", ");
        employeesData.push(new Employee(name.split(": ")[1], age.split(": ")[1], email.split(": ")[1], dateOfBirth.split(": ")[1].replace('\r', "")));
      });

    }

    // If "employee.txt" is empty then applying forEach on it will cause an error
    // or while splitting data of the file with '\n', it may take the last line in "employee.txt" also and then again splitting it with ', ' and ': ' will cause an error. 

    // But there is nothing needs to be done about these errors, so we left catch block empty.
    catch (err) { }

  }

  enterChoice()
}


fetchEmployeesData()

