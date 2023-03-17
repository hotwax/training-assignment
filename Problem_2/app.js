// Importing necessary modules

var readline = require('readline');
const fs = require('fs');


// Creating readline interface
var rl = readline.createInterface(
     process.stdin, process.stdout);
// creating Employee class

class Employee {
    constructor (id,name, email,age,dob) 
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.dob = dob;
    }


    // Function to show all employee data
    showalldata()
    {
        
        if(employeedata.length==0)
        {
            console.log("Empty file");
            this.main();
            
        }else{
            employeedata.forEach(item=>{
                
                if(item.id!=undefined)
                {
                    console.log("ID :  " + item.id,"  Name : " + item.name,"     Email : " + item.email,"   Age :  " + item.age,"    DOB : " + item.dob);
                }
            });

            this.main();
        }
        
    }
    // Function to write employee data to file
    writedatatofile()
    {
        let tempdata= "";
        employeedata.forEach(item=>{
            tempdata =  tempdata+ item.id + ", "+ item.name + ", "+item.email+", "+item.age+", "+item.dob+"\n"; 
        })
        //tempdata = tempdata + empdata.id + ", "+ empdata.name + ", "+empdata.email + ", "+ empdata.age+ ", "+ empdata.dob+ "\n";
        
        try {
            fs.writeFileSync('./employeedata.txt', tempdata);
            console.log();
            console.log("Operation Performed Successfully");
            console.log();
            this.main();
          } catch (err) {
            console.error("Unable to write data in a file");
            this.main();
          }
    }
    // Function to delete employee data
    addEmployee()
    {
        rl.question("Enter Name : ",(name)=>
        {

            rl.question("Enter Email : ",(email)=>
            {
                rl.question("Enter Age : ",(age)=>
                {
                    rl.question("Enter date of birth : ",(DOB)=>
                    {
                        if(/^[a-zA-Z ]*$/.test(name))
                        {
                            if(/^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$/.test(email))
                            {
                                if(age>=0 && age<120)
                                {
                                    if(/^\d{2}\/\d{2}\/\d{4}$/.test(DOB))
                                    {
                                        const empdata = new Employee(idx,name,email,age,DOB);
                                        
                                        employeedata.push(empdata);
                                        idx++;
                                        this.writedatatofile(empdata);
                                        
                                    }else
                                    {
                                        console.log("Enteres Date of birth is Incorrect");
                                    }
                                }else
                                {
                                    console.log("Entered Age is Incorrect")
                                }
                            }else
                            {
                                console.log("Entered Email is Incorrect");
                            }

                        }else
                        {
                            console.log("Entered Name is Incorrect");
                        }
                    })
                })
            
            })
            
        })
        


    }

    deleteEmployee()
    {
        rl.question("Enter Employee ID to delete the data : ",(deleteid)=>{
            
            employeedata.forEach(item=>{
                if(item.id==deleteid)
                {
                    employeedata =  employeedata.filter((i)=> i.id!= deleteid);
                    this.writedatatofile();
                }
            })
        })
        
    }


    // Function to search employee data
    searchEmployeeData()
    {
        console.log();
        rl.question("Text to search :", (tosearch)=>
        {
            console.log();
            console.log("Sort the data by :");
            console.log();
            rl.question("Type : id or name or email or age or dob: ", (sortby)=>{
                console.log();
                console.log("Order the data by Ascending or descending ")
                console.log();
                rl.question("Type ASC or DESC : ", (orderby)=>{
                    const filtersearchdata = employeedata.filter((item)=>{
                        let temp = "";
                        temp = temp + item.id + " " + item.name+ " "+ item.email+ " "+ item.age+ " "+ item.dob ;
                        
                        return (temp.includes(tosearch));
                    })
                    const sorteddata = filtersearchdata.sort((a,b)=>{
                        if(orderby.toLowerCase()=="asc")
                        {
                            return a[sortby]> b[sortby] ? 1 :-1;
                        }else
                        {
                            return a[sortby]<b[sortby] ? 1 : -1;
                        }
                    })

                    if(sorteddata.length!=0)
                    {
                        console.log();
                        console.log("Data Found");
                        console.log();
                        console.log(sorteddata);
                        this.main();
                    }
                    else
                    {
                        console.log();
                        console.log("No result found");
                        this.main();
                    }
                })
            })
        })
    }



    
    // The main function displays the menu to the user and uses a switch statement to call the appropriate function based on the user's choice.
    main()
{
    try {
        console.log("Choose an option: ");
        console.log("1. Add a new employee data.");
        console.log("2. Delete an employee data.");
        console.log("3. Search an employee data.");
        console.log("4. Show all employee data.");
        console.log("5. Exit");
        rl.question("Enter your choice: ", (choice) => {
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
              this.showalldata();
              break;
            case "5":
                console.log();
                console.log("Exiting .....");
                console.log();
                rl.close();
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


// The employee data is stored in an array called "employeedata" and is read from a file called "employeedata.txt". Each line in the file represents an employee and contains the following fields: id, name, email, age, and dob.

let employeedata = [];
let idx =0;
let employee = new Employee();

try {
    const data = fs.readFileSync('./employeedata.txt', 'utf8');
    let splitdata = data.split('\n');
    splitdata.forEach(item=>{
        
        let spliting = item.split(",");
        let idd = spliting[0];
        let namee = spliting[1];
        let emaill = spliting[2];
        let agee = spliting[3];
        let dobb = spliting[4];
        if(idd!=undefined && namee!=undefined && emaill!=undefined && agee!=undefined && dobb!=undefined)
        {
            const emp = new Employee(idx,namee,emaill,agee,dobb);
            employeedata.push(emp);
            idx++;
        }
        
        
    })
     employee.main();
  } catch (err) {
    console.error(err);
  }


