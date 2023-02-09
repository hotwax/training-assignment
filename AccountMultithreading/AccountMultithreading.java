class Account {
  String name;
  static int balance = 1000; 

  Account(String name) {
    this.name = name;
  }

  public boolean withdraw(int withdrawalAmount, String person) {
 
    // making the thread sleep for 100 milliseconds so that other thread can access the resource
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      System.out.println(e);
    }
    

    if (balance >= withdrawalAmount) {
      balance -= withdrawalAmount; // decrease the balance by withdrawalAmount
      System.out.println(person+ " Withdrawal amount: "+withdrawalAmount+ " Current balance: " + balance);
      return true;
    } else {
      System.out.println(person + ", you don't have sufficient balance.");
      return false;
    }

  }

  public int getBalance() {
    return balance;
  }
}

class AccountOverdrawDemo extends Thread {
  Account account;
  String person;

  AccountOverdrawDemo(Account account, String person) {
    this.account = account;
    this.person = person;
  }

  public void run() {
    int totalWithdrawalAmount=0;

    for (int transaction = 1; transaction <= 20; transaction++) {  //20 because 1000/50=20
      
      int withdrawalAmount = 50;
      if(account.withdraw(withdrawalAmount, person)) totalWithdrawalAmount+=withdrawalAmount;
    }

    System.out.println("Total withdrawal amount: "+ totalWithdrawalAmount);
    
  }

}

class AccountOverdrawSafeDemo extends Thread{
  String person;
  Account account;

  AccountOverdrawSafeDemo(Account account, String name){
    this.person=name;
    this.account=account;
  }

  public void run(){

    int totalWithdrawalAmount=0;

    for (int transaction = 1; transaction <= 20; transaction++) {
      
      int withdrawalAmount = 50;
      synchronized(account){
        if(account.withdraw(withdrawalAmount, person)) totalWithdrawalAmount+=withdrawalAmount;
      }
    }

    System.out.println("Total withdrawal amount: "+ totalWithdrawalAmount);
  }
}

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