public class Account {
    public int balance = 1000;


    // Method to withdraw money from the account
    public void withdraw(String name, int amount) {
        balance -= amount;
        System.out.println(name + " withdraws " + amount + " and has " + balance + " left");
    }

}
