class AccountOverdrawSafeDemo implements Runnable {
    private Account account; // instance variable

    public static void main(String args[]) {
        Account account = new Account();
        AccountOverdrawSafeDemo accountOverdraw = new AccountOverdrawSafeDemo(account);
        Thread thread1 = new Thread(accountOverdraw);
        Thread thread2 = new Thread(accountOverdraw);
        // Give names to threads
        thread1.setName("Kunal");
        thread2.setName("Akhil");
        // Start threads
        thread1.start();
        thread2.start();
    }

    public AccountOverdrawSafeDemo(Account account) {
        this.account = account;
    }

    // Run method to check current balance while the thread is withdrawing
    @Override
    public synchronized void run() {
        for (int index = 1; index <= 5; index++) {
            withdraw(200); // Withdraw 200 each time
            if (account.getBalance() < 0) {
                System.out.println("Account is Overdrawn");
            }
        }
    }

    // Withdraw method
    private void withdraw(int withdrawAmount) {
        if (account.getBalance() >= withdrawAmount) {
            System.out.println(Thread.currentThread().getName() + " is going to withdraw " + withdrawAmount);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Error");
            }
            account.withdraw(withdrawAmount);
            System.out.println(Thread.currentThread().getName() + " completed withdraw of " + withdrawAmount);
        } else {
            System.out.println(
                    "Not enough amount for " + Thread.currentThread().getName() + " to withdraw: "
                            + account.getBalance());
        }
    }
}
