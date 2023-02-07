package Program3;
// Account Class
class AccountSafe {
    public String name; //Name of the customer.
    public int balance = 1000; //Balance for the customer
    // Constructor
    AccountSafe(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }
    // Synchronized withdraw method
    synchronized public boolean withdraw(int amount,String name){
        try{
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(balance >= amount){
            balance -= amount;
            System.out.println("Account Holder: "+name +"\nWithdrawal Amount: " + amount + " | Current Balance: " + balance);
            return  true;
        } else {
            System.out.println("Account Holder: "+name+" Sorry!!! Insufficient Balance");
        }
        return false;
    }
}


class AccountOverdrawSafeDemo extends Thread {
    AccountSafe account; //Object creation.
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
        AccountSafe acc1 = new AccountSafe("Aman and Gaurav",1000);
        // Creating two threads
        AccountOverdrawSafeDemo Aman = new AccountOverdrawSafeDemo(acc1,"Aman");
        AccountOverdrawSafeDemo Gaurav = new AccountOverdrawSafeDemo(acc1,"Gaurav");
        // Starting the threads
        Aman.start();
        Gaurav.start();
    }
}
