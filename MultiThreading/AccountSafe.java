package MultiThreading;

public class AccountSafe {
    Account account;
    private int amount;
    private int numberOfWithdrawal;

    // Constructor that initializes the account object and other variables
    AccountSafe(int numberOfWithdrawal, int amount, int balance) {
        this.account = new Account(balance);
        this.numberOfWithdrawal = numberOfWithdrawal;
        this.amount = amount;
    }

    // Thread t1 withdraws the amount
    Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int index = 0; index < numberOfWithdrawal; index++) {
                // Use synchronized block to make withdrawal safe
                // Locks the account object to prevent concurrent access
                synchronized (account) {
                    // Thread1 tries to withdraw amount for 5 times
                    account.Withdrawn(amount, "Gourav");
                }
            }
        }
    });

    // Thread t2 withdraws the amount
    Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int index = 0; index < numberOfWithdrawal; index++) {
                synchronized (account) {
                    // Thread2 tries to withdraw amount for 5 times
                    account.Withdrawn(amount, "Yash");
                }
            }
        }
    });

}
