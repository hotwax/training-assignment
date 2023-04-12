// Importing necessary modules
import fs from "fs"; // for file system operations
import PromptSync from "prompt-sync"; // for synchronous user input

// A Map to store all the existing employees' data and a Set to store their email addresses
const prevEmployeesData = new Map();
const prevEmployeeEmail = new Set();

// Employee class for creating new employee objects
class Employee {
  constructor(empId, empName, empAge, empEmail, empDob) {
    this.employeeId = empId;
    this.employeeName = empName;
    this.employeeAge = empAge;
    this.employeeEmail = empEmail;
    this.employeeDob = empDob;
  }
}

// Function to read all existing employee data from file and populate the Map and Set
const getAllEmployee = () => {
  try {
    // Read data from file
    const data = fs.readFileSync("ProblemStatement-2/employeeData.txt", "utf-8");
    // Split the data by newline
    const employeesData = data.split("\n");
    // Loop through each line of employee data
    for (const employeeData of employeesData) {
      // Split the data by comma
      if (employeeData.trim().length !== 0) {
        const employeed = employeeData.split(",");
        if (employeed[0] !== "") {
          // Create new Employee object from the data and add it to the Map and Set
          const employee = new Employee(parseInt(employeed[0]), employeed[1], parseInt(employeed[2]), employeed[3], employeed[4]);
          prevEmployeesData.set(parseInt(employeed[0]), employee);
          prevEmployeeEmail.add(employeed[3]);
        }
      }
    }
  } catch (error) {
    console.log(error.message);
  }
};

//check the date is valid or not
const isValidDate = (dateOfBirth) => {
  // First check for the pattern
  if (!/^\d{1,2}\/\d{1,2}\/\d{4}$/.test(dateOfBirth)) return false;

  // Parse the date parts to integers
  let parts = dateOfBirth.split("/");
  let day = parseInt(parts[1], 10);
  let month = parseInt(parts[0], 10);
  let year = parseInt(parts[2], 10);
  const currentDate = new Date();

  // Check the ranges of month and year
  if (
    year <= currentDate.getFullYear() - 100 ||
    year > currentDate.getFullYear() - 18 ||
    month == 0 ||
    month > 12
  )
    return false;

  let monthLength = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

  // Adjust for leap years
  if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
    monthLength[1] = 29;

  // Check the range of the day
  return day > 0 && day <= monthLength[month - 1];
};

// Function to add a new employee data to the file and the Map and Set
const addEmployeeData = () => {
  const prompt = PromptSync();
  // Prompt the user to enter employee details and validate the input using regular expressions
  let empId = prompt("Enter the Employee id(only digits) -> ");

  //validate the employee Id
  const regexForId = /^[0-9]*$/;
  while (!regexForId.test(empId) || empId.trim().length == 0) {
    console.log("enter valid employee id\n");
    empId = prompt("Enter the Employee id(only digits) -> ");
  }

  //validate the employee Name
  let empName = prompt("Enter the Employee name -> ");
  const regexForName = /^[a-zA-Z]*$/;
  while (!regexForName.test(empName) || empName.trim().length == 0) {
    console.log("enter valid employee name\n");
    empName = prompt("Enter the Employee name -> ");
  }

  //validate the employee Email
  let empEmail = prompt("Enter the Employee email -> ");
  const regexForEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  while (!regexForEmail.test(empEmail) || empEmail.trim().length == 0) {
    console.log("enter valid employee email\n");
    empEmail = prompt("Enter the Employee email -> ");
  }

  //validate the employee Age
  let empAge = prompt("Enter the Employee age -> ");
  const regexForAge = /^[0-9]*$/;
  while (!regexForAge.test(empAge) || empAge.trim().length == 0) {
    console.log("enter valid employee age\n");
    empAge = prompt("Enter the Employee age -> ");
  }

  //check the Age is correct or not
  while (parseInt(empAge) < 18 || parseInt(empAge) >= 100) {
    console.log("enter valid employee age(18 < Age < 100)\n");
    empAge = prompt("Enter the Employee age -> ");
  }

  //validate the employee Dob
  let empDob = prompt("Enter the Employee Dob(MM/DD/YYYY) -> ");
  while (!isValidDate(empDob)) {
    console.log("enter valid employee Dob(MM/DD/YYYY");
    empDob = prompt("Enter the Employee Dob(MM/DD/YYYY) -> ");
  }

  //check the employee Id is already present in employees Data
  if (prevEmployeesData.has(parseInt(empId))) {
    console.log("employee Id is already present");
  }
  else if (prevEmployeeEmail.has(empEmail)) {//check the employee Email is already present in employees Data
    console.log("Employee Email is already present");
  } else {
    const employee = new Employee(parseInt(empId),empName,parseInt(empAge),empEmail,empDob);
    prevEmployeesData.set(parseInt(empId), employee);
    try {
      //append the employee data in to employeeData file
      fs.appendFileSync("ProblemStatement-2/EmployeeData.txt",employee.employeeId +"," +employee.employeeName +"," +employee.employeeAge +
       "," +employee.employeeEmail +"," +employee.employeeDob +"\n"
      );
      console.log('Employee Data is added');
    } catch (error) {
      console.log(error.message);
    }
  }
};

// show all the Employees Data
const showAllEmployeeData = () => {
  const employeesData = [];
  for (const employeeData of prevEmployeesData) {
    employeesData.push(employeeData[1]);
  }
  //show the employee data in to table
  console.table(employeesData);
};

// Delete the Employee data by Id
const deleteEmployeeById = () => {
  const prompt = PromptSync();

  //check the employee data is empty or not
  let deletionId = prompt("Enter the employee id -> ");

  //validate the employee id
  const regexForId = /^[0-9]*$/;
  while (!regexForId.test(deletionId) || deletionId.trim().length == 0) {
    console.log("enter valid employee id\n");
    deletionId = prompt("Enter the Employee id(only digits) -> ");
  }

  //delete the employee data from employee file
  const idInNumber = parseInt(deletionId);
  if (prevEmployeesData.has(idInNumber)) {
    const deletedEmployee = prevEmployeesData.get(idInNumber);
    prevEmployeesData.delete(idInNumber);
    prevEmployeeEmail.delete(deletedEmployee.employeeEmail);
    try {
      fs.writeFileSync("ProblemStatement-2/EmployeeData.txt", "");
      console.log("employee data is deleted");
    } catch (error) {
      console.log(error.message);
    }
    for (const empData of prevEmployeesData) {
      try {
        fs.appendFileSync("ProblemStatement-2/EmployeeData.txt",empData[1].employeeId +"," +empData[1].employeeName +
             "," + empData[1].employeeAge + "," + empData[1].employeeEmail + "," + empData[1].employeeDob + "\n"
        );
      } catch (error) {
        console.log(error.message);
      }
    }
  } else {
    console.log("employee Id is not present");
  }
};

//Search and sort the employee By text
const searchAndSortEmployee = (text, parameterForSort, orderForSort) => {
  const employeesData = [];

  //search the text in employee data
  for (const empData of prevEmployeesData) {
    const employee = `${empData[1].employeeId} ${empData[1].employeeName} ${empData[1].employeeAge} ${empData[1].employeeEmail} ${empData[1].employeeDob}`;
    if (employee.search(text) >= 0) {
      employeesData.push(empData[1]);
    }
  }
  let sortedEmployeeData;
  switch (parameterForSort) {

    //sort employees by employee Id
    case "1":
      sortedEmployeeData = employeesData.sort((firstValue, secondValue) => {
        const compFirst = firstValue.employeeId;
        const compSecond = secondValue.employeeId;
        if (orderForSort === "1") {//Ascending
          return compFirst - compSecond;
        } else {//Descending
          return compSecond - compFirst;
        }
      });
      break;
    
    // sort employees by employee name
    case "2":
      sortedEmployeeData = employeesData.sort((firstValue, secondValue) => {
        const compFirst = firstValue.employeeName;
        const compSecond = secondValue.employeeName;
        if (orderForSort === "1") {//Ascending
          return compFirst - compSecond;
        } else {//Descending
          return compSecond - compFirst;
        }
      });
      break;
    
    // sort employees by employee Age
    case "3":
      sortedEmployeeData = employeesData.sort((firstValue, secondValue) => {
        const compFirst = firstValue.employeeAge;
        const compSecond = secondValue.employeeAge;
        if (orderForSort === "1") {//Ascending
          return compFirst - compSecond;
        } else {//Descending
          return compSecond - compFirst;
        }
      });
      break;
    
    // sort employee by Dob
    case "4":
      sortedEmployeeData = employeesData.sort((firstValue, secondValue) => {
        const compFirst = new Date(firstValue.employeeDob);
        const compSecond = new Date(secondValue.employeeDob);
        if (orderForSort === "1") { //Ascending
          return compFirst - compSecond;
        } else { //Descending
          return compSecond - compFirst;
        }
      });
      break;
  }
  console.table(sortedEmployeeData);
};

const main = () => {
  
  //get all the existed employee data
  getAllEmployee();
  let choices;
  const prompt = PromptSync();//for user input
  do {
    console.log("\n");
    console.log("Menu Options");
    console.log("1.Add Employee");
    console.log("2.Delete Employee By Id");
    console.log("3.Show All Employee");
    console.log("4. Search and Sort Employee By Text");
    console.log("5.Exit");
    console.log("\n");
    choices = prompt("enter choice ");
    switch (choices) {
      case "1":
        
        //add the employee data in the file
        addEmployeeData();
        break;

      case "2":
        
        //check the employee is empty or not
        if (prevEmployeesData.size === 0) {
          console.log("No employee data is present");
          break;
        }

        //delete the employee by id
        deleteEmployeeById();
        break;

      case "3":
        if (prevEmployeesData.size === 0) {
          console.log("No employee data is present");
          break;
        }

        // show the all employees data 
        showAllEmployeeData();
        break;

      case "4":
        if (prevEmployeesData.size === 0) {
          console.log("No employee data is present");
          break;
        }
        let parameterForSort;
        let orderForSort;
        const text = prompt("Enter the text which you want search -> ");
        do {
          console.log();
          console.log("Enter any parameter (1, 2, 3, 4) for sorting ")
          console.log("1.Sort By Employee Id");
          console.log("2.Sort By Employee Name");
          console.log("3.Sort By Employee Age");
          console.log("4.Sort By Employee DOB");
          parameterForSort = prompt("Enter the Parameter ");
        } while (
          parameterForSort !== "1" &&
          parameterForSort !== "2" &&
          parameterForSort !== "3" &&
          parameterForSort !== "4"
        );
        do {
          console.log();
          console.log("Enter any Order (1, 2) for sorting")
          console.log("1.Ascending Order");
          console.log("2.Descending Order");
          orderForSort = prompt("Enter the Order ");
        } while (orderForSort !== "1" && orderForSort !== "2");

        //sort the employee data by text 
        searchAndSortEmployee(text, parameterForSort, orderForSort);
        break;

      case "5":
        console.log("Thank you");
        break;

      default:
        console.log("Invalid Choice");
        break;
    }
  } while (choices != 5);
};
//call main function
main();