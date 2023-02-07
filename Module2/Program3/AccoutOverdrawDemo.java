package Program3;


// Account Class
class Account {
    public String name; //Name of the customer
    public int balance = 1000; //Balance of the customer.

    // Constructor
     Account(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }
    // Withdraw method : This method tells the user whether he can withdraw money or not.
    public boolean withdraw(int amount,String person){
        try{
            Thread.sleep(100);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        if(balance >= amount){
            balance -= amount;
            System.out.println("Account Holder: "+name +"\nWithdrawal Amount: " + amount + " | Current Balance: " + balance);
            return  true;
        } else {
            System.out.println("Account Holder: "+name+" \n Sorry!!! Insufficient Balance");
        }
        return false;
    }
}


class AccountOverdrawDemo extends Thread{
    Account account;
    String person;
    public AccountOverdrawDemo(Account account,String person){
        this.account = account;
        this.person = person;
    }

    @Override
    public void run() { // Overriding the run method
        for(int i=0; i<5; i++){
            // Withdrawing 200 from the account
            account.withdraw(200,person);
        }
    }
    // main method:
    public static void main(String[] args) {
        // Creating a new Account
        Account acc1 = new Account("Aman and Gaurav",1000);
        // Creating two threads
        AccountOverdrawDemo Aman = new AccountOverdrawDemo(acc1,"Aman");
        AccountOverdrawDemo Gaurav = new AccountOverdrawDemo(acc1,"Gaurav");
        //
        Aman.start();
        Gaurav.start();
    }
}