const prompt = require('prompt-sync')();
const fs     = require('fs'); 

// Creating employee class.
class Employee{
    // Creating a parameterised constructor for employee class.
    constructor (id,name, email,age,dob ){
        this.name = name;
        this.email = email;
        this.age = age;
        this.dob = dob;
        this.id = id;
    }
}


// This function is used for validation an email id.
function validateEamil(email){
    const reg = "(\.?[a-z0-9]){1,}@[a-u]+\.[a-z]+"
    const regex = new RegExp(reg);

    return regex.test(email)
}

// This method is used for creating new users.
function createNewUser(id){
    var name  = prompt('Enter your name: ');
    var email = prompt('Enter your email id: ');
    // validating the email. The loop will continue until it will get correct email.
    while(true){
        if(validateEamil(email)){// The method will return a boolean value.
            break;
        }
        console.log("Invalid email try again..\n")
        var email = prompt('Enter your email id: ');
    };
    var age   = prompt('Enter your age: ');
    try{
        var dob   = new Date(prompt('Enter your dob yyyy-mm-dd: ')).toLocaleDateString().split(" ")[0];
    }catch(err){
        console.log("Invalid date format. Try again.");
        return;
    }
    const content = `${id+1}, ${name},${email},${age},${dob}`;
    fs.appendFileSync("employees.txt", content);
}


// This function is used for deleting data from object and update the file.
function deleteEmployee(employee) {
    const id = parseInt(prompt("Enter the user id: "));
    const filteredData = employee.filter((emp)=>{
        return parseInt(emp.id) !== id;
    });
    var content = "";
    filteredData.map((data)=>{
        content += `${data.id},${data.name},${data.email},${data.age},${data.dob}\n`
    })
    fs.writeFileSync("employees.txt", content);
    return filteredData;
}

// This function is used for searching the employee. It receives an arry object as an argument.
function searchEmployeeFromFile(employees) {
    const query = prompt('Enter the text to search: ');
    const sortBy = prompt('Order by (name, email, age, dob): ');
    const sortDirection = ('Sort direction (asc, desc): ');
    //filtering those employees which have the query 
    const results = employees.filter((employees) => {
        return Object.keys(employees).some((val) => employees[val] === query);
    }).sort((first, second) => {
        let sortOrder = sortDirection === 'asc' ? 1 : -1;
        if (first[sortBy] < second[sortBy]) {  //sorting them accordingly 
            return -1 * sortOrder;
        } else if (first[sortBy] > first[sortBy]) {
            return 1 * sortOrder;
        } else {
            return 0;
        }
    });
    console.log(results); //result printing
}




// Main function in js.
if(require.main===module){
    const data = fs.readFileSync("employees.txt", 'utf-8');
    const array = data.split("\n");
    var allEmployees = [];
    for(line of array){
        [id,names,email,age,dob] = line.split(",");
        const temp = new Employee(id,names,email,age,dob);
        allEmployees.push(temp);
    }
    
    // menu options for users.
    while(true){
        console.log("Choose options from the menu.");
        console.log("1. Create a new employee.");
        console.log("2. Delete an employee.");
        console.log("3. Search an employee.");
        console.log("4. Display all employees.")
        console.log("5 Exit");
        let input;
        try{
            input = parseInt(prompt("Enter you choice: "));
        }catch(err){
            console.log("Invalid input please choose a number between 1 to 5.");
            continue ;
        }

        switch(input){
            case 1:
                const id = parseInt(data.split('\n').slice(-1)[0].split(",")[0]);
                console.log(id);
                createNewUser(id)
                break;
            
            case 2:
                allEmployees = deleteEmployee(allEmployees);
                break;

            case 3:
                searchEmployeeFromFile(allEmployees);
                break;
            case 4:
                console.log(allEmployees);
                break;
            case 5:
                process.exit([0])
            default:
                console.log("Inavlid choice.");
        }
    }
}