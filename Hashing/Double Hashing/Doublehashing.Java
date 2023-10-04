
// import necessary Packages
import java.util.InputMismatchException;
import java.util.Scanner;

// class Doublehashing
public class DoubleHashing {
  private int capacity;
  private int[] keys;
  private int[] values;
  private int size;
  int numCollisions;

  public DoubleHashing(int capacity) {
    this.capacity = capacity;
    this.keys = new int[capacity];
    this.values = new int[capacity];
    this.size = 0;
    this.numCollisions = 0;
  }

  public int hash1(int key) {
    return key % capacity;
  }

  public int hash2(int key) {
    return 2 - (key % 2); // 2 is a prime number smaller than the capacity
  }

  // Inserting key and Value if capacity is full it prints Hashmap is full
  public void put(int key, int value) {
    if (size == capacity) {
      System.out.println("Hashmap is full");
      return;
    }

    int index = hash1(key);
    int offset = hash2(key);

    while (keys[index] != 0 && keys[index] != key) {
      index = (index + offset) % capacity;
      numCollisions++;
    }

    if (keys[index] == key) {
      values[index] = value; // updating existing value
    } else {
      keys[index] = key;
      values[index] = value;
      size++;
    }
  }

  // get the value
  public int get(int key) {
    int index = hash1(key);
    int offset = hash2(key);

    while (keys[index] != 0 && keys[index] != key) {
      index = (index + offset) % capacity;
      numCollisions++;
    }

    if (keys[index] == key) {
      System.out.println("value of " + key + " is " + values[index]);
      return values[index];
    } else {
      System.out.println("key not found");
      return -1; // return -1 if key is not found
    }
  }

  // display to print the Hashmap elements
  public void display() {
    System.out.println("Hashmap ");
    for (int index = 0; index < capacity; index++) {
      if (keys[index] != 0) {
        System.out.println(keys[index] + " => " + values[index]);
      }
    }
  }

  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
      System.out.print("Enter capacity of Hashmap: ");
      int capacity = scanner.nextInt();
      DoubleHashing hashmap = new DoubleHashing(capacity);
      int choice;
      do {
        System.out.println("1. Insert element");
        System.out.println("2. Get element");
        System.out.println("3. Display Hashmap");
        System.out.println("4.Number of collision");
        System.out.println("5.Exit");
        System.out.print("Enter choice: ");
        choice = scanner.nextInt();
        switch (choice) {
          case 1:
            System.out.print("Enter key:");
            int key = scanner.nextInt();
            System.out.print("Enter value:");
            int value = scanner.nextInt();
            hashmap.put(key, value);
            break;
          case 2:
            System.out.print("Enter key: ");
            key = scanner.nextInt();
            hashmap.get(key);
            break;
          case 3:
            hashmap.display();
            break;
          case 4:
            System.out.println("collision - " + hashmap.numCollisions);
            break;
          case 5:
            System.out.println("Exit");
            break;
          default:
            System.out.println("Invalid choice");
            break;
        }
      } while (choice != 5);
    } catch (InputMismatchException ex) {
      System.out.println(ex);
    } catch (Exception exception) {
      System.out.println(exception);
    }
  }
}