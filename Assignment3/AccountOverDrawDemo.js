// A helper function to introduce a delay using Promises
const awaitTimeout = delay => new Promise(resolve => setTimeout(resolve, delay));

// A class to represent a Bank Account
class BankAccount {
  constructor(initialBalance, name1, name2) {
    this.balance = initialBalance;
    this.accountHolderName1 = name1;
    this.accountHolderName2 = name2; 
  }

  // An async function to withdraw money from the account
  async withdraw(amount, accountHolderName) {
    if (amount <= this.balance) {
      // Introduce a delay of 10ms to simulate network latency
      await awaitTimeout(10).then();

      // Deduct the withdrawn amount from the account balance
      this.balance -= amount;

      // Print a message to indicate the withdrawal and the remaining balance
      console.log(accountHolderName + " withdrew " + amount + " from the account. The remaining balance is " + this.balance + ".");

      return amount;
    }

    // If the withdrawal amount is more than the available balance, return 0
    return 0;
  }
}

// Initialize two variables to keep track of the amount withdrawn by each account holder
let amountWithdrawnByAccountHolder1 = 0;
let amountWithdrawnByAccountHolder2 = 0;

// Create a new Bank Account instance with an initial balance of 1000
let accountHolder = new BankAccount(1000, "Rohit", "Rhea");

// A function to demonstrate the scenario of overdrawn accounts
const AccountOverdrawDemo = async () => {
  console.log("Initial Account balance: " + accountHolder.balance);

  // Define two async functions to simulate withdrawals by the two account holders
  const AccountHolder1 = async () => {
    for(i=0; i<1000; i++){
      amountWithdrawnByAccountHolder1 += await accountHolder.withdraw(100, "Rohit");
    }
  }

  const AccountHolder2 = async () => {
    for(i=0; i<1000; i++){
      amountWithdrawnByAccountHolder2 += await accountHolder.withdraw(100, "Rhea");
    }
  }

  // Run the above two async functions simultaneously using Promise.all()
  await Promise.all([AccountHolder1(), AccountHolder2()]);

  // Print the amount withdrawn by each account holder
  console.log("amountWithdrawnByAccountHolder1: " + amountWithdrawnByAccountHolder1 
      + "\namountWithdrawnByAccountHolder2: " + amountWithdrawnByAccountHolder2 
  );

  // Calculate the total amount withdrawn and check if it is more than the initial balance of the account
  const overdrawnAmount = (amountWithdrawnByAccountHolder1 + amountWithdrawnByAccountHolder2) - 1000;
  console.log("Balance Overdrawn Is: " + overdrawnAmount);
}

// Call the AccountOverdrawDemo function to demonstrate the overdrawn account scenario
AccountOverdrawDemo();
