package Problem3;
// Account Class
class AccountSafe {
    public String name;
    public int balance = 1000;
    // Constructor
    AccountSafe(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }
    // Synchronized withdraw method
   synchronized public boolean withdraw(int amount,String person){
        try{
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(balance >= amount){
            balance -= amount;
            System.out.println("Account Holder: "+person +"\nWithdrawal: " + amount + " | Current Balance: " + balance);
            return  true;
        } else {
            System.out.println("Account Holder: "+person+" Insufficient Balance");
        }
        return false;
    }
}


class AccountOverdrawSafeDemo extends Thread {
    AccountSafe account;
    String name;
    // AccountOverdrawSafeDemo Constructor
    public AccountOverdrawSafeDemo(AccountSafe account, String name) {
        this.account = account;
        this.name = name;
    }
    //Overriding the run method
    @Override
    public void run(){
        for(int i=0;i<5;i++){
            // Synchronized block
            synchronized (account){
                // Withdrawing 200 from the account
                account.withdraw(200,name);
            }
        }
    }    public static void main(String[] args) {
        // Creating a new Account
        AccountSafe acc1 = new AccountSafe("Johns and Sons",1000);
        // Creating two threads
        AccountOverdrawSafeDemo john = new AccountOverdrawSafeDemo(acc1,"John");
        AccountOverdrawSafeDemo son = new AccountOverdrawSafeDemo(acc1,"Junior John");
        // Starting the threads
        john.start();
        son.start();
    }
}