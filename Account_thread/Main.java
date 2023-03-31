import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

      
      
      while (true) {
        System.out.println();
        System.out.println("Enter 1 to withdraw amount in unsafe manner");
        System.out.println("Enter 2 to withdraw amount in safe manner");
        System.out.println("Enter 3 to exit the program");
        System.out.println("Enter Your Choice");

        try {
          Scanner sc = new Scanner(System.in);
          int choice = sc.nextInt();
          switch (choice) {
            case 1:
              AccountOverdrawDemo obj1 = new AccountOverdrawDemo();
              obj1.Operations();
              break;

            case 2:
              AccountOverdrawSafeDemo obj2 = new AccountOverdrawSafeDemo();
              obj2.Operations();
              break;

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

  }

}