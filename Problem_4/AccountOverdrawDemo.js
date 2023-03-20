// This is a class named "Account" that has a constructor and an asynchronous method named "withdraw".

class Account {
 // The constructor takes a default parameter "balance" which is set to 1000 if no value is provided.
  constructor(balance = 1000) {
    this.balance = balance;
  }
  // The "withdraw" method takes two parameters: "name" and "amount".
  async withdraw(name, amount) {
    // If the "balance" is greater than or equal to the "amount" provided, it subtracts the "amount" from the "balance", logs the withdrawal details, and updates the current balance
    if (this.balance >= amount) {
      this.balance = this.balance - amount;
      console.log();
      console.log("Amount  Withdraw by : " + name);
      console.log("Withdraw Amount is : " + amount);
      console.log("Current Bank Balance is : " + this.balance);
    } else {
        //Otherwise, it logs a message indicating that there are insufficient funds.
      console.log("Amount  Withdraw by : " + name);
      console.log("Withdraw Amount is : " + 0);
      console.log("Current Bank Balance is : " + this.balance);
      console.log(" Insufficient balance");
    }
  }
}

// The code creates an object of the "Account" class named "accountholder".
let accountholder = new Account();

// It defines two asynchronous functions named "person1" and "person2" which each contain a for loop that calls the "withdraw" method on the "accountholder" object six times with a withdrawal amount of 100 and a name of either "Pushpraj" or "Lakshyaraj".
let person1 = async () => {
  for (let i = 0; i < 6; i++) {
    await accountholder.withdraw("Pushpraj", 100);
  }
};

let person2 = async () => {
  for (let i = 0; i < 6; i++) {
    await accountholder.withdraw("Lakshyaraj", 100);
  }
};
// The functions are called with "person1()" and "person2()" at the end of the code.
person1();
person2();
