package Doublehashing;

import java.util.Scanner;
import java.lang.System;

public class Doublehashing {
    int capacity;
    boolean isnotprime[];
    int keys[];
    int vals[];
    int Prime;
    int size;
    int collision = 0;
    long time;

    // constructor
    Doublehashing(int c) {
        capacity = c;
        isnotprime = new boolean[capacity + 1];
        // sieve of eronthesis for find the number is prime or not
        isnotprime[0] = isnotprime[1] = true;
        for (int i = 2; i < capacity; i++) {
            if (!isnotprime[i]) {

                for (int j = i * 2; j < capacity; j = j + i) {
                    isnotprime[j] = true;
                }
            }
        }

        // find the nearest Prime number from capacity
        Prime = capacity - 1;
        if (Prime > 1) {
            while (isnotprime[Prime] != false) {
                Prime = Prime - 1;
            }
        }

        keys = new int[capacity];
        vals = new int[capacity];
        size = 0;
        time = 0;
        for (int i = 0; i < capacity; i++) {
            keys[i] = -1;
        }

    }

    // Clear the Whole data of Map
    void clear() {
        size = 0;
        keys = new int[capacity];
        vals = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            keys[i] = -1;
        }

    }

    // get the current time in millis
    long gettime() {
        return System.currentTimeMillis();
    }

    // hash1 function is used to compressed the Key under the capacity
    int hash1(int key) {
        return key % capacity;
    }

    // hash2 function is call whenver the collision occur
    int hash2(int key) {
        if (Prime > 0) {
            return Prime - (key % Prime);
        } else {
            return 0;
        }
    }

    int getsize() {
        return size;
    }

    // check the Map is full or not
    boolean isfull() {
        return size == capacity;
    }

    // check the Map is emty or not
    boolean isempty() {
        return size == 0;
    }

    // check the Key is present in Map
    boolean contain(int key) {

        if (getval(key) != -1) {
            return true;
        } else {
            return false;
        }
    }

    // Get the value of particular Key
    public int getval(int key) {
        int i = hash1(key);
        int index = i;
        while (keys[i] != -1) {
            if (keys[i] == key) {
                return vals[i];
            }

            i = (i + hash2(key)) % capacity;
            if (i == index) {
                return -1;
            }
        }
        return -1;
    }

    // Insertion of Key and value in Map
    void insertion(int key, int val) {
        if (isfull()) {
            if (!contain(key)) {
                System.out.println("Map is full");
                return;
            }
        }

        long time1 = gettime();
        int i = hash1(key);

        while (keys[i] != -1) {
            if (keys[i] == key) {
                break;
            }
            collision++;
            i = (i + hash2(key)) % capacity;
        }
        if (keys[i] == key) {
            vals[i] = val;
        } else {
            keys[i] = key;
            vals[i] = val;
            size++;
        }
        long time2 = gettime();

        time += (time2 - time1);
    }

    // Deletion the key from Map
    void deletion(int key) {
        if (isempty()) {
            System.out.println("Map is empty");
            return;
        }
        if (!contain(key)) {
            System.out.println("Key is not present in Map");
            return;
        }
        int i = hash1(key);
        while (keys[i] != key) {
            i = (i + hash2(key)) % capacity;
        }
        keys[i] = -1;
        vals[i] = 0;
        size--;
        System.out.println("Key is deleted");

    }

    // display the Keys and Value
    void display() {
        for (int i = 0; i < capacity; i++) {
            if (keys[i] != -1) {
                System.out.println(keys[i] + "->" + vals[i]);
            }

        }

    }

    public static void main(String[] args) {

        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter the Capacity of Map");
            int capacity = input.nextInt();
            Doublehashing doublehashing = new Doublehashing(capacity);
            int choice;
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
                choice = input.nextInt();
                System.out.println();
                switch (choice) {
                    case 1:
                        System.out.println("Enter the key");
                        int k = input.nextInt();
                        System.out.println("Enter the Value");
                        int val = input.nextInt();
                        doublehashing.insertion(k, val);
                        break;

                    case 2:
                        System.out.println("Enter the key");
                        int key = input.nextInt();
                        if (doublehashing.isempty()) {
                            System.out.println("Map is empty");
                            break;
                        }
                        int value = doublehashing.getval(key);
                        if (value != -1) {
                            System.out.println("Value of the key is - " + value);
                        } else {
                            System.out.println("Key is not present in Map");
                        }
                        break;

                    case 3:
                        System.out.println("Enter the key");
                        int delkey = input.nextInt();
                        doublehashing.deletion(delkey);
                        break;

                    case 4:
                        if (doublehashing.isempty()) {
                            System.out.println("Map is empty");
                        } else {
                            System.out.println("Display the Keys and Values of Map");
                            System.out.println();
                            System.out.println("Keys->Values");
                            System.out.println();
                            doublehashing.display();
                        }
                        break;

                    case 5:
                        System.out.println("Size of Hashmap is - " + doublehashing.getsize());
                        break;

                    case 6:
                        doublehashing.clear();
                        System.out.println("Data is deleted from Map");
                        break;

                    case 7:
                        if (doublehashing.isempty()) {
                            System.out.println("Map is empty");
                        } else {
                            System.out.println("Map is not empty");
                        }
                        break;

                    case 8:
                        System.out.println("Collision - " + doublehashing.collision);
                        break;

                    case 9:
                        System.out.println("Total time take in Insertion is - " + doublehashing.time);
                        break;
                    case 10:
                        System.out.println("Thank You");
                        break;

                    default:
                        System.out.println("Invalid Choice");
                        break;
                }

            } while (choice != 10);
        }

    }

}
