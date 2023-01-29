import java.util.Scanner;
import java.util.InputMismatchException;
// Node for singly linked list 
class Node {
  Node next;
  int key;
  int value;

  // Constructor 
  public Node(int x, int y) {
    key = x;
    value = y;
    next = null;
  }
}

class Chaining {
  int collision = 0;
  private Node[] table;
  private int size;

  //  Constructor 
  public Chaining(int tableSize) {
    table = new Node[tableSize];
    size = 0;
  }

  // Method to check if hash table is empty 
  public boolean isEmpty() {
    return size == 0;
  }

  //  Method to get size 
  public int getSize() {
    return size;
  }

  // Method to insert an element 
  public void put(int key, int val) {
    size++;
    int pos = hash(key);
    Node nptr = new Node(key, val);
    if (table[pos] == null)
      table[pos] = nptr;
    else {
      nptr.next = table[pos];
      table[pos] = nptr;
      collision++;
    }
  }

  // Method to remove an element 
  public void remove(int val) {
    int pos = hash(val);
    Node start = table[pos];
    Node end = start;
    if (start.key == val) {
      size--;
      table[pos] = start.next;
      return;
    }
    while (end.next != null && end.next.key != val)
      end = end.next;
    if (end.next == null) {
      System.out.println("Element not found");
      return;
    }
    size--;
    if (end.next.next == null) {
      end.next = null;
      return;
    }
    end.next = end.next.next;
    table[pos] = start;
  }

  // Method hash to genrate hashcode 
  private int hash(Integer x) {
    // int hashVal = x%17;
    int hashVal = x.hashCode();
    hashVal %= table.length;
    if (hashVal < 0)
      hashVal += table.length;
    return hashVal;
  }

  //method to print the key value pair
  public void showAll() {
    for (int i = 0; i < table.length; i++) {
      Node start = table[i];
      if (start != null) {
        System.out.print("table " + i + "=> ");
      }
      while (start != null) {
        System.out.print("{ Key = " + start.key + ", Value = " + start.value + "} ");
        start = start.next;
      }
      System.out.println();
    }
  }

  //method to get value for a key
  public void get(int key) {
    boolean flag = true;
    for (int i = 0; i < table.length; i++) {
      Node start = table[i];
      while (start != null) {
        if (start.key == key) {
          System.out.println("key = " + start.key + " Value = " + start.value);
          flag = false;
        }
        start = start.next;
      }
    }
    if (flag)
      System.out.println("key = " + key + " Value = null");

    System.out.println();
  }

  // to count number of collision
  public int getCollisions() {
    return collision;
  }
}

class Demo {
  public static void main(String[] args) {
    int a, b;
    Chaining c = new Chaining(5);
    long l1 = System.currentTimeMillis();
    while (true) {
      System.out.println("===========================");
      System.out.println("*****Enter your choice*****");
      System.out.println("1.Put / Update");
      System.out.println("2.Remove");
      System.out.println("3.ShowAll");
      System.out.println("4.Search");
      System.out.println("5.Get collision");
      System.out.println("6.Get Time ");
      System.out.println("7.Exit");
      System.out.println("===========================");

      Scanner sc = new Scanner(System.in);
      try {
        int x = sc.nextInt();
        switch (x) {
        case 1:
          System.out.println("Enter key ");
          a = sc.nextInt();
          System.out.println("Enter value ");
          b = sc.nextInt();
          c.put(a, b);
          System.out.println("Done ");
          break;

        case 2:
          System.out.println("Enter key ");
          a = sc.nextInt();
          c.remove(a);
          System.out.println("Done ");
          break;

        case 3:
          c.showAll();
          break;

        case 4:
          System.out.println("Enter key ");
          a = sc.nextInt();
          c.get(a);
          break;

        case 5:
          System.out.println("No of collision : " + c.getCollisions());
          break;

        case 6:
          long l2 = System.currentTimeMillis();
          System.out.println("Time in mili seconds " + (l2 - l1));
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