import  AsyncLock  from "async-lock";
const lock=new AsyncLock();

export class Account {
  constructor(name, balance = 1000) {
    this.name = name;
    this.balance = balance;
  }
  
// Asynchronised Withdraw - no lock, so multiple people can withdraw at the same time
  async withdraw(amount) {
    if (amount > this.balance) {
      console.log(`${this.name}: Withdrawal amount ${amount} exceeds balance ${this.balance}`);
      return false;
    }
    await new Promise((resolve) => setTimeout(resolve, 1000));

    this.balance -= amount;
    console.log(`${this.name}: Withdrawal of ${amount} successful, new balance is ${this.balance}`);
    return true;
  }


// Synchronised Withdraw - use the lock to ensure only one person can withdraw at a time
  async withdrawSyncronized(amount) {
   return await lock.acquire('',async()=>{
    if (amount > this.balance) {
      console.log(`${this.name}: Withdrawal amount ${amount} exceeds balance ${this.balance}`);
      return false;
    }
    await new Promise((resolve) => setTimeout(resolve, 1000));
    this.balance -= amount;
    console.log(`${this.name}: Withdrawal of ${amount} successful, new balance is ${this.balance}`);
    return true;
   });
  
}
}


