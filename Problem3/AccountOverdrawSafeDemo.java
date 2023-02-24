class Account {
  String name; //name of joint account
  int balance; // the balance that is present

  Account(String name, int balance) //constructor to initialize
  {
    this.balance = balance;
    this.name = name;
  }

  synchronized void withdraw(String name, int with_draw) // method to withdraw cash but not getting into negative balance
  {
    if (balance >= with_draw) {
      balance = balance - with_draw;
      System.out.println(name + " has withdraw : " + with_draw + "\nYour balance : " + balance + "\n");
      try {
        Thread.sleep(50);
      } catch (Exception exception) {}
    } else {
      System.out.println(name + ", You don't have " + with_draw + "\nYour balance : " + balance + "\n");
      try {
        Thread.sleep(50);
      } catch (Exception exception) {}
    }
  }

}

class AccountOverdrawSafeDemo extends Thread //a class to call withdraw method 
{
  Account account;
  String name; //name of varible which is withdrawing the amount

  AccountOverdrawSafeDemo(Account account, String name) //constructor to initialize
  {
    this.account = account;
    this.name = name;
  }

  public void run() //overridding run method of thread class
  {
    int index = 0;
    while (index <= 5) {
      account.withdraw(name, (int)(Math.random() * 1000) + 1); // randomly deducting amount
      index++;
    }
  }

}
class Main {
  public static void main(String[] args) {
    Account account = new Account("Ram & Sita", 1000);
    AccountOverdrawSafeDemo thread1 = new AccountOverdrawSafeDemo(account, "Ram");
    AccountOverdrawSafeDemo thread2 = new AccountOverdrawSafeDemo(account, "Sita");
    thread1.start();
    thread2.start();
  }
}