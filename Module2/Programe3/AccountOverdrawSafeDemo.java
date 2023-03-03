
class AccountSafe {

    // name of the customer
    public String name;
    // starting Bank Balance
    public float balance = 1000;

    // Constructor
    AccountSafe(String name, float balance) {
        this.name = name;
        this.balance = balance;
    }

    // withdraw method to withdraw money from the account

    synchronized public void withdraw(float amount, String person) {
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

public class AccountOverdrawSafeDemo extends Thread {
    // account of person
    AccountSafe account;
    // account holder name
    String accountholder;

    // Constructor
    public AccountOverdrawSafeDemo(AccountSafe acc, String accountholder) {
        this.account = acc;
        this.accountholder = accountholder;
    }

    // run method run automatically then new thread is created and call the withdraw
    // method to withdraw the money
    public void run() {
        for (int i = 0; i < 6; i++) {
            synchronized (account) {
                // Withdrawing 200 from the account
                account.withdraw(100, accountholder);
            }
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // Creating a new account
        AccountSafe acc = new AccountSafe("Pushpraj and Lakshyaraj", 1000);

        // creating two threads
        AccountOverdrawSafeDemo acc1 = new AccountOverdrawSafeDemo(acc, "Pushpraj");
        AccountOverdrawSafeDemo acc2 = new AccountOverdrawSafeDemo(acc, "Lakshyaraj");

        acc1.start();
        acc2.start();

    }

}
