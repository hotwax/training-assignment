import java.util.Scanner;
import java.util.InputMismatchException;
class LinearProbing {
  int size, max;
  Integer[] keys; //table to store values
  Integer[] vals;
  int collision = 0;
  
  LinearProbing(int capacity) //constructor to initize
  {
    size = 0;
    max = capacity;
    keys = new Integer[max];
    vals = new Integer[max];
  }

  /* Method to check if hash table contains a key */
  boolean contains(Integer key) {
    return get(key) != null;
  }

  /** Function  to get hash code of a given key **/
  int hash(Integer key) {
    return key.hashCode() % max;
  }

  void put(Integer key, Integer val) // function to put
  {
    int tmp = hash(key);
    int index = tmp;
    do {
      if (keys[index] == null) {
        keys[index] = key;
        vals[index] = val;
        size++;
        return;
      }
      if (keys[index].equals(key)) {
        vals[index] = val;
        return;
      }
      if (keys[index] != null) collision++;
      index = (index + 1) % max;
    } while (index != tmp);

  }

  Integer get(Integer key) // function to search for value
  {
    int tmp = hash(key);
    int index = tmp;
    do {
      if (keys[index] != null && keys[index].equals(key)) {
        return vals[index];
      }
      index = (index + 1) % max;
    } while (index != tmp);

    return null;
  }

  void remove(Integer key) // function to remove value
  {
    if (!contains(key))
      return;

    int index = hash(key);
    while (!key.equals(keys[index]))
      index = (index + 1) % max;
    keys[index] = vals[index] = null;

    size--;        
  }
  int getCollision() // to count number of collision
  {
    return collision;
  }

  /** Function to print HashTable **/
  void showAll() {
    for (int index = 0; index < max; index++)
      if (keys[index] != null)
        System.out.println("Key = " + keys[index] + ", Value = " + vals[index]);
    System.out.println();
  }
}
class Main {
  public static void main(String[] args) {
    int value1, value2;
    LinearProbing linear = new LinearProbing(10);
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
          linear.put(value1, value2);
          System.out.println("Done ");
          break;

        case 2:
          System.out.println("Enter key ");
          value1 = sc.nextInt();
          linear.remove(value1);
          System.out.println("Done ");
          break;

        case 3:
          linear.showAll();
          break;

        case 4:
          System.out.println("Enter key ");
          value1 = sc.nextInt();
          System.out.println("key=" + value1 + "     Value=" + linear.get(value1));
          break;

        case 5:
          System.out.println("No of collision : " + linear.getCollision());
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