 // Creating Account class
 class Account {
  constructor( balance) {
    this.balance = balance;
  }
  // async method to withDraw money
  async withDraw(amount, name) {
    // check if the balance is sufficient
    if (this.balance < amount) {
      return false;
    } else {

      // Making a delay to simulate the time taken to withdraw money
      await new Promise(resolve => {
        setTimeout(resolve, 1000);
      }
      );
      this.balance -= amount;
      return amount;
    }
  }
}

//running the run method multiple threads
const run = async () => {
  // creating  object of class Account for two users
  const account = new Account( 1000);

  let amtWithdrawal = 0; // keep record of total amount withdrawn

  // Withdrawal 
  const thread1 = async (name , amt) => {
      while (true) {
        amtWithdrawal += amt;
        console.log(`${name} withdrew ${amt}`);
        console.log(`balance Left ${account.balance}`);
          if (!await account.withDraw(amt, name))
          {
            break;
          } 
      }
  };

 
  
  // Promise.all() method to run multiple promise, rejects if any of the promise is rejected
  await Promise.all([
    thread1('User1', 100),
    thread1('User2', 100),
  ]);
  console.log(`Total Amount Withdrawn: ${amtWithdrawal}`);
};

// calling run function
run();