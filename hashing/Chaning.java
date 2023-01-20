import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Chaning {

  public static class HashMap<K, V> {
    public class HMNode {
      K key;
      V value;

      HMNode(K key, V value) {
        this.key = key;
        this.value = value;
      }
    }

    int noOfCollisions=0;

    public int size; // n
    public LinkedList<HMNode>[] buckets; // N = buckets.length // array of linkedlists

    public HashMap(int n) {
      initbuckets(n);
      size = 0;
    }

    public void initbuckets(int N) {
      buckets = new LinkedList[N];
      for (int bi = 0; bi < buckets.length; bi++) {
        buckets[bi] = new LinkedList<>();
      }
    }

    public void put(K key, V value) {
      int bi = hash(key);
      int di = getIndexWithinBucket(key, bi);
      if (di == -1) {
        buckets[bi].add(new HMNode(key, value));
        size++;
      } else {
        buckets[bi].get(di).value = value;

      }

      double lambda = (size * 1.0) / buckets.length;
      if (lambda > 2.0) { // initially k=2.0
        rehash();
      }
    }

    public int hash(K key) {
      int hc = key.hashCode();
      return Math.abs(hc) % buckets.length;
    }

    public int getIndexWithinBucket(K key, int bi) {
      int di = 0;
      for (HMNode node : buckets[bi]) {
        if (node.key.equals(key))
          return di;
        else noOfCollisions++;  
        di++;
      }

      return -1;
    }

    public void rehash() {
      LinkedList<HMNode>[] oba = buckets; // old buckets
      initbuckets(oba.length * 2);
      size = 0;

      for (int i = 0; i < oba.length; i++) {
        for (HMNode node : oba[i]) {
          put1(node.key, node.value);
        }
      }
    }

    public V get(K key) {
      int bi = hash(key);
      int di = getIndexWithinBucket(key, bi);
      if (di == -1) {
        return null;
      } else {
        return buckets[bi].get(di).value;
      }
    }

    public boolean containsKey(K key) {
      int bi = hash(key);
      int di = getIndexWithinBucket(key, bi);

      if (di == -1) {
        return false;
      } else {
        return true;
      }
    }

    public V remove(K key) {
      int bi = hash(key);
      int di = getIndexWithinBucket(key, bi);
      if (di == -1) {
        return null;
      } else {
        HMNode node = buckets[bi].remove(di);
        size--;
        return node.value;
      }
    }

    public int size() {
      return size;
    }

    public void display(){
        for (int i = 0; i < buckets.length; i++) {
          LinkedList<HMNode> ll = buckets[i];
          for (HMNode node : ll) {
             System.out.println("Key: "+node.key+" "+"Value: "+node.value);
          }
        }
    }

    public int getNoOfCollisions(){
      return noOfCollisions;
    } 

  }

  public static void main(String[] args) {

    int startingTime = System.currentTimeMillis();

    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the size of map: ");
    int arrSize=sc.nextInt();
    HashMap<Integer, String> map = new HashMap<>(arrSize);
    
    
    while (true) {
      System.out.println("Your HashMap: ");
      map.display();
      System.out.println();
      
      System.out.println("Enter 1 to add");
      System.out.println("Enter 2 to remove");
      System.out.println("Enter 3 to get");
      System.out.println("Enter 4 to search");
      System.out.println("Enter 5 to find the number of collisions");
      System.out.println("Enter 6 to end the program");
      
      int n = sc.nextInt();

      if (n == 1) {
        System.out.print("Enter the Key: ");
        int key = sc.nextInt();
        System.out.print("Enter the value: ");
        String val = sc.next();
        map.put(key, val);
        int endingTime = System.currentTimeMillis();
        System.out.println("Time taken: "+ (endingTime-startingTime));
        System.out.println("-------------------------------------");
      } else if (n == 2) {
        System.out.print("Enter the Key: ");
        int key = sc.nextInt();
        System.out.println(map.remove(key));
        System.out.println("-------------------------------------");
      } else if (n == 3) {
        System.out.print("Enter the Key: ");
        int key = sc.nextInt();
        System.out.println(map.get(key));
        System.out.println("-------------------------------------");
      } else if (n == 4) {
        System.out.print("Enter the Key: ");
        int key = sc.nextInt();
        System.out.println(map.containsKey(key));
        System.out.println("-------------------------------------");
      } else if(n==5){
        System.out.println(map.getNoOfCollisions());
      } else {
        System.out.println("-------------------------------------");
        return;
      }

    }

  }

}