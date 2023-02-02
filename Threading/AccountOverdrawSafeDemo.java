package Threading;

class AccountOverdrawSafeDemo {

    public static void main(String[] args) {
        // created a joint account for threads (thread1, thread2)
        Account account = new Account("Account");
        System.out.println("Not Synchronized");
        System.out.println("Total amount in the account =" + account.getBalance());
        //creating thread 1
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                double totalWithdraw = 0;
                //making transaction of 1 rs 1000 times
                for (int i = 0; i < 1000; i++) {
                    //I have added Synchronized block to the places where withdraw method is called.
                    //this will protect thread1 to access balance variable of class Account when thread2 is using that variable.
                    synchronized (account) {
                        totalWithdraw += account.withdraw(1);
                    }
                }
                System.out.println("Amount withdraw by person 1 =" + totalWithdraw);
            }
        });

        //creating thread 1
        Thread thread2 = new Thread(new Runnable() {
            double totalWithdraw = 0;

            @Override
            public void run() {
                double totalWithdraw = 0;
                //making transaction of 1 rs 1000 times
                for (int i = 0; i < 1000; i++) {
                    //I have added Synchronized block to the places where withdraw method is called.
                    //this will protect thread2 to access balance variable of class Account when thread1 is using that variable.
                    synchronized (account) {
                        totalWithdraw += account.withdraw(1);
                    }
                }
                System.out.println("Amount withdraw by person 2 =" + totalWithdraw);
            }
        });
        //starting both threads.
        thread1.start();
        thread2.start();
    }
}
