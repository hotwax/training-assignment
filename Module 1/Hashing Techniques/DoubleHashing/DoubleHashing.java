import java.util.InputMismatchException;
import java.util.Scanner;

public class DoubleHashing {
    public static class HashMapArray {
        
        
        int size; //Size of the hashmap.
        
        int capacity; // capacity of the hashmap
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

        public boolean isPrime(int n) { // ts - sqrt(n)
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
        int getValueOfKey(int key) {
            int index = hashFunction(key);
            int index1 = hashFunction1(key);
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
                if (table[index].key == key) {
                    table[index].value = val;
                    return;
                }

                collision++;
                index = (index + index1) % capacity;

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
        public int numberOfCollision() {
            return collision;
        }

    }

    public static void main(String[] args) {
        HashMapArray hashmap = null;
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print("Please Enter the size of the HashMap: ");
        hashmap = new HashMapArray(sc.nextInt());
        long t1 = System.currentTimeMillis();

        do {
            System.out.println("Select an option from the Dashboard given below:");
            System.out.println("1 : Insert  ");
            System.out.println("2 : Delete ");
            System.out.println("3 : Display the Hashmap");
            System.out.println("4 : Number of Collisions");
            System.out.println("5 : Get Value of Key");
            System.out.println("6 : Time ");
            System.out.println("7 : Exit");
            System.out.println("Enter your choice : ");
            try {
                
                choice = sc.nextInt();
                switch (choice) {
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
                            System.out.println("Number of collisions: " + hashmap.numberOfCollision());
                        }
                        break;
                    case 5:
                        if (hashmap != null) {
                            System.out.println("Please Enter the key: ");
                            System.out.println(hashmap.getValueOfKey(sc.nextInt()));
                        } else {
                            System.out.println("Create a hashmap first");
                        }
                        break;
                    case 6:
                        long t2 = System.currentTimeMillis();
                        System.out.println("Time in milli Seconds is:" + (t2-t1));
                        break;
                    case 7:
                        System.out.println("Program Terminated");
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid choice");
            }
        } while (choice != 7);
    }
}