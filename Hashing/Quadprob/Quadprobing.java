package Quadprob;

import java.util.Scanner;

import java.lang.System;

public class Quadprobing {
    int capacity;
    String keys[];
    String vals[];
    int currsize;
    int collision;
    long time;

    // constructor
    Quadprobing(int c) {
        capacity = c;
        keys = new String[capacity];
        vals = new String[capacity];
        currsize = 0;
        collision = 0;
        time = 0;
    }

    // covert the string in to integer
    // compress the value under the capacity
    int hash(String key) {
        int k = key.hashCode();
        return k % capacity;
    }

    // get current time in miilis
    long gettime() {
        return System.currentTimeMillis();
    }

    // clear the whole data
    public void clear() {
        currsize = 0;
        keys = new String[capacity];
        vals = new String[capacity];
        return;
    }

    // check the Map is full or not
    public boolean isfull() {
        return currsize == capacity;
    }

    // check the Map is empty or not
    public boolean isempty() {
        return currsize == 0;
    }

    // get the size of Map
    int getsize() {
        return currsize;
    }

    // check the key is present on Map or not
    public boolean contain(String key) {
        if (getval(key) != null) {
            return true;
        } else {
            return false;
        }
    }

    // Get the value from a key
    public String getval(String key) {

        if (isempty()) {

            return null;
        }
        int prob = hash(key);

        int quadindex = 0;
        while (keys[prob] != null && quadindex < capacity) {
            if (keys[prob].equals(key)) {
                return vals[prob];
            }
            quadindex++;
            prob = (prob + (quadindex * quadindex)) % capacity;

        }
        return null;

    }

    // Insert the Key and Value in Map
    void insertion(String key, String val) {
        if (isfull()) {
            if (!contain(key)) {
                System.out.println("Map is full");
                return;
            }
        }
        long time1 = gettime();

        int index = hash(key);

        int quadindex = 0;
        // no element is present in these index;
        while (quadindex < capacity) {
            if (keys[index] == null) {
                keys[index] = key;
                vals[index] = val;
                currsize++;
                long time2 = gettime();
                time += time2 - time1;
                return;
            }
            // the same key is present in these index so update the value
            if (keys[index].equals(key)) {
                vals[index] = val;
                long time2 = gettime();
                time += time2 - time1;
                return;
            }
            quadindex++;
            collision++;
            index = (index + quadindex * quadindex) % capacity;
            long time2 = gettime();
            time += time2 - time1;

        }

    }

    // display the Keys and Values from Map
    public void display() {
        for (int i = 0; i < capacity; i++) {
            if (keys[i] != null) {
                System.out.println(keys[i] + "->" + vals[i]);
            }
        }

    }

    // deleted the key from Map
    void deleted(String key) {
        if (!contain(key)) {
            System.out.println("Key is not present in Map");
            return;
        }

        // Find position key and delete
        int index = hash(key);
        int quadindex = 0;
        while (!key.equals(keys[index])) {
            quadindex++;
            index = (index + quadindex * quadindex) % capacity;
        }

        keys[index] = vals[index] = null;
        System.out.println("Key is deleted");

        // rehash all keys
        quadindex = 0;

        for (index = (index + quadindex * quadindex)
                % capacity; keys[index] != null; index = (index + quadindex * quadindex) % capacity) {
            String tmp1 = keys[index], tmp2 = vals[index];
            keys[index] = vals[index] = null;
            currsize--;
            quadindex++;
            insertion(tmp1, tmp2);
        }
        currsize--;
    }

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter the Capacity of Map");
            int capacity = input.nextInt();
            Quadprobing quad = new Quadprobing(capacity);
            int choice;
            do {
                System.out.println();
                System.out.println("Operations in HashMap(Quadratic Probing)");
                System.out.println();
                System.out.println("  1. Insert the key and value in Map");
                System.out.println("  2. Get the Values Using Key");
                System.out.println("  3. Delete the key in Map");
                System.out.println("  4. Display ");
                System.out.println("  5. GetSize");
                System.out.println("  6. Clear the Data ");
                System.out.println("  7. Check the Map is Empty or not");
                System.out.println("  8. Number of Collision");
                System.out.println("  9. Total time take in insertion");
                System.out.println("  10. Exit ");
                System.out.println();
                System.out.println("Enter the choice");
                System.out.println();
                choice = input.nextInt();
                System.out.println();
                switch (choice) {
                    case 1:
                        System.out.println("Enter the key");
                        String k = input.next();
                        System.out.println("Enter the Value");
                        String val = input.next();
                        quad.insertion(k, val);
                        break;

                    case 2:
                        System.out.println("Enter the key");
                        String key = input.next();
                        String value = quad.getval(key);
                        if (quad.isempty()) {
                            System.out.println("Map is empty");
                            break;
                        }
                        if (value != null) {
                            System.out.println("Value of the key is - " + value);
                        } else {
                            System.out.println("Key is not present in Map");
                        }
                        break;

                    case 3:
                        System.out.println("Enter the key");
                        String delkey = input.next();
                        if (quad.isempty()) {
                            System.out.println("Map is empty");
                            break;
                        }
                        quad.deleted(delkey);
                        break;

                    case 4:
                        if (quad.isempty()) {
                            System.out.println("Map is empty");
                        } else {
                            System.out.println("Display the Keys and Values of Map");
                            System.out.println();
                            System.out.println("Keys->Values");
                            System.out.println();
                            quad.display();
                        }
                        break;

                    case 5:
                        System.out.println("Size of Hashmap is - " + quad.getsize());
                        break;

                    case 6:
                        quad.clear();
                        System.out.println("Data is deleted from Map");
                        break;

                    case 7:
                        if (quad.isempty()) {
                            System.out.println("Map is empty");
                        } else {
                            System.out.println("Map is not empty");
                        }
                        break;

                    case 8:
                        System.out.println("Collision - " + quad.collision);
                        break;

                    case 9:
                        System.out.println("Time taken - " + quad.time);
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
