 // Creating Account class
 class Account {
  constructor(name1, name2, balance) {
    this.name1 = name1;
    this.name2 = name2;
    this.balance = balance;
  }
  // async method to withDraw money
  async withDraw(amount, name) {
    // check if the balance is sufficient
    if (this.balance < amount) {
      return false;
    } else {

      // Making a delay to simulate the time taken to withdraw money
      console.log(`${name} is withdrawing ${amount}`);
      await new Promise(resolve => {
        this.balance -= amount;
        console.log(`${name} withdrew ${amount}`);
        console.log(`balance Left ${this.balance}`);
        setTimeout(resolve, 1000);
      }
      );
      return true;
    }
  }
}

//running the run method multiple threads
const run = async () => {
  // creating  object of class Account for two users
  const account = new Account('User1', 'User2', 1000);

  let amtWithdrawal = 0; // keep record of total amount withdrawn

  // Withdrawal 1
  const thread1 = async (name , amt) => {
      while (true) {
          if (await account.withDraw(amt, name)) amtWithdrawal += amt;
          else break;
      }
  };

  // Withdrawal 2 
  const thread2 = async (name , amt ) => {
      while (true) {
          // calling withDraw method
          if (await account.withDraw(amt, name)) amtWithdrawal += amt;
          else break;
      }
  };
  
  // Promise.all() method to run multiple promise, rejects if any of the promise is rejected
  await Promise.all([
    thread1('User1', 100),
    thread2('User2', 100),
  ]);
  console.log(`Total Amount Withdrawn: ${amtWithdrawal}`);
};

// calling run function
run();