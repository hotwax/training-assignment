const fs = require("fs");

class Address {
  constructor(city, state, pincode, country) {
    this.city = city;
    this.state = state;
    this.pincode = pincode;
    this.country = country;
  }
}

class Student {
  constructor(name, dob, address) {
    this.name = name;
    this.dob = dob;
    this.address = address;
  }
}

class Serialize {
  // This function creates a student object and returns it.
  createStudentObject(name, dob, city, state, pincode, country) {
    const address = new Address(city, state, pincode, country);
    const student = new Student(name, dob, address);
    return student;
  }

  serializeStudentObject() {
    const student1 = this.createStudentObject(
      "Ram",
      "12/09/1998",
      "Delhi",
      "Delhi",
      "110001",
      "India"
    );
    const student2 = this.createStudentObject(
      "Shyam",
      "24/12/1970",
      "Indore",
      "Madhya Pradesh",
      "110001",
      "India"
    );
    const student3 = this.createStudentObject(
      "Adarsht",
      "20/06/2001",
      "Mumbai",
      "Maharashtra",
      "110001",
      "India"
    );
    const student4 = this.createStudentObject(
      "Hardik",
      "07/05/2004",
      "Pilani",
      "Goa",
      "110001",
      "India"
    );

    const students = [student1, student2, student3, student4];

    try {
      // Write the students array to a file named "output1.ser".
      fs.writeFileSync(
        "output1.ser",
        JSON.stringify(students)
      );
      console.log("Serialized data is saved in .ser file in the folder.");
    } catch (err) {
      console.error(err);
    }
  }
}


class Deserialize {
  deserializeStudentObject() {
    try {
      // Read the students array from the file "output1.ser".
      const data = fs.readFileSync("output1.ser");
      const students = JSON.parse(data);
      console.table(students);
    } catch (err) {
      console.error(err);
    }
  }
}


const serialize = new Serialize();
serialize.serializeStudentObject();
const deserialize = new Deserialize();
deserialize.deserializeStudentObject();
