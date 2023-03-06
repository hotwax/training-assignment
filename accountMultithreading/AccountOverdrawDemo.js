class Account {
  constructor(person1, person2, balance) { //person1 and person2 names
    this.person1 = person1;
    this.person2 = person2;
    this.balance = balance;
  }

  async withdraw(withdrawalAmount, person) {

    if (this.balance >= withdrawalAmount) { // sufficient balance
      await new Promise(resolve => setTimeout(resolve, 1000));  //making the person wait so that the other person can interfere in between
      this.balance -= withdrawalAmount; // reduction in balance
      console.log(`${person}, withdrawal amount ${withdrawalAmount},   New balance: ${this.balance}\n`);
      return true; // withdrawal marker
    } else { // insufficient balance
      console.log(`${person} you don't have sufficient balance\n`);
      return false; // withdrawal stopped marker
    }
  }
}


class AccountOverdrawDemo {

  static async run() {
    
    const account = new Account('person1', 'person2', 1000); //two joint account holders- person1, person2

    // two threads thread1 and thread2
    // Withdrawal 1
    const thread1 = async (person) => {
      let totalWithdrawalAmount=0;
      while (true) { // will run until insufficient balance found
        if(await account.withdraw(200, person)) totalWithdrawalAmount+=200;
        else break;  // insuffient balance
      }
      console.log(person, " total withdrawal amount: "+ totalWithdrawalAmount+"\n-------------------\n");
    };

    // Withdrawal 2 
    const thread2 = async (person) => {
      let totalWithdrawalAmount=0;
      while (true) { // will run until insufficient balance found
        if(await account.withdraw(200, person)) totalWithdrawalAmount+=200;
        else break; // insuffient balance
      }
      console.log(person, " total withdrawal amount: "+ totalWithdrawalAmount+"\n-------------------\n");
    };


    //It returns a single Promise that resolves when all of the promises passed as an iterable, have resolved or when the iterable contains no promises.
    await Promise.all([thread1('person1'), thread2('person2')]);  //an array of promises

    // in AccountOverdrawDemo now balance is going to be '-200' because of withdraw method not being synchronized
    console.log(`Final balance in the account: ${account.balance}`);  
  }
}



module.exports = AccountOverdrawDemo;
