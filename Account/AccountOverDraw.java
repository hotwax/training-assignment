package Account;

public class AccountOverDraw {

  public static void main(String args[]) {
    // Initialize the account Object;
    Account account = new Account();
    // thread t1 withdraw the amount
    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {

        for (int i = 0; i < 5; i++) {
          // thread1 try to withdraw amount 5 times
          account.Withdraw(200, "t1");

        }

      }

    });

    // thread t2 withdraw the amount
    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {

        for (int i = 0; i < 5; i++) {
          // thread2 try to withdraw amount 5 times
          account.Withdraw(200, "t2");

        }

      }

    });

    t2.start();// begin the execution of thread2
    t1.start();// begin the execution of thread1

  }
}
