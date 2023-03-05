
public class Account {

    String name;
    Integer balance;

    Account(String name) {
        this.name = name;
        this.balance = 1000;
    }
    

    public void withdraw(Integer amount, String name) throws InterruptedException {
        // Thread.sleep(100); // Simulate time taken for the withdraw process

        if(this.balance < amount) {
            System.out.println(name + " does not have enough money to withdraw " + amount);
            return;
        }
        
        this.balance -= amount;
        System.out.println(name + " withdrew " + amount + " and now has " + this.balance);
    }


    public synchronized void synchronisedWithdraw(Integer amount, String name) throws InterruptedException {
        if (balance >= amount) {
          try {
            Thread.sleep(100); // Simulate time taken for the withdraw process
          } catch (InterruptedException ex) { }
          balance -= amount;
          System.out.println(name + " completes the withdrawal of " + amount + " and now has " + balance);
        } else {
          System.out.println(name + " does not have enough money to withdraw " + amount);
        }
      }
    

    public static void main(String[] args) {
        System.out.println("here");
    }

}

