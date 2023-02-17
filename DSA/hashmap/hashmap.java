import java.util.Scanner;
import java.util.InputMismatchException;

class Node < K, V > {
  private K key;
  private V value;
  private Node <K,V> next;
  
  //constructor
  public Node(K key, V value, Node < K, V > next) {
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
  public Node getNext() {
    return next;
  }
//to set next entry
  public void setNext(Node < K, V > next) {
    this.next = next;
  }
}
class CustomHashMap<K,V> {
	
  private int capacity = 16; //initial capacity

  private Node < K, V > [] table; //table to store CustomHashMap entries

  public CustomHashMap() { //constructor to initialize
    table = new Node[capacity];
  }

  public CustomHashMap(int capacity) { //constructor to initialize
    this.capacity = capacity;
    table = new Node[capacity];
  }
  
  public void put(K key, V value) { //method to add a pair into CustomHashMap
    int index = index(key); //getting index for key
    Node newNode = new Node(key, value, null);
    if (table[index] == null) {
      table[index] = newNode; // if that index is empty we will store data there.
    } else {
      Node < K, V > previousNode = null;  // if that index is having a value pair already we will have to override the data part
      Node < K, V > currentNode = table[index];
      while (currentNode != null) {
        if (currentNode.getKey().equals(key)) {
          currentNode.setValue(value);
          break;
        }
        previousNode = currentNode;
        currentNode = currentNode.getNext();
      }
      if (previousNode != null)
        previousNode.setNext(newNode);
    }
  }

  public V get(K key) { //method to get value corresponding to a object
	  
    V value = null;
    int index = index(key);
    Node < K, V > entry = table[index];
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
    Node previous = null;
    Node entry = table[index];
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
  public void showAll() { //printing the entire CustomHashMap
    for (int index = 0; index < capacity; index++) {
      if (table[index] != null) {
        Node < K, V > currentNode = table[index];
        while (currentNode != null) {
          System.out.println("Key = "+currentNode.getKey()+", Value = "+currentNode.getValue());
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
    int input1, input2;
    CustomHashMap < Integer, Integer > map = new CustomHashMap < > ();

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
        int condition = sc.nextInt();
        switch (condition) {
        case 1:
          System.out.println("Enter key ");
          input1 = sc.nextInt();
          System.out.println("Enter value ");
          input2 = sc.nextInt();
          map.put(input1, input2);
          System.out.println("Done ");
          break;

        case 2:
          System.out.println("Enter key ");
          input1 = sc.nextInt();
          map.remove(input1);
          System.out.println("Done ");
          break;

        case 3:
          map.showAll();
          break;

        case 4:
          System.out.println("Enter key ");
          input1 = sc.nextInt();
          System.out.println("key=" + input1 + "     Value=" + map.get(input1));
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