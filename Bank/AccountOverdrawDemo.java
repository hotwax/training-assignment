class AccountOverdrawDemo implements Runnable{
  private Account account; //instance variable
public static void main(String args[])
{
    Account account = new Account();
    AccountOverdrawDemo accountOverdraw = new AccountOverdrawDemo(account);
    Thread T1=  new Thread(accountOverdraw);
    Thread T2= new Thread(accountOverdraw);
    //Given names to threads
    T1.setName ("Kunal");
    T2.setName ("Akhil");
    //starting thread 
    T1.start();
    T2.start();
}

public AccountOverdrawDemo(Account account)
{
    this.account = account;
}

//run method to check current Balance  while Thread is withdrawing
@Override
public void run(){
    for(int index=1;index<=5;index++)
    {
        withdraw(200); //withdraw 200 each time
        if(account.getBalance()< 0)
        {
            System.out.println("Account is Overdrawn");
        }
    }
}

//withdraw method
private void withdraw(int withdrawAmount)
{
    if(account.getBalance()>=withdrawAmount)
    {
        System.out.println (Thread.currentThread().getName()+" "+"is going to withdraw"+" "+withdrawAmount);
        try
        {
            Thread.sleep(1000);
        }catch(InterruptedException e){
            System.out.println("Error");
        }
        account.withdraw(withdrawAmount);
        System.out.println (Thread.currentThread().getName()+" "+"Completed withdraw of"+" "+withdrawAmount);
    }
    else
    {
        System.out.println("Not Enough Amount for" +" "+ Thread.currentThread().getName()+" "+ " to withdraw"+" "+account.getBalance());
    } 
    }
}

 