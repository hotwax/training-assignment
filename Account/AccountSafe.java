package Account;

public class AccountSafe {

    public static void main(String args[]) {
        // initialize the account object
        Account account = new Account();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int index = 0; index < 5; index++) {
                    // Synchronized make Withdraw safe
                    // It is used to lock a object for any shared resourse
                    synchronized (account) {
                        account.Withdraw(200, "Gourav");
                    }
                }

            }

        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int index = 0; index < 2; index++) {

                    synchronized (account) {
                        account.Withdraw(200, "Yash");
                    }

                }

            }

        });

        thread2.start(); // begin the Execution of thread 1
        thread1.start(); // begin the Execution of thread2

    }

}
