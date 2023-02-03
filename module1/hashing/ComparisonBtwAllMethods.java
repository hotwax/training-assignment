import java.util.Random;

public class ComparisonBtwAllMethods{
  public static void main(String[] args) {
    
    int[] inputArray = new int[50000];
    Random random = new Random();
    for (int i = 0; i < inputArray.length; i++) {
      inputArray[i]=random.nextInt(1000000);
    }
  
    System.out.println("Chaining: \n"+ Chaining.comparison(inputArray));
    System.out.println("---------------------------------- \n");
    System.out.println("Linear Probing: \n"+ LinearProbing.comparison(inputArray));
    System.out.println("---------------------------------- \n");
    System.out.println("Quadratic Probing: \n"+ QuadraticProbing.comparison(inputArray));
    System.out.println("---------------------------------- \n");
    System.out.println("Double Hashing: \n"+ DoubleHashing.comparison(inputArray));
    System.out.println("---------------------------------- \n");
    System.out.println("Random Probing: \n"+ RandomProbing.comparison(inputArray));
    System.out.println("---------------------------------- \n");
    
  }
}