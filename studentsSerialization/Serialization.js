const Student = require("./Student")
const Address = require("./Address")
const fileHandler = require("fs");

const read = require("readline").createInterface({
  input: process.stdin,
  output: process.stdout,
});

const student1 = new Student("Nidhi", "1990-01-01", new Address("indore", "m.p.", 452001, "India"))
const student2 = new Student("Ankita", "1990-01-01", new Address("ujjain", "m.p.", 458110, "India"))
const student3 = new Student("Janvi", "1990-01-01", new Address("bhopal", "m.p.", 452201, "India"))
const student4 = new Student("Shradha", "1990-01-01", new Address("indore", "m.p.", 452701, "India"))

const students = [student1, student2, student3, student4]  // an array of students objects

class SerializationTest {
  performSerialization() {
    const studentsString = JSON.stringify(students) // object -> string to store students data  in some file
    read.question("Enter file name for serialization: ", (fileName) => {
      fileHandler.writeFileSync(fileName, studentsString) // write data in file 
      console.log("Data serialized successfully.\n----------------------\n");
      enterChoice()
    })
  }
}

class DeserializationTest {
  performDeserialization() {
    read.question("Enter file name for deserialization: ", (fileName) => {
      if (!fileHandler.existsSync(fileName)) {  // entered file doesn't exists
        console.log(fileName + " file doesn't exists\n");
      }
      else if (fileName.split(".")[1] == "ser" || fileName.split(".")[1] == "txt" || fileName.split(".")[1] == "json") {
        const data = fileHandler.readFileSync(fileName, "utf-8")
        JSON.parse(data).forEach((student) => console.log(student)) // string to object and then looping to print data
        console.log("Data deserialized successfully.\n----------------------\n");
         
      }
      else {
        console.log("Please enter a valid file name. (only '.ser', '.txt', '.json' files can be deserialized.)\n");
      }
      enterChoice()
    })


  }
}

function enterChoice() {
  console.log("Options: ");
  console.log("1. Serialization");
  console.log("2. Deserialization");
  console.log("3. Exit the prorgram");

  read.question("\nChoose: ", (choice) => {
    switch (choice) {
      case '1':
        new SerializationTest().performSerialization();
        break;

      case '2':
        new DeserializationTest().performDeserialization();
        break;

      case '3':
        read.close()
        process.exit(1)

      default:
        console.log("Please enter a valid input.\n");
        enterChoice()
        break;
    }
  })

}

enterChoice()