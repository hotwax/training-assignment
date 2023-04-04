//creating account class
class Account {
    constructor(name1, name2, balance) {
        this.name1 = name1;
        this.name2 = name2;
        this.balance = balance;
    }

    // method to withdraw amount from account
    async withdraw(amount, name) {
        //only if amount is less then amount will withdraw
        if (this.balance >= amount) {
            console.log(`${name} is withdrawing ${amount}`);
            await new Promise(resolve => setTimeout(resolve, 1000));
            this.balance -= amount;
            console.log(`New balance: ${this.balance}`);// withdraw succesful
            return true;
        } else {
            //if they don't have balance
            console.log(`${name} you do not have ${amount}`);
            return false;
        }
    }
}

class AccountOverdrawDemo {
    //run method to run tasks or threads 
    static async run() {
        //joint account holder's, account creation 
        const account = new Account('Ishaan', 'Akar', 1000);

        // Withdrawal 1
        const thread1 = async (name) => {
            while (true) {
                //randomly generating withdraw amount
                const flag = await account.withdraw(Math.floor(Math.random() * 300) + 100, name);//calling method
                if (!flag) break;
            }
        };

        // Withdrawal 2 
        const thread2 = async (name) => {
            while (true) {
                //randomly generating withdraw amount
                const flag = await account.withdraw(Math.floor(Math.random() * 500) + 100, name);//calling method
                if (!flag) break;
            }
        };

        await Promise.all([thread1('Ishaan'), thread2('Akar')]);
        console.log(`Final balance: ${account.balance}`);
    }
}

// Test AccountOverdrawDemo
console.log('Running AccountOverdrawDemo...\n');
AccountOverdrawDemo.run().catch(console.error);
