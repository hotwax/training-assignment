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
      } catch (Exception e) {}
    } else {
      System.out.println(name + ", You don't have " + with_draw + "\nYour balance : " + balance + "\n");
      try {
        Thread.sleep(50);
      } catch (Exception e) {}
    }
  }

}

class AccountOverdrawSafeDemo extends Thread //a class to call withdraw method 
{
  Account a;
  String name; //name of varible which is withdrawing the amount

  AccountOverdrawSafeDemo(Account a, String name) //constructor to initialize
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
    Account ob = new Account("Ram & Sita", 1000);
    AccountOverdrawSafeDemo t1 = new AccountOverdrawSafeDemo(ob, "Ram");
    AccountOverdrawSafeDemo t2 = new AccountOverdrawSafeDemo(ob, "Sita");
    t1.start();
    t2.start();
  }
}