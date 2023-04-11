//create the address class
class Address{
    constructor(city,state,pinCode,country){
        this.city=city;
        this.state=state;
        this.pinCode=pinCode;
        this.country=country;
    }
}

//create the students class
export class Student{
  constructor(firstName,dateOfBirth,city,state,pinCode,country){
    this.firstName=firstName;
    this.dateOfBirth=new Date(dateOfBirth);
    this.address=new Address(city,state,pinCode,country);//create new instance of address class 
  }
}