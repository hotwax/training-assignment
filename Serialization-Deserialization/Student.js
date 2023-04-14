export class Address {
    constructor(city, state, pinCode, country) {
      this.city = city;
      this.state = state;
      this.pinCode = pinCode;
      this.country = country;
    }
  }
  
export class Student {
  constructor(firstName, dateOfBirth, address) {
    this.firstName = firstName;
    this.dateOfBirth = dateOfBirth;
    this.address = address;
  }
}

  // export class Student {
  //   constructor(firstName, dateOfBirth, address) {
  //     this.firstName = firstName;
  //     this.dateOfBirth = new Date(dateOfBirth);
  //     this.address = address;
  //   }
  // }
  