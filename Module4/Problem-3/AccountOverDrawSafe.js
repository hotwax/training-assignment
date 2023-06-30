//creating account class
class Account {
    constructor(name1, name2, balance) {
        this.name1 = name1;
        this.name2 = name2;
        this.balance = balance;
    }
    // method to withdraw amount from account
    async withdraw(amount, name) {
        //Withdrawing only if amount is less than the balance.
        if (this.balance >= amount) {
            console.log(`${name} is withdrawing ${amount}`);
            await new Promise(resolve => {
                this.balance -= amount;
                console.log(`New balance: ${this.balance}`); // withdraw succesful
                setTimeout(resolve, 100);
            }
            );
            return true;
        } else {
            //if they don't have balance
            console.log(`${name} you do not have ${amount}`);
            return false;
        }
    }
}

class AccountOverdrawSafeDemo {
    //run method to run tasks or threads 
    static async run() {
        //Account creation
        const account = new Account('Vishal', 'Sanskar', 1000);
        let flag = true; // To bifercate between thread1 and thread2.

        // Withdraw 1
        const thread1 = async (name) => {
            while (true) {
                //  Delaying the process 
                await new Promise(resolve => setTimeout(resolve, 100));
                //randomly generating withdraw amount

                const flag = await account.withdraw(Math.floor(Math.random() * 500) + 100, name); //calling method
                if (!flag) break;
            }

        };
        // Withdraw 2
        const thread2 = async (name) => {
            while (true){
                //  Delaying the process
                await new Promise(resolve => setTimeout(resolve, 4000));
                //randomly generating withdraw amount

                const flag = await account.withdraw(Math.floor(Math.random() * 500) + 100, name); //calling method
                if (!flag) break;
            }
        };

        await Promise.all([thread1('Vishal'), thread2('Sanskar')]);
        console.log(`Final balance: ${account.balance}`);
    }
}


// Test AccountOverdrawSafeDemo
console.log('Running AccountOverdrawSafeDemo \n');
AccountOverdrawSafeDemo.run().catch(console.error);