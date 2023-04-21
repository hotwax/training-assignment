import { Account } from './Account.js';
// method to start the withdrawals
const doWithdrawals= async ()=>
{   const account = new Account('Joint Account', 1000);
    const withdrawAmount = 100;
    

    const withdrawal= async(personName)=>{
      
        while(true){

            let result=await account.withdraw(withdrawAmount); 
            console.log(`${personName}: Attempting to withdraw ${withdrawAmount}`);
            console.log('Remaining Balance: '+account.balance);

            if (!result){
                break;
            
        }

    }
    }

    // start the 2 concurrent withdrawals
    await Promise.all([withdrawal('Tejasvi'),withdrawal('TejPratap')]);


}

// starting the withdrawals
doWithdrawals();




