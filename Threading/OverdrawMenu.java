import java.util.Scanner;

public class OverdrawMenu {

    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            // Display menu options
            System.out.println("Please select an option:");
            System.out.println("1. Run AccountOverdrawDemo");
            System.out.println("2. Run AccountOverdrawSafeDemo");
            System.out.println("3. Exit");

            // Get user input
            int choice = scanner.nextInt();

            // Execute chosen option
            switch (choice) {
                case 1:
                    System.out.println("Running AccountOverdrawDemo...\n");
                    AccountOverdrawDemo.main(args);
                    Thread.sleep(5000);
                    break;
                case 2:
                    System.out.println("Running AccountOverdrawSafeDemo...\n");
                    AccountOverdrawSafeDemo.main(args);
                    Thread.sleep(5000);
                    break;
                case 3:
                    System.out.println("Exiting program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println(); // print a blank line for readability

        }

    }

}
