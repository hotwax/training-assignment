import java.util.Scanner;
public class Main
{
 static Scanner scanner= new Scanner(System.in);
 public static void main(String args[])
 {
  System.out.println("Enter size of hashTables:");
  int capacity = scanner.nextInt();
  System.out.println("Enter number of Insertions:");
  int insertions= scanner.nextInt();
  System.out.println("Comuting for all collisions techniques:");
  HashingStat hs1= new Chaining(capacity).test(insertions);
  System.out.println("Chaining: Collisions="+hs1.numOfCollisions+" Time taken="+hs1.timeTakenInMillis+" ms");
  HashingStat hs2= new LinearProbing(capacity).test(insertions);
  System.out.println("Linear Probing: Collisions="+hs2.numOfCollisions+" Time taken="+hs2.timeTakenInMillis+" ms");
  HashingStat hs3= new QuadraticProbing(capacity).test(insertions);
  System.out.println("Quadratic Probing: Collisions="+hs3.numOfCollisions+" Time taken="+hs3.timeTakenInMillis+" ms");
 }
}