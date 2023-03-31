class Account {
  String name;
  static int balance = 1000; 

  Account(String name) {
    this.name = name;
  }

  void setInitialBalance(){
    balance=1000;
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
      System.out.println(person+ " Withdrawal amount: "+withdrawalAmount+" -----"+ " Current balance: " + balance);
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
