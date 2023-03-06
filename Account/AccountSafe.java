package Account;

public class AccountSafe {

    public static void main(String args[]) {
        // initialize the account object
        Account account = new Account();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 5; i++) {
                    // Synchronized make Withdraw safe
                    // It is used to lock a object for any shared resourse
                    synchronized (account) {
                        account.Withdraw(200, "t1");
                    }
                }

            }

        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 5; i++) {

                    synchronized (account) {
                        account.Withdraw(200, "t2");
                    }

                }

            }

        });

        t2.start(); // begin the Execution of thread 1
        t1.start(); // begin the Execution of thread2

    }

}
