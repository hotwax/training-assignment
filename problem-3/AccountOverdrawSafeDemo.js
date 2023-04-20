// For creating a delay creating promise with settimeout (function)
const awaitTimeout = delay => new Promise(resolve => setTimeout(resolve, delay));

// Creating Account class
class BankAccount {
    // Define a constructor that takes an initial balance as a parameter
    constructor(initialBalance) {
      // Set the initial balance to the value passed in as a parameter
      this.balance = initialBalance;
      // Set a lock to prevent concurrent withdrawals
      this.lock = false;
    }
    // Define a method called withdraw that takes an amount as a parameter
    async withdraw(amount) {
      // Check if the requested amount is less than or equal to the current balance
      if (amount <= this.balance) {
        // Check if the lock is already set
        if (this.lock) {
          return 0; // Return 0 if another withdrawal is in progress
        }
        // Set the lock to prevent concurrent withdrawals
        this.lock = true;
        // Subtract the requested amount from the balance after waiting for 10ms
        await awaitTimeout(10);
        this.balance -= amount;
        // Release the lock
        this.lock = false;
        // Return the withdrawn amount
        return amount;
      }
      return 0;
    }
}

// The main logic starts here

//Creating vars to store amount withdraw by persons
let amountWithdrawenByPerson1 = 0;
let amountWithdrawenByPerson2 = 0;

//Creating BankAccount instance with initial balance 0f 1000
let accountHolder = new BankAccount(1000);

//Assuming that account holder has two ATM cards and withdrawing at the same time
//Creating function for the same
const AccountOverdrawDemo = async () => {
    //printing initial Account balance
    console.log("Initial Account balance : " + accountHolder.balance);

    // person 1 withdrawing amount
    const person1 = async () => {
        for (i = 0; i < 1000; i++) {
            const amountWithdrawn = await accountHolder.withdraw(10);
            if (amountWithdrawn === 0) {
                break; // Stop withdrawing if overdrawn
            }
            amountWithdrawenByPerson1 += amountWithdrawn;
        }
    }

    // person 2 withdrawing amount
    const person2 = async () => {
        for (i = 0; i < 1000; i++) {
            const amountWithdrawn = await accountHolder.withdraw(20);
            if (amountWithdrawn === 0) {
                break; // Stop withdrawing if overdrawn
            }
            amountWithdrawenByPerson2 += amountWithdrawn;
        }
    }

    // calling all functions of withdraw by person
    await Promise.all([person1(), person2()]);

    // Printing the amount withdrawn by persons
    console.log("amountWithdrawenByPerson1 : " + amountWithdrawenByPerson1 
        + "\namountWithdrawenByPerson2 : " + amountWithdrawenByPerson2 
    );

    // Printing the balance which overdrawn from the account
    const overDrawnAmount = (amountWithdrawenByPerson1 + amountWithdrawenByPerson2) - 1000;
    console.log("Balance Overdrawn Is : " + overDrawnAmount);
}

// Calling the AccountOverdrawDemo Function
AccountOverdrawDemo();