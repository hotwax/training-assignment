const fileHandler = require("fs");
const read = require("readline").createInterface({
  input: process.stdin,
  output: process.stdout,
});
const Employee = require("./Employee");


const EMPLOYEE_FILE = "employees.txt"
var employeesData = [];


// check whether the email already present in the Array 
function whetherEmailExists(email) {
  return employeesData.filter((emp) => emp.email == email).length == 1;
}


// functions to add an employee's data
function enterEmail() {

  return new Promise((resolve) => {
    read.question("\nEnter email id of the employee: ", (email) => {
      if (!/\S+@\S+\.\S+/.test(email)) {
        console.log("Please enter a valid email.");
        resolve(enterEmail())
      }
      if (whetherEmailExists(email)) {
        console.log("Email already exists. Please enter a different email.");
        resolve(enterEmail())
      }
      resolve(email)
    });
  });


}

function enterName() {

  return new Promise((resolve) => {
    read.question("\nEnter name of the employee: ", (name) => {
      resolve(name)
    });
  });


}

function enterAge() {

  return new Promise((resolve) => {
    read.question("\nEnter age of the employee: ", (age) => {
      if (!new RegExp('^[0-9]+$').test(age)) {
        console.log("Please enter a valid age.");
        resolve(enterAge())
      }
      resolve(age)
    });
  });


}

function enterDateOfBirth() {

  return new Promise((resolve) => {
    read.question("\nEnter date of birth of the employee yyyy-mm-dd: ", (dateOfBirth) => {
      if (!new RegExp('^([1-9][0-9][0-9][0-9])-(0[1-9]||1[0-2])-([0-2][0-9]||3[0-1])$').test(dateOfBirth)) {
        console.log("Please enter a valid date.");
        resolve(enterDateOfBirth())
      }
      resolve(dateOfBirth)
    });
  });


}

function addAnEmployee() {
  enterEmail().then((email) => {
    enterName().then((name) => {
      enterAge().then((age) => {
        enterDateOfBirth().then((dateOfBirth) => {
          const employee = new Employee(name, age, email, dateOfBirth)
          employeesData.push(employee);
          fileHandler.appendFileSync(EMPLOYEE_FILE, employee.toString() + "\n")
          console.log("\nEmployee's data added successfully.\n------------------------\n");
          enterChoice()
        })
      })
    })
  })

}


// functions to remove an employee's data
function deleteAnEmployee() {
  read.question("Enter email of the employee: ", (email) => {

    if (whetherEmailExists(email)) {
      employeesData = employeesData.filter((employee) => employee.email != email)
      rewriteEmployeesInFile()
      console.log("\nEmployee's data deleted successfully.\n------------------------\n");
      enterChoice()
    }
    else {
      console.log("This email doesn't exists. Please enter a different email.\n");
      deleteAnEmployee()
    }
  });

}

function rewriteEmployeesInFile() {
  let employeesDataString = ""
  employeesData.forEach((employee) => {
    employeesDataString = employeesDataString.concat(employee.toString() + "\n")
  })
  fileHandler.writeFileSync(EMPLOYEE_FILE, employeesDataString);
}


// function to show all employee's data
function showAllEmployees() {
  console.log("\nEmployee's data:");
  if(employeesData.length==0){
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


// function to search for an employee's data 
function searchAnEmployee() {
  read.question("Enter employee's data (name or age or email or date of birth): ", (query) => {

    let searchedData= [];
    console.log();
    searchedData = employeesData.filter((employee) => {
      return Object.keys(employee).some((val) => employee[val] == query);
    })
    
    searchedData.forEach((employee) => console.log(employee.toString()))

    if (searchedData.length==0) console.log("No employee exists.");

    console.log("------------------------\n");
    enterChoice()
  });
}


// functions to sort employees' data 
function enterSortBy() {

  return new Promise((resolve) => {
    read.question('Enter the field to sort by (name or age or dateOfBirth): ', (sortBY) => {
      if (sortBY != "name" && sortBY != "age" && sortBY != "dateOfBirth") {
        console.log("Please enter a valid field.\n");
        resolve(enterSortBy())
      }
      resolve(sortBY)
    });
  });


}

function enterOrder() {

  return new Promise((resolve) => {
    read.question('\nEnter the order of sorting (ascending or descending): ', (order) => {
      if (order != "ascending" && order != "descending") {
        console.log("Please enter a valid order.");
        resolve(enterOrder())
      }
      resolve(order)
    });
  });
}

function sortEmployees() {

  enterSortBy().then((sortBY) => {
    enterOrder().then((order) => {
      console.log("Employee's data:");
      [...employeesData].sort((e1, e2) => {
        let e1Field = e1[sortBY];
        let e2Field = e2[sortBY];

        let i = (order == "ascending" ? 1 : -1);
        if (e1Field < e2Field) return -1 * i;
        else if (e1Field > e2Field) return 1 * i;
        else return 0;
      }).forEach((employee) => {
        console.log(employee.toString());
      })

      console.log("------------------------\n");
      enterChoice()
    })
  })
}


function enterChoice() {
  console.log('Please choose an option:');
  console.log('1. Add an employee');
  console.log('2. Delete an employee');
  console.log("3. Show all employee's data");
  console.log('4. Search for an employee');
  console.log("5. Sort the employees");
  console.log('6. Exit the program');
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
        searchAnEmployee();
        break;
      case '5':
        sortEmployees();
        break;
      case '6':
        read.close();
        process.exit(1);
      default:
        console.log('Please enter a valid input.\n----------------------\n');
        enterChoice()
    }
  });
}


// if already "employee.txt" file exists, then fetch its data 
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

