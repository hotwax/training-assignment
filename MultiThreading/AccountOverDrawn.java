package MultiThreading;

// AccountOverDrawn class
public class AccountOverDrawn {
  Account account;
  private int withdrawAmount;
  private int numberOfWithdraw;

  // constructor
  AccountOverDrawn(int numberOfWithdrawal, int amount, int balance) {
    this.account = new Account(balance); // Creating a new Account object with the given balance
    this.withdrawAmount = amount;
    this.numberOfWithdraw = numberOfWithdrawal;
  }

  // thread t1 withdraw the amount
  Thread thread1 = new Thread(new Runnable() {
    @Override
    public void run() {
      for (int index = 0; index < numberOfWithdraw; index++) {
        // thread1 tries to withdraw the specified amount from the account
        account.Withdrawn(withdrawAmount, "Gourav"); // Calling the Withdrawn() method of the Account class
      }
    }
  });

  // thread t2 withdraw the amount
  Thread thread2 = new Thread(new Runnable() {
    @Override
    public void run() {
      for (int i = 0; i < numberOfWithdraw; i++) {
        // thread2 tries to withdraw the specified amount from the account
        account.Withdrawn(withdrawAmount, "Yash"); // Calling the Withdrawn() method of the Account class
      }
    }
  });
}