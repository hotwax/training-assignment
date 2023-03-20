// This is a class named "Account" that has a constructor and a method named "withdraw".
class Account {
  // The constructor takes a default parameter "balance" which is set to 1000 if no value is provided.
  constructor(balance = 1000) {
    this.balance = balance;
  }
  // The "withdraw" method takes two parameters: "name" and "amount".
  withdraw(name, amount) {
    // If the "balance" is greater than or equal to the "amount" provided, it subtracts the "amount" from the "balance", logs the withdrawal details, and updates the current balance. Otherwise,
    // it logs a message indicating that there are insufficient funds.
    if (this.balance >= amount) {
      this.balance = this.balance - amount;
      console.log();
      console.log("Amount  Withdraw by : " + name);
      console.log("Withdraw Amount is : " + amount);
      console.log("Current Bank Balance is : " + this.balance);
    } else {
      console.log("");
      console.log("Sorry Insufficient Balance");
    }
  }
}

// The code creates an object of the "Account" class named "accountholder"
let accountholder = new Account();

//it calls the "withdraw" method five times with a withdrawal amount of 100 and the account holder's name as "Pushpraj".
for (let i = 0; i < 5; i++) {
  accountholder.withdraw("Pushpraj", 100);
}

// Similarly, it calls the "withdraw" method five times with a withdrawal amount of 100 and the account holder's name as "Lakshyaraj".

for (let i = 0; i < 5; i++) {
  accountholder.withdraw("Lakshyaraj", 100);
}
