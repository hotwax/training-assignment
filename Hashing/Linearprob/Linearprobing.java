package Linearprob;

import java.util.Scanner;
import java.lang.System;

public class Linearprobing {

    int capacity;
    String keys[];
    String vals[];
    int currsize;
    int collision;
    long time;

    // constructor
    public Linearprobing(int c) {
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

    // get the size of map
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

    long gettime() {
        return System.currentTimeMillis();
    }

    // Get the value from a key
    public String getval(String key) {

        int prob = hash(key);
        int index = prob;
        while (keys[prob] != null) {
            if (keys[prob].equals(key)) {
                return vals[prob];
            }
            prob = (prob + 1) % capacity;
            if (index == prob) {
                return null;
            }
        }
        return null;

    }

    // Insert the Key and Value in Map
    public void insertion(String key, String val) {

        if (isfull()) {
            if (!contain(key)) {
                System.out.println("Map is Full");
                return;
            }

        }
        long time1 = gettime();
        int prob = hash(key);
        int index = prob;
        do {
            // no element is present in these index;
            if (keys[index] == null) {
                keys[index] = key;
                vals[index] = val;
                currsize++;
                long time2 = gettime();
                time += (time2 - time1);
                return;
            }
            if (keys[index].equals(key)) {
                vals[index] = val;
                long time2 = gettime();
                time += time2 - time1;
                return;
            }
            index = (index + 1) % capacity;
            collision++;
        } while (index != prob);
        long time2 = gettime();
        time += (time2 - time1);

    }

    // Display the data of Map
    public void display() {
        for (int prob = 0; prob < capacity; prob++) {
            if (keys[prob] != null) {
                System.out.println(keys[prob] + "->" + vals[prob]);
            }
        }

    }

    // Delete the key from Map
    void deleted(String key) {
        if (!contain(key)) {
            System.out.println("Key is not present in Map");
            return;
        }

        // Find position key and delete
        int prob = hash(key);
        while (!key.equals(keys[prob]))
            prob = (prob + 1) % capacity;
        keys[prob] = vals[prob] = null;
        System.out.println("Key is deleted");
        // rehash all keys
        for (prob = (prob + 1) % capacity; keys[prob] != null; prob = (prob + 1) % capacity) {
            String tmp1 = keys[prob], tmp2 = vals[prob];
            keys[prob] = vals[prob] = null;
            currsize--;
            insertion(tmp1, tmp2);
        }
        currsize--;
    }

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter the Capacity of Map");
            int capacity = input.nextInt();
            Linearprobing linear = new Linearprobing(capacity);
            int choice;
            do {
                System.out.println();
                System.out.println("Operations in HashMap(Linear Probing)");
                System.out.println();
                System.out.println("  1. Insert the key and value in Map");
                System.out.println("  2. Get the Values Using Key");
                System.out.println("  3. Delete the key in Map");
                System.out.println("  4. Display ");
                System.out.println("  5. GetSize");
                System.out.println("  6. Clear the Data ");
                System.out.println("  7. Check the Map is Empty or not");
                System.out.println("  8. Collision");
                System.out.println("  9 Total time take in insertion");
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

                        linear.insertion(k, val);
                        break;

                    case 2:
                        System.out.println("Enter the key");
                        String key = input.next();
                        String value = linear.getval(key);
                        if (linear.isempty()) {
                            System.out.println("Map is empty");
                            break;
                        }
                        if (value != null) {
                            System.out.println("Value of the key is - " + value);
                        } else {
                            System.out.println("Key is not present");
                        }
                        break;

                    case 3:
                        System.out.println("Enter the key");
                        String delkey = input.next();
                        if (linear.isempty()) {
                            System.out.println("Map is empty");
                            break;
                        }
                        linear.deleted(delkey);
                        break;

                    case 4:
                        if (linear.isempty()) {
                            System.out.println("Map is empty");
                        } else {
                            System.out.println("Display the Keys and Values of Map");
                            System.out.println();
                            System.out.println("Key->Value");
                            System.out.println();
                            linear.display();
                        }
                        break;

                    case 5:
                        System.out.println("Size of Hashmap is - " + linear.getsize());
                        break;

                    case 6:
                        linear.clear();
                        System.out.println("Data is deleted from Map");
                        break;

                    case 7:
                        if (linear.isempty()) {
                            System.out.println("Map is empty");
                        } else {
                            System.out.println("Map is not empty");
                        }
                        break;

                    case 8:
                        System.out.println("Collision - " + linear.collision);
                        break;

                    case 9:
                        System.out.println("Time Taken - " + linear.time);
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
