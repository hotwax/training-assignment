
// import necessary Packages
import java.util.Scanner;
import java.util.InputMismatchException;

//class QuadraticProbing
public class QuadraticProbing {
    private String[] keys;
    private int[] values;
    private int size;
    private int capacity;
    private int collisions;

    public QuadraticProbing(int capacity) {
        this.capacity = capacity;
        this.keys = new String[capacity];
        this.values = new int[capacity];
        this.size = 0;
        this.collisions = 0;
    }

    public int hash(String key) {
        return Math.abs(key.hashCode() % capacity);
    }

    // to insert key and value
    public void put(String key, int value) {
        int index = hash(key);
        int index1 = 0;
        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                values[index] = value;
                return;
            }
            collisions++;
            index1++;
            index = (index + index1 * index1) % capacity;
        }
        keys[index] = key;
        values[index] = value;
        size++;
    }

    // to delete key and value
    public int delete(String key) {
        int index = hash(key);
        int index1 = 0;
        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                keys[index] = null;
                values[index] = 0;
            }
            index = (index + index1 * index1) % capacity;
        }
        System.out.println("Key not found");
        return -1;
    }
    
    public int collisions() {
        return collisions;
    }

    // traversing for keys and values
    public void traverse() {
        for (int index = 0; index < capacity; index++) {
            if (keys[index] != null) {
                System.out.println("(" + keys[index] + "," + values[index] + ")");
            }
        }
    }

    // Searching for Values
    public void search(String key) {
        int index = hash(key);
        int index1 = 0;
        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                System.out.println("Value is:" + values[index]);
                return;
            }
            index1++;
            index = (index + index1 * index1) % capacity;
        }
        System.out.println("Key not found.");
    }

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter the size of hash table: ");
            int capacity = input.nextInt();
            QuadraticProbing hashtable = new QuadraticProbing(capacity);
            int choice;
            do {
                System.out.println("\n1. Put ");
                System.out.println("2. Search");
                System.out.println("3. Traverse ");
                System.out.println("4. number of collisions");
                System.out.println("5. Delete");
                System.out.println("6. Exit");
                System.out.print("\nEnter your choice: ");
                choice = input.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Enter the key: ");
                        String key = input.next();
                        System.out.print("Enter the value: ");
                        int value = input.nextInt();
                        hashtable.put(key, value);
                        break;
                    case 2:
                        System.out.print("Enter the key to search: ");
                        key = input.next();
                        hashtable.search(key);
                        break;
                    case 3:
                        hashtable.traverse();
                        break;
                    case 4:
                        System.out.println("Number of collisions: " + hashtable.collisions());
                        break;
                    case 5:
                        String key1 = input.next();
                        hashtable.delete(key1);
                        System.out.println("Deleted");
                        break;
                    case 6:
                        System.out.println("Exit done");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            } while (choice != 6);
        } catch (InputMismatchException exception) {
            System.out.println(exception);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}