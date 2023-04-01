import java.util.Arrays;
import java.util.Scanner;
import java.util.InputMismatchException;

public class DoubleHashing {

  static int[] keys;
  static int[] vals;
  static int currentSize = 0, maxSize = 0;
  static int noOfCollisions = 0;

  static void initialize(int size) {
    maxSize = size;
    keys = new int[size];
    vals = new int[size];

    Arrays.fill(keys, Integer.MAX_VALUE);
    Arrays.fill(vals, Integer.MAX_VALUE);
  }

  static void display() {
    for (int i = 0; i < keys.length; i++) {
      if (keys[i] != Integer.MAX_VALUE)
        System.out.print(keys[i] + " " + vals[i] + ", ");
    }
  }

  static void add(int key, int value) {

    if (currentSize == maxSize) {
      System.out.println("array size is full");
      return;
    }

    int hashValue1 = hash1(key), hashValue2 = hash2(key);
    int initialHashValue = hashValue1;

    do {
      if (keys[hashValue1] == Integer.MAX_VALUE) {
        keys[hashValue1] = key;
        vals[hashValue1] = value;
        currentSize++;
        return;
      } else if (keys[hashValue1] == key) {
        vals[hashValue1] = value;
        return;
      } else {
        hashValue1 = (hashValue1 + hashValue2) % maxSize;
        noOfCollisions++;
      }

    } while (hashValue1 != initialHashValue);
  }

  static int hash1(int key) {
    if (key < 0)
      key += maxSize;
    return key % maxSize;
  }

  static int hash2(int key) {
    if (key < 0)
      key += maxSize;
    key = key % maxSize;
    return (prime - (key % prime));
  }

  static int prime = justSmallerPrimeNum();

  static int justSmallerPrimeNum() {
    for (int num = maxSize - 1; num >= 1; num--) {
      if (isPrime(num))
        return num;
    }

    return 1;
  }

  public static boolean isPrime(int n) { // ts - sqrt(n)
    for (int div = 2; div * div <= n; div++) { // div <= root(n)
      if (n % div == 0) {
        return false;
      }
    }
    return true;
  }

  static void remove(int key) {
    if (get(key) == Integer.MAX_VALUE)
      return;

    int hashValue1 = hash1(key), hashValue2 = hash2(key);

    // remove
    while (keys[hashValue1] != key) {
      hashValue1 = (hashValue1 + hashValue2) % maxSize;
    }
    keys[hashValue1] = vals[hashValue1] = Integer.MAX_VALUE;

    // rehash
    hashValue1 = (hashValue1 + hashValue2) % maxSize;
    do {
      if (keys[hashValue1] != Integer.MAX_VALUE) {
        int temp1 = keys[hashValue1], temp2 = vals[hashValue1];
        keys[hashValue1] = vals[hashValue1] = Integer.MAX_VALUE;
        currentSize--;
        add(temp1, temp2);
      }
      hashValue1 = (hashValue1 + hashValue2) % maxSize;

    } while (keys[hashValue1] != Integer.MAX_VALUE);

  }

  static int get(int key) {
    int hashValue1 = hash1(key), hashValue2 = hash2(key);

    while (keys[hashValue1] != Integer.MAX_VALUE) {
      if (keys[hashValue1] == key) {
        return vals[hashValue1];
      }
      hashValue1 = (hashValue1 + hashValue2) % maxSize;
    }
    return Integer.MAX_VALUE;
  }

  static String comparison(int[] arr) {
    initialize(50000);
    long startTime = System.currentTimeMillis();
    for (int i = 0; i < arr.length; i++) {
      add(arr[i], arr[i]);
    }
    long endTime = System.currentTimeMillis();
    long timeTaken = endTime - startTime;
    return "No of collisions: " + noOfCollisions + " Time taken: " + timeTaken;
  }

  public static void main(String[] args) {

    long startingTime = System.currentTimeMillis();

    try {
      System.out.println("Initialize capacity of array: ");
      Scanner sc = new Scanner(System.in);
      int capacity = sc.nextInt();
      if (capacity == 0) {
        System.out.println("Please enter a valid (>0) size.");
        return;
      }
      initialize(capacity);

      while (true) {
        System.out.println("Your hash map: ");
        display();
        System.out.println();

        System.out.println("Enter 1 to add an element");
        System.out.println("Enter 2 to remove an element");
        System.out.println("Enter 3 to search an element");
        System.out.println("Enter 4 to check whether an element is present");
        System.out.println("Enter 5 to find number of collisions");
        System.out.println("Enter 6 to exit the program");

        System.out.println("Note- The data type of key and value is int.");

        int choice = sc.nextInt();
        int key;
        String val;

        switch (choice) {
          case 1:
              System.out.println("Enter the key: ");
              key = sc.nextInt();
              System.out.println("Enter the value: ");
              int value = sc.nextInt();
              add(key, value);
              long endingTime = System.currentTimeMillis();
              System.out.println("Time taken: " + (endingTime - startingTime) + " ms");
              System.out.println("-----------------------------------");
            
            break;

          case 2:
            System.out.println("Enter the key: ");
            key = sc.nextInt();
            remove(key);
            System.out.println("-----------------------------------");
            break;

          case 3:
            System.out.println("Enter the key: ");
            key = sc.nextInt();
            if (get(key) == Integer.MAX_VALUE)
              System.out.println("Doesn't exists");
            else
              System.out.println(get(key));
            System.out.println("-----------------------------------");
            break;

          case 4:
            System.out.println("Enter the key: ");
            key = sc.nextInt();
            if (get(key) == Integer.MAX_VALUE)
              System.out.println("Doesn't exists");
            else
              System.out.println("Exists");
            System.out.println("-----------------------------------");
            break;

          case 5:
            System.out.println(noOfCollisions);
            System.out.println("-------------------------------------");

            break;

          case 6:
            System.out.println("Program terminated successfully.");
            System.out.println("-------------------------------------");
            return;

          default:
            System.out.println("Please enter a valid choice (1,2,3,4,5,6).");
            System.out.println("-------------------------------------");
            break;
        }

      }

    } catch (InputMismatchException e) {
      System.out.println("Please enter a valid number. " + e);
    }catch (Exception e) {
      System.out.println(e);
    }

  }
}