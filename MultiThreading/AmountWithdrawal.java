package MultiThreading;

//Importing required libraries
import java.util.InputMismatchException;
import java.util.Scanner;

//Amount withdrawal class
public class AmountWithdrawal {
    public static void main(String args[]) {
        // Creating scanner object and handling resources with try-with-resources
        try (Scanner input = new Scanner(System.in)) {
            // Taking user input for balance, withdrawal amount, and number of withdrawals
            System.out.println("Enter the total balance of account -");
            int balance = input.nextInt();
            System.out.println("Enter the withdrawal amount -");
            int amount = input.nextInt();
            System.out.println("Enter the number of withdrawal for each customers");
            int numberOfWithdrawal = input.nextInt();
            String choices;
            do {
                // Printing menu options
                System.out.println();
                System.out.println("Menu options");
                System.out.println("1.Account is Overdrawn");
                System.out.println("2.Account is safe to Overdrawn");
                System.out.println("3.Exit");
                // Taking user choice for options
                choices = input.next();
                switch (choices) {
                    case "1":
                        try {
                            // Creating AccountOverDrawn object and starting threads for withdrawal
                            AccountOverDrawn accountOverDraw = new AccountOverDrawn(numberOfWithdrawal, amount,balance);
                            accountOverDraw.thread1.start();
                            accountOverDraw.thread2.start();
                            // Waiting for threads to finish
                            while (accountOverDraw.thread2.isAlive() || accountOverDraw.thread1.isAlive()) {
                                Thread.sleep(1);
                            }
                            System.out.println();
                            System.out.println("Total Withdrawn - " + accountOverDraw.account.getTotalWithdrawn());
                        } catch (InterruptedException exception) {
                            System.out.println(exception);
                        } catch (Exception exception) {
                            System.out.println(exception);
                        }
                        break;

                    case "2":
                        try {
                            // Creating AccountSafe object and starting threads for withdrawal
                            AccountSafe accountSafe = new AccountSafe(numberOfWithdrawal, amount, balance);
                            accountSafe.thread1.start();
                            accountSafe.thread2.start();
                            // Waiting for threads to finish
                            while (accountSafe.thread2.isAlive() || accountSafe.thread1.isAlive()) {
                                Thread.sleep(1);
                            }
                            System.out.println();
                            System.out.println("Total Withdrawn - " + accountSafe.account.getTotalWithdrawn());
                        } catch (InterruptedException exception) {
                            System.out.println(exception);
                        } catch (Exception exception) {
                            System.out.println(exception);
                        }
                        break;
                    case "3":
                        // Exiting the program
                        System.out.println("Thank you");
                        break;
                    default:
                        // Handling invalid choice
                        System.out.println("invalid choice");
                }
            } while (!choices.equals("3"));
        } catch (InputMismatchException exception) {
            // Handling input mismatch exception
            System.out.println(exception);
        } catch (Exception exception) {
            // Handling other exceptions
            System.out.println(exception);
        }
    }
}