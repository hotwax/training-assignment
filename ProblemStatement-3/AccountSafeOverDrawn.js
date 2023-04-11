// Import the AsyncLock library
import AsyncLock from "async-lock";

// Create a new object of AsyncLock
let lock = new AsyncLock();

// Define a class called Account
class Account {
  
    // Constructor to initialize the account's total balance
    constructor(totalBalance) {
        this.totalBalance = totalBalance;
    }

    // Define an asynchronous function called withdraw
    async withdrawn(amount) {
        // Use the lock to ensure that only one instance of this function can be executed at a time
        return lock.acquire('', async () => {
            // Check if the total balance is greater than or equal to the amount being withdrawn
            if (this.totalBalance >= amount) {
                // Wait for 10ms before proceeding
                await new Promise((resolve) => {
                    setTimeout(resolve, 100)
                })
                this.totalBalance -= amount;
                return amount;           
            } else {
                return 0;
            }
        });
    }
}

// Define an asynchronous function called main
const main = async () => {
    // Create an Object of the Account class with a balance of 1000
    const account = new Account(800);
    let totalWithdrawn = 0;
    console.log(`Total balance is - ${account.totalBalance}`);

    // a transaction function that will keep withdrawing until there is no balance left
    const transaction = async (person) => {   
        while (true) {
            let amountWithdrawn = await account.withdrawn(100);
            totalWithdrawn += amountWithdrawn;
            console.log(`Transaction completed by ${person} and transaction amount is - 100`);
            console.log(`Remaining balance is ${account.totalBalance}`);
            if (!amountWithdrawn) {
                break;
            }  
        }
    }
    // Call the transaction function for three persons simultaneously using Promise.all
    await Promise.all([transaction("Gourav"), transaction("Yash"),transaction("Kunal")]);    
    console.log(`Total withdrawal amount of both persons is - ${totalWithdrawn}`);
}
// Call the main function
main();