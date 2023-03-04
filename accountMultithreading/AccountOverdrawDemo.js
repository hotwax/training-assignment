class Account {
  constructor(person1, person2, balance) {
    this.person1 = person1;
    this.person2 = person2;
    this.balance = balance;
  }

  async withdraw(withdrawalAmount, person) {

    if (this.balance >= withdrawalAmount) {
      await new Promise(resolve => setTimeout(resolve, 1000));  //making the person wait so that the other person can interfere in between
      this.balance -= withdrawalAmount;
      console.log(`${person}, withdrawal amount ${withdrawalAmount},   New balance: ${this.balance}\n`);
      return true;
    } else {
      console.log(`${person} you don't have sufficient balance\n`);
      return false;
    }
  }
}


class AccountOverdrawDemo {

  static async run() {
    
    const account = new Account('person1', 'person2', 1000); //joint account holders- person1, person2

    // Withdrawal 1
    const thread1 = async (person) => {
      let totalWithdrawalAmount=0;
      while (true) {
        if(await account.withdraw(200, person)) totalWithdrawalAmount+=200;
        else break;  // insuffient balance
      }
      console.log(person, " total withdrawal amount: "+ totalWithdrawalAmount+"\n-------------------\n");
    };

    // Withdrawal 2 
    const thread2 = async (person) => {
      let totalWithdrawalAmount=0;
      while (true) {
        if(await account.withdraw(200, person)) totalWithdrawalAmount+=200;
        else break; // insuffient balance
      }
      console.log(person, " total withdrawal amount: "+ totalWithdrawalAmount+"\n-------------------\n");
    };

    await Promise.all([thread1('person1'), thread2('person2')]);
    console.log(`Final balance in the account: ${account.balance}`);
  }
}



module.exports = AccountOverdrawDemo;
