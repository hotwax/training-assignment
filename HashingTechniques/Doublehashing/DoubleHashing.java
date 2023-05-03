package Doublehashing;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.System;

//Double Hashing class
public class DoubleHashing {
    private int capacity;
    private boolean isNotPrime[];
    private int keys[];
    private int vals[];
    private int prime;
    private int size;
    int collision = 0;
    long time;

    // constructor
    DoubleHashing(int c) {
        capacity = c;
        isNotPrime = new boolean[capacity + 1];
        // sieve of eronthesis for find the number is prime or not
        isNotPrime[0] = isNotPrime[1] = true;
        for (int index = 2; index < capacity; index++) {
            if (!isNotPrime[index]) {
                for (int index2 = index * 2; index2 < capacity; index2 = index2 + index) {
                    isNotPrime[index2] = true;
                }
            }
        }

        // find the nearest Prime number from capacity
        prime = capacity - 1;
        if (prime > 1) {
            while (isNotPrime[prime] != false) {
                prime = prime - 1;
            }
        }
        keys = new int[capacity];
        vals = new int[capacity];
        size = 0;
        time = 0;
        for (int index = 0; index < capacity; index++) {
            keys[index] = -1;
        }
    }

    // Clear the Whole data of Map
    void clear() {
        size = 0;
        keys = new int[capacity];
        vals = new int[capacity];
        for (int index = 0; index < capacity; index++) {
            keys[index] = -1;
        }
    }

    // get the current time in millis
    private long getTime() {
        return System.currentTimeMillis();
    }

    // hash1 function is used to compressed the Key under the capacity
    private int hash1(int key) {
        return key % capacity;
    }

    // hash2 function is call whenver the collision occur
    private int hash2(int key) {
        if (prime > 0) {
            return prime - (key % prime);
        } else {
            return 0;
        }
    }

    int getSize() {
        return size;
    }

    // check the Map is full or not
    boolean isFull() {
        return size == capacity;
    }

    // check the Map is emty or not
    boolean isEmpty() {
        return size == 0;
    }

    // check the Key is present in Map
    boolean contain(int key) {
        if (getValue(key) != -1) {
            return true;
        } else {
            return false;
        }
    }

    // Get the value of particular Key
    public int getValue(int key) {
        int prob = hash1(key);
        int index = prob;
        while (keys[prob] != key) {
            prob = (prob + hash2(key)) % capacity;
            if (index == prob) {
                return -1;
            }
        }
        return vals[prob];
    }

    // Insertion of Key and value in Map
    void insertion(int key, int val) {
        if (isFull()) {
            if (!contain(key)) {
                System.out.println("Map is full");
                return;
            }
        }
        long initialTime = getTime();
        int index = hash1(key);
        while (keys[index] != -1) {
            if (keys[index] == key) {
                break;
            }
            collision++;
            index = (index + hash2(key)) % capacity;
        }
        if (keys[index] == key) {
            vals[index] = val;
        } else {
            keys[index] = key;
            vals[index] = val;
            size++;
        }
        long finalTime = getTime();
        time += (finalTime - initialTime);
    }

    // Deletion the key from Map
    Integer deletion(int key) {
        if (isEmpty()) {
            return -1;
        }
        if (!contain(key)) {
            return -1;
        }
        int index = hash1(key);
        while (keys[index] != key) {
            index = (index + hash2(key)) % capacity;
        }
        int tempKey = keys[index];
        keys[index] = -1;
        vals[index] = 0;
        size--;
        return tempKey;
    }

    // display the Keys and Value
    void display() {
        for (int index = 0; index < capacity; index++) {
            if (keys[index] != -1) {
                System.out.println(keys[index] + "->" + vals[index]);
            }
        }
    }

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter the Capacity of Map");
            int capacity = input.nextInt();
            DoubleHashing doubleHashing = new DoubleHashing(capacity);
            String choice;
            do {
                System.out.println();
                System.out.println("Operations in HashMap(Double hashing)");
                System.out.println();
                System.out.println("  1. Insert the key and value in Map");
                System.out.println("  2. Get the Values Using Key");
                System.out.println("  3. Delete the key in Map");
                System.out.println("  4. Display ");
                System.out.println("  5. GetSize");
                System.out.println("  6. Clear the Data ");
                System.out.println("  7. Check the Map is Empty or not");
                System.out.println("  8. Number of Collision  ");
                System.out.println("  9.Total time take in insertion");
                System.out.println("  10.Exit");
                System.out.println();
                System.out.println("Enter the choice");
                System.out.println();
                choice = input.next();
                System.out.println();
                switch (choice) {
                    case "1":
                        System.out.println("Enter the key");
                        int key = input.nextInt();
                        System.out.println("Enter the Value");
                        int val = input.nextInt();
                        doubleHashing.insertion(key, val);
                        break;

                    case "2":
                        System.out.println("Enter the key");
                        int keyOfValue = input.nextInt();
                        if (doubleHashing.isEmpty()) {
                            System.out.println("Map is empty");
                            break;
                        }
                        int value = doubleHashing.getValue(keyOfValue);
                        if (value != -1) {
                            System.out.println("Value of the key is - " + value);
                        } else {
                            System.out.println("Key is not present in Map");
                        }
                        break;

                    case "3":
                        System.out.println("Enter the key");
                        int delkey = input.nextInt();
                        if (doubleHashing.isEmpty()) {
                            System.out.println("Map is empty");
                            break;
                        }
                        int deletedKey = doubleHashing.deletion(delkey);
                        if (deletedKey == -1) {
                            System.out.println("Key is not present in Map");
                        } else {
                            System.out.println("Deleted Key is -" + deletedKey);
                        }
                        break;

                    case "4":
                        if (doubleHashing.isEmpty()) {
                            System.out.println("Map is empty");
                        } else {
                            System.out.println("Display the Keys and Values of Map");
                            System.out.println();
                            System.out.println("Keys->Values");
                            System.out.println();
                            doubleHashing.display();
                        }
                        break;

                    case "5":
                        System.out.println("Size of Hashmap is - " + doubleHashing.getSize());
                        break;

                    case "6":
                        doubleHashing.clear();
                        System.out.println("Data is deleted from Map");
                        break;

                    case "7":
                        if (doubleHashing.isEmpty()) {
                            System.out.println("Map is empty");
                        } else {
                            System.out.println("Map is not empty");
                        }
                        break;

                    case "8":
                        System.out.println("Collision - " + doubleHashing.collision);
                        break;

                    case "9":
                        System.out.println("Total time take in Insertion is - " + doubleHashing.time);
                        break;

                    case "10":
                        System.out.println("Thank You");
                        break;

                    default:
                        System.out.println("Invalid Choice");
                        break;
                }
            } while (!choice.equals("10"));
        } catch (InputMismatchException exception) {
            System.out.println(exception);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}