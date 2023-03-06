package Account;

public class Account {
    private int balance = 1000;

    Account() {
    }

    int getbalance() {
        return this.balance;
    }

    void Withdraw(int withdraw, String name) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.getStackTrace();
        }
        if (balance < withdraw) {
            System.out.println(name + "Account is Overdrawn");
            return;
        }

        int temp = balance - withdraw;

        System.out.println(name + " completed withdraw " + withdraw + "and Awailable Balance is " + temp);

        balance = temp;
    }

}
