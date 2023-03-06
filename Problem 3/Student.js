export default class Student {
  constructor(name, dob, address) {
    this.name = name;
    this.dob = new Date(dob);
    this.address = address;
  }
}

// export default class Student {
//   constructor(name, dob, address) {
//     this.name = name;
//     this.dob = dob;
//     this.address = address;
//   }
// }
