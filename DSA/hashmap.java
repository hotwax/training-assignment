import java.util.Scanner;
import java.util.InputMismatchException;

class Entry < K, V > {
  private K key;
  private V value;
  private Entry <K,V> next;
  
  //constructor
  public Entry(K key, V value, Entry < K, V > next) {
    this.key = key;
    this.value = value;
    this.next = next;
  }

  // method to return key
  public K getKey() {
    return key;
  }
  //method to set key
  public void setKey(K key) {
    this.key = key;
  }
 //method to get value
  public V getValue() {
    return value;
  }
//method to set value
  public void setValue(V value) {
    this.value = value;
  }
//to get next entry
  public Entry getNext() {
    return next;
  }
//to set next entry
  public void setNext(Entry < K, V > next) {
    this.next = next;
  }
}
class hashmap<K,V> {
	
  private int capacity = 16; //initial capacity

  private Entry < K, V > [] table; //table to store hashmap entries

  public hashmap() { //constructor to initialize
    table = new Entry[capacity];
  }

  public hashmap(int capacity) { //constructor to initialize
    this.capacity = capacity;
    table = new Entry[capacity];
  }
  
  public void put(K key, V value) { //method to add a pair into hashmap
    int index = index(key); //getting index for key
    Entry newEntry = new Entry(key, value, null);
    if (table[index] == null) {
      table[index] = newEntry; // if that index is empty we will store data there.
    } else {
      Entry < K, V > previousNode = null;  // if that index is having a value pair already we will have to override the data part
      Entry < K, V > currentNode = table[index];
      while (currentNode != null) {
        if (currentNode.getKey().equals(key)) {
          currentNode.setValue(value);
          break;
        }
        previousNode = currentNode;
        currentNode = currentNode.getNext();
      }
      if (previousNode != null)
        previousNode.setNext(newEntry);
    }
  }

  public V get(K key) { //method to get value corresponding to a object
	  
    V value = null;
    int index = index(key);
    Entry < K, V > entry = table[index];
    while (entry != null) {
      if (entry.getKey().equals(key)) { //if key found then loop will break
        value = entry.getValue();
        break;
      }
      entry = entry.getNext();
    }
    return value;
  }
  
  public void remove(K key) { //method to remove key value pair
    int index = index(key); //getting index for key
    Entry previous = null;
    Entry entry = table[index];
    while (entry != null) { // to delete first we will search by key
      if (entry.getKey().equals(key)) {
        if (previous == null) {
          entry = entry.getNext();
          table[index] = entry;
          return;
        } else {
          previous.setNext(entry.getNext());
          return;
        }
      }
      previous = entry;
      entry = entry.getNext(); // when we find that we will store next entry at that place
    }
  }
  public void showAll() { //printing the entire hashmap
    for (int i = 0; i < capacity; i++) {
      if (table[i] != null) {
        Entry < K, V > currentNode = table[i];
        while (currentNode != null) {
          System.out.println(String.format("Key = "+currentNode.getKey()+", Value = "+currentNode.getValue()));
          currentNode = currentNode.getNext();
        }
      }
    }
  }
  private int index(K key) { //a helping function to get index of a key
    if (key == null) {
      return 0;
    }
    return Math.abs(key.hashCode() % capacity);
  }
}

class Demo {
  public static void main(String[] args) {
    int a, b;
    hashmap < Integer, Integer > map = new hashmap < > ();

    while (true) {
      System.out.println("===========================");
      System.out.println("*****Enter your choice*****");
      System.out.println("1.Put / Update");
      System.out.println("2.Remove");
      System.out.println("3.ShowAll");
      System.out.println("4.Search");
      System.out.println("5.Exit");
      System.out.println("===========================");
      try {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        switch (x) {
        case 1:
          System.out.println("Enter key ");
          a = sc.nextInt();
          System.out.println("Enter value ");
          b = sc.nextInt();
          map.put(a, b);
          System.out.println("Done ");
          break;

        case 2:
          System.out.println("Enter key ");
          a = sc.nextInt();
          map.remove(a);
          System.out.println("Done ");
          break;

        case 3:
          map.showAll();
          break;

        case 4:
          System.out.println("Enter key ");
          a = sc.nextInt();
          System.out.println("key=" + a + "     Value=" + map.get(a));
          break;


        case 5:
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