const fs = require("fs");
// const { argv } = require("process");

// Struden class
class Student{
    /**
     *
    /**
     * A parameterised constructor for studen class.
     * Which takes name, dob as ab string and an object of address class.
     * @param {String} name 
     * @param {object} dob 
     * @param {object} address 
     */
    constructor (name, dob,address){
        this.name = name;
        this.dob=  dob;
        this.address = address;
    };
}

// Address class
class Address{
    /**
     * A parameterised constructor which takes city, state, pin Code and country name as an argument. 
     * @param {String} city 
     * @param {String} state 
     * @param {number} pinCode 
     * @param {String} country 
     */
    constructor(city, state, pinCode, country){
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
        this.country = country;
    };
}

if(require.main===module){
    const StudentsDetails = [
         new Student("vishal", new Date("2001-03-28").toLocaleDateString().split(" ")[0], new Address("Indore", "Madhya pradesh", "452010", "India")),
         new Student("Sanskar", new Date("2002-04-08").toLocaleDateString().split(" ")[0], new Address("Indore", "Madhya pradesh", "452010", "India")),
         new Student("Varun", new Date("2003-05-17").toLocaleDateString().split(" ")[0], new Address("Indore", "Madhya pradesh", "452010", "India")),
         new Student("Vicky", new Date("2004-01-18").toLocaleDateString().split(" ")[0], new Address("Indore", "Madhya pradesh", "452010", "India")),
         new Student("Samyukth", new Date("2001-06-29").toLocaleDateString().split(" ")[0], new Address("Indore", "Madhya pradesh", "452010", "India")),
         new Student("Shivam", new Date("2000-09-20").toLocaleDateString().split(" ")[0], new Address("Indore", "Madhya pradesh", "452010", "India"))
    ]
    // try{
    //     const fileName = argv[2];
    // }catch(err){
    //     console.log("err");
    //     return;
    // }
    const fileName = process.argv[2];
    if(!fileName){
        console.log("Please provide file name with argument.");
        return;
    }
    fs.writeFileSync(fileName, JSON.stringify(StudentsDetails));
    console.log("Serilization done.");
}