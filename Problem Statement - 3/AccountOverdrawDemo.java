class Account {

    // data members --
    private String name; // Account name
    private int balance = 1000; // Account Balance

    // constructor --
    Account(String name, int balance) {

        // Initializing members
        this.name = name;
        this.balance = balance;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    // function to withdraw amount
    public int withdraw(int amountToWithdraw) {
        // allowing user to withdraw amount only if possible
        if (this.balance - amountToWithdraw >= 0) {
            this.balance = this.balance - amountToWithdraw;
            return amountToWithdraw;
        }
        return 0;
    }

}

public class AccountOverdrawDemo {

    public static int amountWithdrawenByPerson1 = 0;
    public static int amountWithdrawenByPerson2 = 0;

    public static void main(String args[]) {

        // Creating account object and initializing the balance with 1000
        Account accountHolder = new Account("Joint Account", 1000);

        // Creating Anonymous Objects which withdraws the amount from the account object
        Runnable ATMToWithdraw1 = () -> {
            for (int index = 0; index < 1000; index++) {
                try {
                    Thread.sleep(2);
                } catch (InterruptedException exp) {
                    // TODO: handle exception
                    System.out.println("Exception Occured : " + exp);
                }
                amountWithdrawenByPerson1 += accountHolder.withdraw(30);
            }
        };

        Runnable ATMToWithdraw2 = () -> {
            for (int index = 0; index < 1000; index++) {
                try {
                    Thread.sleep(2);
                } catch (InterruptedException exp) {
                    // TODO: handle exception
                    System.out.println("Exception Occured : " + exp);
                }
                amountWithdrawenByPerson2 += accountHolder.withdraw(40);
            }
        };

        // Creating threads of these Runnable Objects --

        Thread thread1 = new Thread(ATMToWithdraw1);
        Thread thread2 = new Thread(ATMToWithdraw2);

        // Strating the threads
        // start() method causes this thread to begin execution

        thread1.start();
        thread2.start();

        try {
            // Join thread
            // Join method waits thread to die
            thread1.join();
            thread2.join();
        } catch (InterruptedException exp) {
            // Handling the Exception
            System.out.println("Exception Occured : " + exp);
        }

        // Final printings
        System.out.println("Total Account balance was : 1000");
        System.out.println("Total Account balance Now : " + accountHolder.getBalance());
        System.out.println("Amount Withdrawn By person 1 : " + amountWithdrawenByPerson1);
        System.out.println("Amount Withdrawn By person 2 : " + amountWithdrawenByPerson2);

    }
}
