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
  System.out.println("Technique           Collisions   Time taken(ns)");
  HashingStat hs1= HashMapTester.test(new Chaining(capacity),insertions);  
  System.out.println("Chaining:           "+hs1.numOfCollisions+"         "+hs1.timeTakenInMillis);
  HashingStat hs2= HashMapTester.test(new LinearProbing(capacity),insertions);
  System.out.println("Linear Probing:     "+hs2.numOfCollisions+"         "+hs2.timeTakenInMillis);
  HashingStat hs3= HashMapTester.test(new QuadraticProbing(capacity),insertions);
  System.out.println("Quadratic Probing:  "+hs3.numOfCollisions+"         "+hs3.timeTakenInMillis);
  HashingStat hs4= HashMapTester.test(new DoubleHashing(capacity),insertions);
  System.out.println("Double Hashing   :  "+hs4.numOfCollisions+"         "+hs4.timeTakenInMillis);
 }
}