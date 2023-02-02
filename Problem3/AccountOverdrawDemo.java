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
      System.out.println(name + ", You don't have " + with_draw + "\nYour balance : " + balance + "\n");
      //making a thread to sleep so that the thread are executed one by one
      try {
        Thread.sleep(50);
      } catch (Exception e) {}
    } else {
      //making a thread to sleep so that the thread are executed one by one
      System.out.println(name + ", You don't have " + with_draw + "\nYour balance : " + balance + "\n");
      try {
        Thread.sleep(50);
      } catch (Exception e) {}
    }

  }

}
class AccountOverdrawDemo extends Thread //a class to call withdraw method
{
  Account a;
  String name; //name of varible which is withdrawing the amount

  AccountOverdrawDemo(Account a, String name) //constructor to initialize
  {
    this.a = a;
    this.name = name;
  }

  public void run() //overridding run method of thread class
  {
    int i = 0;
    while (i <= 5) {
      a.withdraw(name, (int)(Math.random() * 1000) + 1); // randomly deducting amount
      i++;
    }
  }

}
class Demo {
  public static void main(String[] args) {
    Account a = new Account("Ram & Sita", 1000);
    AccountOverdrawDemo t1 = new AccountOverdrawDemo(a, "Ram");
    AccountOverdrawDemo t2 = new AccountOverdrawDemo(a, "Sita");
    t1.start();
    t2.start();
  }
}