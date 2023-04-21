  
// export class Student {
//   constructor(firstName, dateOfBirth, address) {
//     this.firstName = firstName;
//     this.dateOfBirth = dateOfBirth;
//     this.address = address;
//   }
// }

  export class Student {
    constructor(firstName, dateOfBirth, address) {
      this.firstName = firstName;
      this.dateOfBirth = new Date(dateOfBirth);
      this.address = address;
    }
  }
  