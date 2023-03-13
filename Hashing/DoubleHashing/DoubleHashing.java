package Hashing.DoubleHashing;
import java.util.InputMismatchException;
import java.util.Scanner;
public class DoubleHashing {
public static class HashMapArray {
    // size of the hashmap
    int size;
    // capacity of the hashmap
     int capacity;
    // number of collision in the hashmap
    int collision;
    // hashmap array
    Node[] table;
    // prime number for hash function 2
    
    // Node class
    static class Node {
        int key;
        int value;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // Constructor
    public HashMapArray(int capacity) {
        int size = 0;
        this.capacity = capacity;
        table = new Node[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = null;
        }
        collision = 0;
    }
     int justSmallerPrimeNum() {
        for (int num = capacity - 1; num >= 1; num--) {
            if (isPrime(num))
                return num;
        }

        return 1;
    }

    public  boolean isPrime(int n) { // ts - sqrt(n)
        for (int div = 2; div * div <= n; div++) { // div <= root(n)
            if (n % div == 0) {
                return false;
            }
        }
        return true;
    }
    // Function to create hash function 1
    int hashFunction(int key) {
        return key % capacity;
    }

    // Function to create hash function 2
    int hashFunction1(int key) {
      if (key < 0)
            key += capacity;
      key = key % capacity;
        return justSmallerPrimeNum() - (key % justSmallerPrimeNum());
    }

    // Function to get value from hash map
    int get(int key) {
        int index = hashFunction(key);
        int index1 = hashFunction1(key);
        if(table[index] == null) {
            System.out.println("Key not found");
            return -1;
        }
        while (table[index] != null && (!(table[index].key == key))) {
            index += index1;
            index %= capacity;
        }
        return table[index].value;
    }

    // Function to insert a key value pair in the hash map
    public void insert(int key, int val) {
        if (size == capacity) {
            System.out.println("Hash map is full");
            return;
        }
        int index = hashFunction(key);
        int index1 = hashFunction1(key);

        while (table[index] != null) {
            if(table[index].key == key) {
                     table[index].value = val;
                        return;
            }

            collision++;
            index = (index+index1)%capacity ;

        }
        Node newNode = new Node(key, val);
        table[index] = newNode;
        size++;
    }

    // Function to delete a key value pair in the hash map
    public void delete(int key) {
        int index = hashFunction(key);
        int index1 = hashFunction1(key);
        while (table[index] != null && (!(table[index].key == key))) {
            index += index1;
            index %= capacity;
        }
        table[index] = null;
        size--;
    }

    // Function to display the hash map
    public void display() {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                System.out.println("Key: " + table[i].key + " Value: " + table[i].value);
            }
        }
    }

    // Function to get the number of collision
    public int getCollision() {
        return collision;
    }

}


    public static void main(String[] args) {
        HashMapArray hashmap = null;
        int choice = 0 ;

        do {
            System.out.println("--------- MENU ---------");
            System.out.println("Press 0 : Create a new Hashmap");
            System.out.println("Press 1 : Insert  Value");
            System.out.println("Press 2 : Delete Value");
            System.out.println("Press 3 : Display the Hashmap");
            System.out.println("Press 4 : Get All Collisions");
            System.out.println("Press 5 : Get Value from Key");
            System.out.println("Press 6 : Exit");
            System.out.println("Enter your choice : ");
            try {
                Scanner sc = new Scanner(System.in);
                choice = sc.nextInt();
                switch (choice) {
                    case 0:
                        System.out.print("Please Enter the size of the HashMap: ");
                        hashmap = new HashMapArray(sc.nextInt());
                        break;
                    case 1:
                        System.out.print("Please Enter the key: ");
                        int key = sc.nextInt();
                        System.out.print("Please Enter the value: ");
                        int value = sc.nextInt();
                        hashmap.insert(key, value);
                        break;
                    case 2:
                        System.out.print("Please Enter the key: ");
                        hashmap.delete(sc.nextInt());
                        System.out.println("Deleted");
                        break;
                    case 3:
                        hashmap.display();
                        break;
                    case 4:
                        if (hashmap != null) {
                            System.out.println("Number of collisions: " + hashmap.getCollision());
                        }
                        break;
                    case 5:
                        if (hashmap != null) {
                            System.out.println("Please Enter the key: ");
                            System.out.println(hashmap.get(sc.nextInt()));
                        } else {
                            System.out.println("Create a hashmap first");
                        }
                        break;
                    case 6:
                        System.out.println("Program Terminated");
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid choice");
            }
        } while (choice != 6);
    }
}