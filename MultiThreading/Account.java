package MultiThreading;

//Account class
public class Account {
    private int balance;
    private int totalWithdrawnAmount;

    // constructor
    Account(int balance) {
        this.balance = balance;
        this.totalWithdrawnAmount = 0;
    }

    // get the current balance
    int getbalance() {
        return this.balance;
    }

    // get the total Withdrawal amount
    int getTotalWithdrawn() {
        return this.totalWithdrawnAmount;
    }

    // withdraw the amount
    void Withdrawn(int withdrawnAmount, String name) {
        try {
            Thread.sleep(1000); // Adding a 1 second delay to simulate withdrawal process
        } catch (InterruptedException exception) {
            System.out.println(exception); // Catching InterruptedException and printing the exception message
        } catch (Exception exception) {
            System.out.println(exception); // Catching all other exceptions and printing the exception message
        }
        if (balance < withdrawnAmount) { // Checking if the account balance is sufficient for withdrawal
            System.out.println(name + " Account does not have enough balance to withdraw");                                                                          // insufficient
            return;
        }
        int remainingBalance = balance - withdrawnAmount; // Deducting the withdrawal amount from the account balance
        totalWithdrawnAmount += withdrawnAmount; // Adding the withdrawal amount to the total withdrawn amount
        System.out.println(name + " completed withdraw " + withdrawnAmount + " and Available Balance is " + remainingBalance);                                                                                                              // balance
        balance = remainingBalance; // Updating the account balance
    }
}
