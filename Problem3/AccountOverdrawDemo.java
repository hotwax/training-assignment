class Account {
  String name; //name of joint account
  int balance; // the balance that is present

  Account(String name, int balance) {
    this.balance = balance;
    this.name = name;
  }

  void withdraw(String name, int with_draw) //method to deduct cash but here the amount can be negative
  {

    if (balance >= with_draw) {
      balance = balance - with_draw;
      System.out.println(name + " has withdraw : " + with_draw + "\nYour balance : " + balance + "\n");
      //making a thread to sleep so that the thread are executed one by one
      try {
        Thread.sleep(50);
      } catch (Exception exception) {}
    } else {
      //making a thread to sleep so that the thread are executed one by one
      System.out.println(name + ", You don't have " + with_draw + "\nYour balance : " + balance + "\n");
      try {
        Thread.sleep(50);
      } catch (Exception exception) {}
    }

  }

}
class AccountOverdrawDemo extends Thread //a class to call withdraw method
{
  Account account;
  String name; //name of varible which is withdrawing the amount

  AccountOverdrawDemo(Account account, String name) //constructor to initialize
  {
    this.account = account;
    this.name = name;
  }

  public void run() //overridding run method of thread class
  {
    int index	= 0;
    while (index <= 5) {
      account.withdraw(name, (int)(Math.random() * 1000) + 1); // randomly deducting amount
      index++;
    }
  }

}
class Demo {
  public static void main(String[] args) {
    Account account = new Account("Ram & Sita", 1000);
    AccountOverdrawDemo thread1 = new AccountOverdrawDemo(account, "Ram");
    AccountOverdrawDemo thread2 = new AccountOverdrawDemo(account, "Sita");
    thread1.start();
    thread2.start();
  }
}