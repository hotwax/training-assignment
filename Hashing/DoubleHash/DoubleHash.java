import java.util.Scanner;
import java.util.InputMismatchException;
class Node {
  Integer key;
  Integer value;

  // Constructor 
  Node(int key, int value) {
    this.key = key;
    this.value = value;
  }
}

class DoubleHash {
  int collision = 0;
  private int size;
  private Node[] table;
  private int primeSize;

  /* Constructor */
  public DoubleHash(int tablesize) {
    size = 0;
    table = new Node[tablesize];
    for (int index = 0; index < table.length; index++)
      table[index] = null;
    primeSize = getPrime();
  }

  /* Method to get prime number less than table size for myhash2 function */
  public int getPrime() {
    for (int index = table.length - 1; index >= 1; index--) {
      int fact = 0;
      for (int j_index = 2; j_index <= (int) Math.sqrt(index); j_index++)
        if (index % j_index == 0)
          fact++;
      if (fact == 0)
        return index;
    }
    /* Return a prime number */
    return 3;
  }

  /* Method to get number of key-value pairs */
  public int getSize() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  /* Method to get value of a key */
  public Integer get(Integer key) {
    int hash1 = myhash1(key);
    int hash2 = myhash2(key);
    while (table[hash1] != null && !table[hash1].key.equals(key)) {
      hash1 += hash2;
      hash1 %= table.length;
    }
    if (table[hash1] != null) return table[hash1].value;
    return null;
  }

  /* Method to insert a key value pair */
  public void insert(int key, int value) {
    if (size == table.length) {
      System.out.println("Table full");
      return;
    }
    int hash1 = myhash1(key);
    int hash2 = myhash2(key);
    while (table[hash1] != null) {
      collision++;
      hash1 += hash2;
      hash1 %= table.length;
    }
    table[hash1] = new Node(key, value);
    size++;
  }

  /* Method to remove a key */
  public void remove(int key) {
    int hash1 = myhash1(key);
    int hash2 = myhash2(key);
    while (table[hash1] != null && !table[hash1].key.equals(key)) {
      hash1 += hash2;
      hash1 %= table.length;
    }
    table[hash1] = null;
    size--;
  }

  /* Method myhash which gives a hash value for a given string */
  private int myhash1(Integer key) {
    int hashVal = key.hashCode();
    hashVal %= table.length;
    if (hashVal < 0)
      hashVal += table.length;
    return hashVal;
  }

  /* Method myhash function for double hashing */
  private int myhash2(Integer key) {
    int hashVal = key.hashCode();
    hashVal %= table.length;
    if (hashVal < 0)
      hashVal += table.length;
    return primeSize - hashVal % primeSize;
  }

  /* Method to print hash table */
  public void showAll() {
    for (int index = 0; index < table.length; index++)
      if (table[index] != null)
        System.out.println("Key = " + table[index].key + ", Value = " + table[index].value);
  }

  int getCollisions() {
    return collision;
  }
}
class Main {
  public static void main(String[] args) {
    int value1, value2;
    DoubleHash c = new DoubleHash(5);
    long time1 = System.currentTimeMillis();
    while (true) {
      System.out.println("===========================");
      System.out.println("*****Enter your choice*****");
      System.out.println("1.Put / Update");
      System.out.println("2.Remove");
      System.out.println("3.ShowAll");
      System.out.println("4.Search");
      System.out.println("5.Get collision");
      System.out.println("6.Time");
      System.out.println("7.Exit");
      System.out.println("===========================");
      Scanner sc = new Scanner(System.in);
      try {
        int condition = sc.nextInt();
        switch (condition) {
        case 1:
          System.out.println("Enter key ");
          value1 = sc.nextInt();
          System.out.println("Enter value ");
          value2 = sc.nextInt();
          c.insert(value1, value2);
          System.out.println("Done ");
          break;

        case 2:
          System.out.println("Enter key ");
          value1 = sc.nextInt();
          c.remove(value1);
          System.out.println("Done ");
          break;

        case 3:
          c.showAll();
          break;

        case 4:
          System.out.println("Enter key ");
          value1 = sc.nextInt();
          System.out.println("key=" + value1 + "     Value=" + c.get(value1));
          break;

        case 5:
          System.out.println("No of collision : " + c.getCollisions());
          break;

        case 6:
          long time2 = System.currentTimeMillis();
          System.out.println("Time in mili seconds " + (time2 - time1));
          break;

        case 7:
          System.out.println("Thank you");
          System.exit(0);
          break;

        default:
          System.out.println("Invalid");
          break;
        }
      } catch (InputMismatchException e) {
        System.out.println("Enter valid option");
      }

    }
  }
}