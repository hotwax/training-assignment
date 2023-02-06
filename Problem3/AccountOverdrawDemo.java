package Problem3;


// Account Class
class Account {
    public String name;
    public int balance = 1000;
    // Constructor
    Account(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }
    // Withdraw method
    public boolean withdraw(int amount,String person){
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
            System.out.println("Account Holder: "+person+" \nInsufficient Balance");
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
    // Overriding the run method
    @Override
    public void run() {
        for(int i=0;i<5;i++){
            // Withdrawing 200 from the account
            account.withdraw(200,person);
        }
    }
    public static void main(String[] args) {
        // Creating a new Account
        Account acc1 = new Account("Johns and Sons",1000);
        // Creating two threads
        AccountOverdrawDemo john = new AccountOverdrawDemo(acc1,"John");
        AccountOverdrawDemo son = new AccountOverdrawDemo(acc1,"Junior John");
        //
        john.start();
        son.start();
    }
}