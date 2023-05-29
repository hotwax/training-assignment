
// import necessary Packages
import java.util.InputMismatchException;
import java.util.Scanner;

//Class LinearProbing
public class LinearProbing {
    private int[] keys;
    private int[] values;
    private int size;
    private int capacity;

    public LinearProbing(int capacity) {
        this.capacity = capacity;
        this.keys = new int[capacity];
        this.values = new int[capacity];
    }

    // get function
    public int get(int key) {
        int hashKey = hash(key);
        while (keys[hashKey] != 0) {
            if (keys[hashKey] == key) {
                return values[hashKey];
            }
            hashKey = (hashKey + 1) % capacity;
        }
        return -1;
    }

    // put function
    public void put(int key, int value) {
        if (size == capacity) {
            System.out.println("Hash Table is Full");
            return;
        }
        int hashKey = hash(key);
        while (keys[hashKey] != 0 && keys[hashKey] != key) {
            hashKey = (hashKey + 1) % capacity;
        }
        keys[hashKey] = key;
        values[hashKey] = value;
        size++;
    }

    public int hash(int key) {
        return key % capacity;
    }

    // to count number of collisions
    public int collisionCount() {
        int collisionCount = 0;
        for (int index = 0; index < capacity; index++) {
            if (keys[index] != 0 && keys[index] != -1) {
                int hashKey = hash(keys[index]);
                if (hashKey != index) {
                    collisionCount++;
                }
            }
        }
        return collisionCount;
    }

    // function to traverse
    public void traverse() {
        System.out.print("Hash Table:");
        for (int index = 0; index < capacity; index++) {
            if (keys[index] != 0) {
                System.out.print("(" + keys[index] + ", " + values[index] + ") ");
            } else {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    // function to delete key with value
    public void delete(int key) {
        int index = hash(key);
        while (keys[index] != 0) {
            if (keys[index] == key) {
                keys[index] = 0;
                break;
            } else {
                System.out.println("Key not found");
            }
            index = (index + 1) % capacity;
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter capacity of hash table: ");
            int capacity = scanner.nextInt();
            LinearProbing hashTable = new LinearProbing(capacity);
            int choice = 0;
            do {
                System.out.println("1. Put");
                System.out.println("2. Get");
                System.out.println("3. traverse");
                System.out.println("4. Number of collisions");
                System.out.println("5. Delete");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter key: ");
                        int key = scanner.nextInt();
                        System.out.print("Enter value: ");
                        int value = scanner.nextInt();
                        hashTable.put(key, value);
                        break;
                    case 2:
                        System.out.print("Enter key: ");
                        key = scanner.nextInt();
                        int result = hashTable.get(key);
                        if (result == -1) {

                            System.out.println("Key not found!");
                        } else {
                            System.out.println("Value: " + result);
                        }
                        break;
                    case 3:
                        hashTable.traverse();
                        break;
                    case 4:
                        System.out.println("Number of collisions: " + hashTable.collisionCount());
                        break;
                    case 5:
                        System.out.print("Enter key to delete: ");
                        int deletedKey = scanner.nextInt();
                        hashTable.delete(deletedKey);
                        break;
                    case 6:
                        System.out.println("Exit done");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                        break;
                }
            } while (choice != 6);
        } catch (InputMismatchException exception) {
            System.out.println("Input Mismatch");
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}