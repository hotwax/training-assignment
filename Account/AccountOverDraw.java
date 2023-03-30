package Account;

public class AccountOverDraw {

  public static void main(String args[]) {
    // Initialize the account Object;
    Account account = new Account();
    // thread t1 withdraw the amount
    Thread thread1 = new Thread(new Runnable() {
      @Override
      public void run() {

        for (int index = 0; index < 5; index++) {
          // thread1 try to withdraw amount 5 times
          account.Withdraw(200, "Gourav");

        }

      }

    });

    // thread t2 withdraw the amount
    Thread thread2 = new Thread(new Runnable() {
      @Override
      public void run() {

        for (int i = 0; i < 2; i++) {
          // thread2 try to withdraw amount 5 times
          account.Withdraw(200, "Yash");

        }

      }

    });

    thread2.start();// begin the execution of thread2
    thread1.start();// begin the execution of thread1

  }
}
