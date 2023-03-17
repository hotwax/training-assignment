public class AccountOverdrawSafeDemo {


    // Method to withdraw money from the account using synchronized keyword
    synchronized static void debit (Account account, String name, int amount) {
        account.withdraw(name, amount);
    }

    public static void main(String[] args) {
        Account account = new Account();
        System.out.println();

        // Thread1 and Thread2 are trying to withdraw money from the same account
        Thread thread1 = new Thread(new Runnable() {

            // Method to run the thread
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    debit(account, "thread1", 100);
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    debit(account, "thread2", 100);
                }
            }
        });

        // Starting the threads
        thread1.start();
        thread2.start();
    }
    
}
