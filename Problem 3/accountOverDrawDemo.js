class Account {
  constructor(name1, name2, balance) {
    this.name1 = name1;
    this.name2 = name2;
    this.balance = balance;
  }
  // async method to withdraw money
  async withdraw(amount, name) {
    if (this.balance < amount) {
      console.log('Insufficient funds');
      return false;
    } else {
      // wait for 1 second
      await new Promise((resolve) => setTimeout(resolve, 1000));
      this.balance -= amount;
      console.log(`${name} withdrew ${amount} and has ${this.balance} left`);
      return true;
    }
  }
}
const runner = async () => {
  const account = new Account('John', 'Doe', 1000);
  let amtWithdrawal = 0;
  const thread1 = async (name, amt) => {
    while (true) {
      if (await account.withdraw(amt, name)) amtWithdrawal += amt;
      else break;
    }
  };
  await Promise.all([thread1('John', 100), thread1('Doe', 200)]);
  console.log(`Total Amount Withdrawn: ${amtWithdrawal}`);
};
runner();
