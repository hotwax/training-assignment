public class AccountOverdrawSafeDemo {

    synchronized static void debit (Account account, String name, int amount) {
        account.withdraw(name, amount);
    }

    public static void main(String[] args) {
        Account account = new Account();
        System.out.println();
        Thread thread1 = new Thread(new Runnable() {
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

        thread1.start();
        thread2.start();
    }
    
}
