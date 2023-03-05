public class AccountOverdrawSafeDemo {
    public static void main(String[] args) {
        Account account = new Account("John Smith", 1000);

        Runnable withdrawal1 = () -> {
            synchronized(account) {
                for (int i = 0; i < 5; i++) {
                    account.withdraw(200);
                }
            }
        };

        Runnable withdrawal2 = () -> {
            synchronized(account) {
                for (int i = 0; i < 5; i++) {
                    account.withdraw(200);
                }
            }
        };

        Thread thread1 = new Thread(withdrawal1);
        Thread thread2 = new Thread(withdrawal2);

        thread1.start();
        thread2.start();
    }
}
 
