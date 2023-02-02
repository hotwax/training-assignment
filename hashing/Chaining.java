import java.util.Scanner;

public class Chaining {

  static String comparison(int[] arr) {
    HashMap map = new HashMap(50000);
    long startTime = System.currentTimeMillis();
    for (int i = 0; i < arr.length; i++) {
      map.put(arr[i], arr[i] + "");
    }
    long endTime = System.currentTimeMillis();
    long timeTaken = endTime - startTime;
    return "No of collisions: " + map.noOfCollisions + " Time taken: " + timeTaken;
  }

  public static class HashMap {
    public class HMNode {
      int key;
      String value;
      HMNode next;

      HMNode() {

      }

      HMNode(int key, String value) {
        this.key = key;
        this.value = value;
      }
    }

    class LinkedList {
      HMNode head, tail;
      int size;

      void add(HMNode node) {

        if (size == 0) {
          head = tail = node;
        } else {
          tail.next = node;
          tail = node;
        }
        size++;
      }

      void removeFirst() {
        if (size == 0) {
          System.out.println("List is empty");
        } else if (size == 1) {
          head = tail = null;
          size = 0;
        } else {
          head = head.next;
          size--;
        }
      }

      void removeLast() {
        if (size == 0) {
          System.out.println("List is empty");
        } else if (size == 1) {
          head = tail = null;
          size = 0;
        } else {
          HMNode temp = head;
          while (temp.next != tail) {
            temp = temp.next;
          }
          temp.next = null;
          tail = temp;
          size--;
        }
      }

      void remove(int idx) {
        if (idx < 0 || idx >= size) {
          System.out.println("Invalid arguments");
        } else if (idx == 0) {
          removeFirst();
        } else if (idx == size - 1) {
          removeLast();
        } else {

          HMNode temp = head;
          for (int i = 1; i < idx; i++) {
            temp = temp.next;
          }
          HMNode nnode = temp.next.next;
          temp.next = nnode;
          size--;
        }
      }

      HMNode get(int idx) {
        if (size == 0) {
          System.out.println("List is empty");
          return new HMNode();
        } else if (idx < 0 || idx >= size) {
          System.out.println("Invalid arguments");
          return new HMNode();
        }

        HMNode temp = head;
        for (int i = 1; i <= idx; i++) {
          temp = temp.next;
        }
        return temp;
      }

    }

    int noOfCollisions = 0;

    public int size;
    public LinkedList[] buckets; // array of linkedlists

    public HashMap(int n) {
      initbuckets(n);
    }

    public void initbuckets(int N) {
      buckets = new LinkedList[N];
      for (int bi = 0; bi < buckets.length; bi++) {  //bi- bucket index
        buckets[bi] = new LinkedList();
      }
    }

    public void put(int key, String value) {
      int bi = hash(key);  //bi- bucket index
      int di = getIndexWithinBucket(key, bi);  //di- data index within bucket
      if (di == -1) {
        buckets[bi].add(new HMNode(key, value));
        size++;
      } else {
        buckets[bi].get(di).value = value;

      }
    }

    public int hash(int key) {
      if (key < 0)
        key += buckets.length;
      return Math.abs(key) % buckets.length;
    }

    public int getIndexWithinBucket(int key, int bi) {
      int di = 0; //di- data index within bucket
      for (int i = 0; i < buckets[bi].size; i++) {
        if (buckets[bi].get(i).key == key)
          return di;
        else
          noOfCollisions++;

        di++;
      }

      return -1;
    }

    public String get(int key) {
      int bi = hash(key); //bi- bucket index
      int di = getIndexWithinBucket(key, bi); //di- data index within bucket
      if (di == -1) {
        return null;
      } else {
        return buckets[bi].get(di).value;
      }
    }

    public boolean containsKey(int key) {
      int bi = hash(key); //bi- bucket index
      int di = getIndexWithinBucket(key, bi); //di- data index within bucket

      if (di == -1) {
        return false;
      } else {
        return true;
      }
    }

    public void remove(int key) {
      int bi = hash(key); //bi- bucket index
      int di = getIndexWithinBucket(key, bi); //di- data index within bucket
      if (di == -1) {
        System.out.println("Doesn't exists.");
        return;
      } else {
        buckets[bi].remove(di);
        size--;
      }
    }

    public int size() {
      return size;
    }

    public void display() {
      for (int i = 0; i < buckets.length; i++) {
        for (int j = 0; j < buckets[i].size; j++) {
          System.out.println("Key: " + buckets[i].get(j).key + " " + "Value: " + buckets[i].get(j).value);
        }
      }
    }

    public int getNoOfCollisions() {
      return noOfCollisions;
    }

  }

  public static void main(String[] args) {

    long startingTime = System.currentTimeMillis();

    try {
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter the size of map: ");
      int arrSize = sc.nextInt();
      HashMap map = new HashMap(arrSize);

      while (true) {
        System.out.println("Your HashMap: ");
        map.display();
        System.out.println();

        System.out.println("Enter 1 to add");
        System.out.println("Enter 2 to remove");
        System.out.println("Enter 3 to get");
        System.out.println("Enter 4 to search");
        System.out.println("Enter 5 to find the number of collisions");

        System.out.println();
        System.out.println("Note- key is of 'int' data type and value is of 'String' data type.");

        int choice = sc.nextInt();
        int key;
        String val;

        switch (choice) {
          case 1:
            System.out.print("Enter the Key: ");
            key = sc.nextInt();
            System.out.print("Enter the value: ");
            val = sc.next();
            map.put(key, val);
            long endingTime = System.currentTimeMillis();
            System.out.println("Time taken: " + (endingTime - startingTime) + " ms");
            System.out.println("-------------------------------------");
            break;

          case 2:
            System.out.print("Enter the Key: ");
            key = sc.nextInt();
            map.remove(key);
            System.out.println("-------------------------------------");
            break;

          case 3:
            System.out.print("Enter the Key: ");
            key = sc.nextInt();
            System.out.println(map.get(key));
            System.out.println("-------------------------------------");
            break;

          case 4:
            System.out.print("Enter the Key: ");
            key = sc.nextInt();
            System.out.println(map.containsKey(key));
            System.out.println("-------------------------------------");
            break;

          case 5:
            System.out.println(map.getNoOfCollisions());
            System.out.println("-------------------------------------");

            break;

          default:
            System.out.println("Program terminated successfully.");
            System.out.println("-------------------------------------");
            return;
        }

      }

    } catch (Exception e) {
      System.out.println(e);
    }

  }

}