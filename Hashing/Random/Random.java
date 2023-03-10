import java.util.Scanner;
import java.util.InputMismatchException;
class RandomPro {
  int size, max;
  Integer[] keys;
  Integer[] value; //arrays to store data
  int collision;
  final int random_var;

  RandomPro(int capacity) //constructor to initialize
  {
	random_var=(int)(Math.random()*(max)) + 1;
    size = 0;
    max = capacity;
    keys = new Integer[max];
    value = new Integer[max];
  }

  boolean contains(Integer key) // contains key or not
  {
    return get(key) != null;
  }

  int hash(Integer key) //hash code generation method
  {
    return key.hashCode() % max;
  }

  void insert(Integer key, Integer val) //insertion of an key value
  {
    int tmp = hash(key);
    int index = tmp;
    do {
      if (keys[index] != null) collision++; //if that index is already filled then c++
      if (keys[index] == null) //if it is empty we will simply initialize it
      {
        keys[index] = key;
        value[index] = val;
        size++;
        return;
      }
      if (keys[index].equals(key)) //to update if same key comes 
      {
        value[index] = val;
        return;
      }
      index = (index + (random_var * collision)) % max;
    } while (index != tmp);
  }

  Integer get(Integer key) //to get value from an index
  {
    int index = hash(key);
    while (keys[index] != null) {
      if (keys[index].equals(key))
        return value[index]; //returns value corresponding to key
      index = (index + (random_var * collision)) % max;
    }
    return null; //else return null
  }

  void remove(Integer key) // to remove key value pair 
  {
    if (!contains(key))
      return;

    int index = hash(key);
    while (!key.equals(keys[index]))
      index = (index + (random_var * collision)) % max;
    keys[index] = value[index] = null; //searchs the index and put null over there

    size--;
  }

  void showAll() //display all
  {
    for (int index = 0; index < max; index++)
      if (keys[index] != null)
        System.out.println("Key = " + keys[index] + ", Value = " + value[index]);

    System.out.println();
  }

  int getCollisions() // find out all collision
  {
    return collision;
  }
}

class Main {
  public static void main(String[] args) {
    int value1, value2;
    RandomPro rand = new RandomPro(10);
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
          rand.insert(value1, value2);
          System.out.println("Done ");
          break;

        case 2:
          System.out.println("Enter key ");
          value1 = sc.nextInt();
          rand.remove(value1);
          System.out.println("Done ");
          break;

        case 3:
          rand.showAll();
          break;

        case 4:
          System.out.println("Enter key ");
          value1 = sc.nextInt();
          System.out.println("key=" + value1 + "     Value=" + rand.get(value1));
          break;

        case 5:
          System.out.println("No of collision : " + rand.getCollisions());
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