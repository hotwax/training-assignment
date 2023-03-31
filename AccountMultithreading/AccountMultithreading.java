import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountMultithreading {

  public static void main(String[] args) {

    try {
      System.out.println("Enter 1 to withdraw amount in unsafe manner");
      System.out.println("Enter 2 to withdraw amount in safe manner");
      System.out.println("Enter 3 to exit the program");
      System.out.println();

      while (true) {

        Account account1 = new Account("account1");
        account1.setInitialBalance();
        System.out.println("Current balance: " + account1.getBalance() + "\n");

        try {
          Scanner sc = new Scanner(System.in);
          int choice = sc.nextInt();
          switch (choice) {
            case 1:
              AccountOverdrawDemo thread1Overdraw = new AccountOverdrawDemo(account1, "person1");
              AccountOverdrawDemo thread2Overdraw = new AccountOverdrawDemo(account1, "person2");
              thread1Overdraw.start();
              thread2Overdraw.start();
              System.out.println("\n---------------------------\n");
              break;

            case 2:
              AccountOverdrawSafeDemo thread1OverdrawSafe = new AccountOverdrawSafeDemo(account1, "person1");
              AccountOverdrawSafeDemo thread2OverdrawSafe = new AccountOverdrawSafeDemo(account1, "person2");
              thread1OverdrawSafe.start();
              thread2OverdrawSafe.start();
              System.out.println("\n---------------------------\n");
              break;

            // in using AccountOverdrawDemo, the sum of totalWithdrawalAmount of thread1 and
            // thread2 will be > 1000
            // which is the scenario we need to handle.
            // in using AccountOverdrawSafeDemo, the sum of totalWithdrawalAmount of thread1
            // and thread2 will always be = 1000
            // by using synchronized block.

            case 3:
              System.out.println("---------------------------\n");
              System.out.println("Program terminated successfully.");
              return;

            default:
              System.out.println("---------------------------\n");
              System.out.println("Please enter a valid number.\n");
          }
        } catch (InputMismatchException e) {
          System.out.println("Please enter a valid input.");
        }
      }
    } catch (Exception e) {
      System.out.println(e);
    }

  }

}