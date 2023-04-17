//defining an Account class 
class Account{
  // constructor intialises the balance in Account
  constructor(balance)
  {
      this.balance = balance;
  }
/* asynchoronous withdrawn function to check if balance is sufficient or not 
if amount is less than balance then it transaction can be done
*/
  async withdraw(amount) {
      if (this.balance >= amount) {
       await new Promise((resolve)=>{setTimeout(
          resolve,100)}) 
        this.balance -= amount;
        return amount; 
      }
     else{
      return 0;
     }  
    }
  }
//defining main function  
const main=async()=>{
let total=0;
//An Object of account class having balance 1000
const account = new Account(1000);
//function run that will run util transaction is going
const run=async(name)=>{
  while(true)
  {
    const withdrawAmount=  await account.withdraw(100);
    total+=withdrawAmount;
    console.log(`${name} withdraw 100`);
    console.log(`Remaining Balance is: ${account.balance}`)
    if(!withdrawAmount){
      break;
    }
  };
}
// calling an run function for both the accountholders to withdraw simultaneously 
await Promise.all[(run("Kunal"),run("Akhil"))];  
}  
// main function
main();
