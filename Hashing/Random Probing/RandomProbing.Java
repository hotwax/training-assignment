import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
public class RandomProbing{
    private int capacity;
    private int[] keys;
    private int[] values;
    private int size;
    private Random random;

    public RandomProbing(int capacity) {
        this.capacity = capacity;
        this.keys = new int[capacity];
        this.values = new int[capacity];
        this.size = 0;
        this.random = new Random();
    }

    public int hash(int key) {
        return key % capacity;
    }

    public int probe() {
        return random.nextInt(capacity);
    }
    //method to put Key And Value
    public void put(int key, int value) {
        if (size == capacity) {
            System.out.println("Hashmap is full");
            return;
        }

        int index = hash(key);
        int numCollisions=0;

        while (keys[index] != 0 && keys[index] != key) {
            index = (index + probe()) % capacity;
            numCollisions++;
        }

        if (keys[index] == key) {
            values[index] = value; // update existing value
        } else {
            keys[index] = key;
            values[index] = value;
            size++;
        }

        System.out.println("Inserted key " + key + " with value " + value);
    }
    //method to get Value
    public int get(int key) {
        int index = hash(key);
        int numCollisions = 0;

        while (keys[index] != 0 && keys[index] != key) {
            index = (index + probe()) % capacity;
            numCollisions++;
        }

        if (keys[index] == key) {
            System.out.println("Found key " + key + " with value " + values[index]);
            return values[index];
        } else {
            System.out.println("Key " + key + " not found ");
            return -1; // return -1 if key is not found
        }
    }
    //method to display
    public void display() {
        System.out.println("Hashmap:");
        for (int i = 0; i < capacity; i++) {
            if (keys[i] != 0) {
                System.out.println(keys[i] + " => " + values[i]);
            }
        }
    }
    // Main method
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter capacity:");
            int capacity = scanner.nextInt();
            RandomProbing hashmap = new RandomProbing(capacity);
            int choice;
            do{
                System.out.println("Menu:");
                System.out.println("1. Insert element");
                System.out.println("2. Get element");
                System.out.println("3. Display Hashmap");
                System.out.println("4. Exit");
                System.out.print("Enter choice:");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Enter key: ");
                        int key = scanner.nextInt();
                        System.out.print("Enter value: ");
                        int value = scanner.nextInt();
                        hashmap.put(key, value);
                        break;
                    case 2:
                        System.out.print("Enter key: ");
                        key = scanner.nextInt();
                        hashmap.get(key);
                        break;
                    case 3:
                        hashmap.display();
                        break;
                    case 4:
                        System.out.println("Exit done");
                        break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }while(choice!=4);
    }
    catch(InputMismatchException ex)
    {
        System.out.println(ex);
    }
}
}
