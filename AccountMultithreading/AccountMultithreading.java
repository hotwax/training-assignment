public class AccountMultithreading {

  public static void main(String[] args) {
    Account account1 = new Account("account1");
    System.out.println("Current balance: " + account1.getBalance());

    // AccountOverdrawDemo thread1 = new AccountOverdrawDemo(account1, "person1");
    // AccountOverdrawDemo thread2 = new AccountOverdrawDemo(account1, "person2");

    AccountOverdrawSafeDemo thread1 = new AccountOverdrawSafeDemo(account1, "person1");
    AccountOverdrawSafeDemo thread2 = new AccountOverdrawSafeDemo(account1, "person2");

    // in using AccountOverdrawDemo, the sum of totalWithdrawalAmount of thread1 and thread2 will be > 1000 
    // which is the scenario we need to handle.
    // in using AccountOverdrawSafeDemo, the sum of totalWithdrawalAmount of thread1 and thread2 will always be = 1000
    // by using synchronized block.

    thread1.start();
    thread2.start();
  }

}