// created a Student class 
export class Student
{
//initialised constructor wih firstName, dateOfBirth,city,state,pinCode,country
 constructor(firstName, dateOfBirth,city,state,pinCode,country){
    this.firstName = firstName;
    this.dateOfBirth = dateOfBirth;
    this.address = new Address(city,state,pinCode,country);
}
}

//Created an Address class 
class Address{
 constructor(city, state, pinCode,country)
 {
    this.city = city;
    this.state = state;
    this.pinCode = pinCode;
    this.country = country;
 }
}