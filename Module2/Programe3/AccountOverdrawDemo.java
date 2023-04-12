// Account Class
class Account {

    // name of the customer
    public String name;
    // starting Bank Balance
    public float balance = 1000;

    // Constructor
    Account(String name, float balance) {
        this.name = name;
        this.balance = balance;
    }

    // withdraw method to withdraw money from the account

    public void withDraw(float amount, String person) {
        // thread sleep for a moment so that new thread start running
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        // if amount is sufficient then withdraw the money from the account else print
        // Warning Message
        if (balance >= amount) {
            balance = balance - amount;
            System.out.println("Amount Withdraw from Account Holder : " + person + "\nAmount Withdraw is :" + amount
                    + "\nCurrent Amount is :" + balance);
        } else {
            System.out.println("Sorry Insufficient Balance");
        }
    }
}

// main class

public class AccountOverdrawDemo extends Thread {

    // account of person
    Account account;
    // account holder name
    String accountHolder;

    // Constructor
    public AccountOverdrawDemo(Account account, String accountHolder) {
        this.account = account;
        this.accountHolder = accountHolder;
    }

    // run method run automatically then new thread is created and call the withdraw
    // method to withdraw the money
    public void run() {
        for (int i = 0; i < 10; i++) {
            account.withDraw(100, accountHolder);
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // Creating a new account
        Account acc = new Account("Pushpraj and Lakshyaraj", 1000);

        // creating two threads
        AccountOverdrawDemo acc1 = new AccountOverdrawDemo(acc, "Pushpraj");
        AccountOverdrawDemo acc2 = new AccountOverdrawDemo(acc, "Lakshyaraj");
        // Start the thread
        acc1.start();
        acc2.start();

    }

}
