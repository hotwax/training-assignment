const AsyncLock = require('async-lock');
const lock = new AsyncLock();

//Nodejs is single threaded, and the code execution never gets interrupted inside an event loop,
//so locking is unnecessary? This is true ONLY IF your critical section can be executed inside a
//single event loop. However, if you have any async code inside your critical section (it can be
//simply triggered by any I/O operation, or timer), your critical logic will across multiple
//event loops, therefore it's not concurrency safe,
//thats why i have used async-lock package to aquire locks.



//class account
class Account {
    constructor(name) {
        this.name = name;
        //initaial balance will be 1000
        this.balance = 1000;
    }

    //asynchronous method withdraw to withdraw ammount.
    async withdraw(amount) {
        //this time we are acquiring lock for synchronously using withdraw method.
        return lock.acquire('account', async() =>{
            if (this.balance >= amount) {

                // Simulate a delay to make it more likely that two threads will access the balance at the same time
                await new Promise(resolve => setTimeout(resolve, 10));
                //subtracticting withdwarwal ammount from balance.
                this.balance -= amount;
                return amount;
            } else {
                //if balance is less than amount returning zero.
                return 0;
            }
        })
        
    }
    //method to get current balance
    getBalance() {
        return this.balance;
    }
}

// run method to simulate multi threading.
async function run() {
    //creating a joint account.
    const account = new Account('Account');
    console.log('Not Synchronized');
    console.log('Total amount in the account =' + account.getBalance());

    //variable to maintain the total ammount withdrawal by two people.
    let totalWithdraw = 0;
    //transaction will contain an unreturned promise
    const transactions = async (person) => {
        
        //this loop will run until there is balance present in the account which is greater than withdrawl ammount.
        while (true) {
            //this varaible will store the amount withdwrawal
            //if balance remaining in the account is smaller than withdrawl ammount then flag will be 0.
            const flag = await account.withdraw(50);//calling method
            totalWithdraw+=flag;
            console.log(`Amount withdrawn by person ${person} = ${flag}`);
            //if flag becomes 0, then break the loop.
            if (!flag) break;
        }

    };

    //if any of the passed-in promises reject,
    //the Promise.all() method asynchronously rejects the value of the promise
    //that already rejected, whether or not the other promises have resolved.
    await Promise.all([transactions(1), transactions(2)]);
    console.log(`Total amount remaining in the account = ${account.getBalance()}`);
    console.log(`Total amount withdraw by two persons = ${totalWithdraw}`);
}

//calling the run method.
run();
