class Account {
  constructor() {
    this.balance = 1000;
  }

  // Method to withdraw money from the account
  withdraw(name, amount) {
    this.balance -= amount;
    console.log(
      name + " withdraws " + amount + " and has " + this.balance + " left"
    );
  }
}






const account = new Account();
account.withdraw("John", 200); // John withdraws 200 and has 800 left
