package Threading;

public class Account {

    private String name;
    private static double balance = 1000;

    //constructor
    public Account(String name) {
        this.name = name;
    }

    //method to withdraw amount
    public double withdraw(int amount) {
        //i have added sleep to get more concurrent chances for both thread to acces the account at the same time
        //which ultimately cass more overdraw of bank account, which demonstrate the necessacity of Synchronized transaction.
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        //if the account has money greater than or equal to amount entered by user to withdwaw then return that ammount
        if (amount <= balance) {
            //reduce that amount from total balance
            balance -= amount;
            return amount;
        } else //else return 0
        {
            return 0;
        }

    }

    //method to get the current remaining balance.
    public double getBalance() {
        return balance;
    }
}
