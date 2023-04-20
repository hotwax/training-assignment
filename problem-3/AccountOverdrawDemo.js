// For creating a delay creating promise with settimeout (function)
const awaitTimeout = delay => new Promise(resolve => setTimeout(resolve, delay));

// Creating Account class
class BankAccount {
    // Define a constructor that takes an initial balance as a parameter
    constructor(initialBalance) {
      // Set the initial balance to the value passed in as a parameter
      this.balance = initialBalance;
    }
    // Define a method called withdraw that takes an amount as a parameter
    async withdraw(amount) {
      // Check if the requested amount is less than or equal to the current balance
      if (amount <= this.balance) {

        // Subtract the requested amount from the balance after waitring for 1000 ms
        await awaitTimeout(10).then();
        this.balance -= amount;

        // Returning the withdrawn amount
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

//Assumming that account holder has two atm card an dwithdrawing at the same time
//Creating function for the same
const AccountOverdrawDemo = async () => {
    //printing initial Acoount balance
    console.log("Initial Account balanace : " + accountHolder.balance);

    // person 1 withdrwaing amount
    const person1 = async () => {
        for(i=0; i<1000; i++){
            amountWithdrawenByPerson1 += await accountHolder.withdraw(30);
        }
    }

    // person 2 withdrwaing amount
    const person2 = async () => {
        for(i=0; i<1000; i++){
            amountWithdrawenByPerson2 += await accountHolder.withdraw(40);
        }
    }

    // calling all functions of withdraw by person
    await Promise.all([person1(), person2()]);

    // Printint the amount withdrawn by persons
    console.log("amountWithdrawenByPerson1 : " + amountWithdrawenByPerson1 
        + "\namountWithdrawenByPerson2 : " + amountWithdrawenByPerson2 
    );

    //Printing the the balance which overdrawn from the account
    const overDrawnAmount = (amountWithdrawenByPerson1 + amountWithdrawenByPerson2) - 1000;
    console.log("Balance Overdrawn Is : " + overDrawnAmount);
}


//Calling the AccountOverdrawDemo Function
AccountOverdrawDemo();



