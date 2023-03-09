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

// thread-1
async function withRam() {
  while (account.balance > 0) {
    await account.withdraw("Ram", 100);
  }
}

// thread-2
async function withShyam() {
  while (account.balance > 0) {
    await account.withdraw("Shyam", 100);
  }
}

// thread-1 and thread-2 working simultaniously

withRam()
withShyam()
