// class Account 
public class Account {
    // data members
    String owner;
    Integer available_balance;
    
    // constructor
    
    Account(String name) {
        this.owner = name;
        // initializing available_balance with the value 1000 according to the problem statement
        this.available_balance = 1000;
    }
    

    public void withdraw(Integer amount, String name) throws InterruptedException {
        // if the requested amount is greater than the balance in account 
        if(amount>available_balance)
        System.out.println("Balance Insufficient");
        // else if the requested amount is less than the current balance in the account deduct that amount
        else{
        this.available_balance -= amount;
        System.out.println(name + " has withdrawn " + amount + " rupees, remaining balance is " + this.available_balance);
        }
    }

    // synchronized method to withdraw money
    public synchronized void synchronisedWithdraw(Integer amount, String name) throws InterruptedException {
        // if the requested amount is available
        if (amount <=available_balance) {
          try {
            Thread.sleep(100); 
          } catch (InterruptedException ex) { }
          available_balance -= amount;
          System.out.println(name + " has withdrawn " + amount + " rupees, remaining balance is " + this.available_balance);
        } 
        // else if the requested amount is more than the the current balance in the account
        else {
          System.out.println("Balance Insufficient");
        }
      }
    
}