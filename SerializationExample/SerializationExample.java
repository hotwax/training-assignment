import java.util.Scanner;

public class SerializationExample {
  public static void main(String[] args) {
    try {
      Scanner sc = new Scanner(System.in);

      while (true) {
        System.out.println("Enter 1 to serialize");
        System.out.println("Enter 2 to deserialize");
        System.out.println("Enter 3 to exit the program");
        System.out.println();

        int choice = sc.nextInt();

        switch (choice) {
          case 1:
            System.out.println("Enter the file name: ");
            String fileName = sc.next();
            new SerializationTest().serialize(fileName);
            System.out.println("---------------------------\n");
            break;

          case 2:
            System.out.println("Enter the file name: ");
            fileName = sc.next();
            new DeserializationTest().deserialize(fileName);
            System.out.println("---------------------------\n");
            break;

          case 3:
            System.out.println("---------------------------\n");
            System.out.println("Program terminated successfully.");
            return;

          default:
            System.out.println("---------------------------\n");
            System.out.println("Please enter a valid number");
        }
      }

    } catch (Exception e) {
      System.out.println(e);
    }

  }
}
