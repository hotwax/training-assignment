import AsyncLock from 'async-lock';
const lock = new AsyncLock();

class Account {
  constructor(name1, name2, balance) {
    this.name1 = name1;
    this.name2 = name2;
    this.balance = balance;
  }
  // async method to withdraw money
  async withdraw(amount, name) {
    //using lock to achieve something like a multi-threaded environment
    return lock.acquire('account', async () => {
      // check if the balance is sufficient
      if (this.balance < amount) {
        console.log('Insufficient funds');
        return false;
      } else {
        // Making a delay to simulate the time taken to withdraw money
        await new Promise((resolve) => setTimeout(resolve, 1000));
        this.balance -= amount;
        console.log(`${name} withdrew ${amount} and has ${this.balance} left`);
        return true;
      }
    });
  }
}
//running the runner method multiple threads
const runner = async () => {
  //creating a  new account
  const account = new Account('John', 'Doe', 1000);
  // variable to store the total amount withdrawn
  let amtWithdrawal = 0;
  // performTransaction function to withdraw money
  const performTransaction = async (name, amt) => {
    // loop only run if the withdraw method returns true which means there is balance in the account
    while (true) {
      // calling withdraw method
      if (await account.withdraw(amt, name)) amtWithdrawal += amt;
      else break;
    }
  };
  // Promise.all() method to run multiple promise, rejects if any of the promise is rejected
  await Promise.all([
    performTransaction('John', 100),
    performTransaction('Doe', 100),
  ]);
  console.log(`Total Amount Withdrawn: ${amtWithdrawal}`);
};
runner();
