import java.util.Arrays;
import java.util.Scanner;

public class RandomProbing {

  static int[] keys;
  static int[] vals;
  static int currentSize = 0, maxSize = 0;
  static int noOfCollisions = 0;

  static int randomNum = 0;

  static void initialize(int size) {
    maxSize = size;
    keys = new int[size];
    vals = new int[size];

    randomNum = (int) (Math.random() * maxSize) % maxSize;

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

    int pointer = hash(key);
    int initialHashValue = pointer;

    do {
      if (keys[pointer] == Integer.MAX_VALUE) {
        keys[pointer] = key;
        vals[pointer] = value;
        currentSize++;
        return;
      } else if (keys[pointer] == key) {
        vals[pointer] = value;
        return;
      } else {
        pointer = (pointer + 1 + randomNum) % maxSize;
        noOfCollisions++;
      }

    } while (pointer != initialHashValue);
  }

  static int hash(int key) {
    if (key < 0)
      key += maxSize;
    return key % maxSize;
  }

  static void remove(int key) {
    if (get(key) == Integer.MAX_VALUE) {
      System.out.println(key + " doesn't exists.");
      return;
    }

    // remove
    int pointer = hash(key);
    while (keys[pointer] != key) {
      pointer = (pointer + 1 + randomNum) % maxSize;
    }
    keys[pointer] = vals[pointer] = Integer.MAX_VALUE;

    // rehash
    pointer = (pointer + 1 + randomNum) % maxSize;
    do {
      if (keys[pointer] != Integer.MAX_VALUE) {
        int temp1 = keys[pointer], temp2 = vals[pointer];
        keys[pointer] = vals[pointer] = Integer.MAX_VALUE;
        currentSize--;
        add(temp1, temp2);
      }
      pointer = (pointer + 1 + randomNum) % maxSize;

    } while (keys[pointer] != Integer.MAX_VALUE);

  }

  static int get(int key) {
    int pointer = hash(key);
    while (keys[pointer] != Integer.MAX_VALUE) {
      if (keys[pointer] == key) {
        return vals[pointer];
      }
      pointer = (pointer + 1 + randomNum) % maxSize;
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
      initialize(capacity);

      while (true) {
        System.out.print("Your hash table: ");
        display();
        System.out.println();

        System.out.println("Enter 1 to add an element");
        System.out.println("Enter 2 to remove an element");
        System.out.println("Enter 3 to search an element");
        System.out.println("Enter 4 to check whether an element is present");
        System.out.println("Enter 5 to find number of collisions");

        System.out.println("Note- The data type of key and value is int.");

        int choice = sc.nextInt();
        int key;
        int value;

        switch (choice) {
          case 1:
            System.out.println("Enter the key: ");
            key = sc.nextInt();
            System.out.println("Enter the value: ");
            value = sc.nextInt();
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

          default:
            System.out.println("Program terminated successfully.");
            System.out.println("-------------------------------------");
            return;
        }

      }

    } catch (Exception e) {

    }

  }
}