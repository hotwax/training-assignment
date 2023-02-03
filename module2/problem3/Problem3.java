import java.util.Random;

class Account {
  String name;
  static int balance = 1000;

  Account(String name) {
    this.name = name;
  }

  public boolean withdraw(int withdrawalAmount, String person) {
 
    try {
      Thread.sleep(50);
    } catch (InterruptedException e) {
      System.out.println(e);
    }
    

    if (balance >= withdrawalAmount) {
      balance -= withdrawalAmount;
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
  Account acc;
  String person;

  AccountOverdrawDemo(Account acc, String person) {
    this.acc = acc;
    this.person = person;
  }

  Random rm = new Random();

  public void run() {
    int totalWithdrawalAmount=0;

    for (int transaction = 1; transaction <= 22; transaction++) {
      
      // int withdrawalAmount = rm.nextInt(500);
      int withdrawalAmount = 50;
      if(acc.withdraw(withdrawalAmount, person)) totalWithdrawalAmount+=withdrawalAmount;
    }

    System.out.println("Total withdrawal amount: "+ totalWithdrawalAmount);
  }

}

class AccountOverdrawSafeDemo extends Thread{
  String person;
  Account acc;

  AccountOverdrawSafeDemo(Account acc, String name){
    this.person=name;
    this.acc=acc;
  }

  Random rm = new Random();

  public void run(){

    int totalWithdrawalAmount=0;

    for (int transaction = 1; transaction <= 22; transaction++) {
      
      // int withdrawalAmount = rm.nextInt(500);
      int withdrawalAmount = 50;
      synchronized(acc){
        if(acc.withdraw(withdrawalAmount, person)) totalWithdrawalAmount+=withdrawalAmount;
      }
    }

    System.out.println("Total withdrawal amount: "+ totalWithdrawalAmount);
  }
}

public class Problem3 {

  public static void main(String[] args) {
    Account account1 = new Account("account1");
    System.out.println("Current balance: " + account1.getBalance());

    // AccountOverdrawDemo thread1 = new AccountOverdrawDemo(account1, "person1");
    // AccountOverdrawDemo thread2 = new AccountOverdrawDemo(account1, "person2");

    AccountOverdrawSafeDemo thread1 = new AccountOverdrawSafeDemo(account1, "person1");
    AccountOverdrawSafeDemo thread2 = new AccountOverdrawSafeDemo(account1, "person2");

    thread1.start();
    thread2.start();
  }

}