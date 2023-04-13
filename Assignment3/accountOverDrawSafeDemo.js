// A helper function to introduce a delay using setTimeout
const awaitTimeout = delay => new Promise(resolve => setTimeout(resolve, delay));

class BankAccount {
    constructor(initialBalance, name1, name2) {
        // Set initial balance and account holder names
        this.balance = initialBalance;
        this.name1 = name1;
        this.name2 = name2;
        // Set initial lock state to false
        this.lock = false;
    }

    async withdraw(amount, name) {
        if (amount <= this.balance) {
            // If the account is locked, return 0 indicating the withdrawal failed
            if (this.lock) {
                return 0;
            }
            // Set lock to true to prevent simultaneous withdrawals
            this.lock = true;
            // Wait for 10 milliseconds to simulate a delay in the withdrawal process
            await awaitTimeout(10);
            // Reduce the balance by the withdrawn amount and update the lock state
            this.balance -= amount;
            this.lock = false;
            // Log the withdrawal and remaining balance
            console.log(name + " withdrew " + amount + " from the account. The remaining balance is " + this.balance + ".");
            return amount;
        }
        // If the withdrawal amount exceeds the account balance, return 0 indicating the withdrawal failed
        return 0;
    }
}

// Initialize variables to keep track of the total amount withdrawn by each person
let amountWithdrawnByPerson1 = 0;
let amountWithdrawnByPerson2 = 0;

// Create a new BankAccount instance with an initial balance of 1000
let accountHolder = new BankAccount(1000, "Rohit", "Rhea");

// Define an async function to demonstrate overdrawing from the account
const AccountOverdrawDemo = async () => {
    console.log("Initial Account balance : " + accountHolder.balance);

    // Define an async function for person1 to withdraw 100 units from the account up to 1000 times
    const person1 = async () => {
        for (i = 0; i < 1000; i++) {
            const amountWithdrawn = await accountHolder.withdraw(100, "Rohit");
            // If the withdrawal amount is 0, it means the withdrawal failed due to insufficient balance, so break out of the loop
            if (amountWithdrawn === 0) {
                break;
            }
            // If the withdrawal was successful, add the withdrawn amount to the total amount withdrawn by person1
            amountWithdrawnByPerson1 += amountWithdrawn;
        }
    }

    // Define an async function for person2 to withdraw 20 units from the account up to 1000 times
    const person2 = async () => {
        for (i = 0; i < 1000; i++) {
            const amountWithdrawn = await accountHolder.withdraw(100, "Rhea");
            // If the withdrawal amount is 0, it means the withdrawal failed due to insufficient balance, so break out of the loop
            if (amountWithdrawn === 0) {
                break;
            }
            // If the withdrawal was successful, add the withdrawn amount to the total amount withdrawn by person2
            amountWithdrawnByPerson2 += amountWithdrawn;
        }
    }

    // Wait for both person1 and person2 to finish their withdrawals
    await Promise.all([person1(), person2()]);

    // Log the total amount withdrawn by each person

    console.log("amountWithdrawenByPerson1 : " + amountWithdrawnByPerson1 
        + "\namountWithdrawenByPerson2 : " + amountWithdrawnByPerson2 
    );

    const overDrawnAmount = (amountWithdrawnByPerson1 + amountWithdrawnByPerson2) - 1000;
    console.log("Balance Overdrawn Is : " + overDrawnAmount);
}

AccountOverdrawDemo();