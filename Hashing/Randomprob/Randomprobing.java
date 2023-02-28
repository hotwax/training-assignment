package Randomprob;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Randomprobing {
    int capacity;
    boolean isnotprime[];
    int keys[];
    int vals[];
    int random[];
    int size;
    int collision;
    long time;
    Set<Integer> set = new LinkedHashSet<>();

    // Constructor
    Randomprobing(int c) {
        capacity = c;
        keys = new int[capacity];
        vals = new int[capacity];
        size = 0;
        // initialise the array of Key to -1
        for (int i = 0; i < capacity; i++) {
            keys[i] = -1;
        }

        Random r = new Random();
        // store the unique random number in set
        while (set.size() < capacity) {
            Integer next = r.nextInt(capacity);
            set.add(next);
        }
        random = new int[capacity];
        random[0] = 0;
        int i = 1;
        // store the random number in array
        // for access the element directly from any index
        for (int s : set) {
            if (s != 0) {
                random[i] = s;
                i++;
            }
        }

        // initialise the collision and time
        collision = 0;
        time = 0;
    }

    // get the current time in millis
    long gettime() {
        return System.currentTimeMillis();
    }

    // Delete the whole data from Map
    void clear() {
        size = 0;
        keys = new int[capacity];
        vals = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            keys[i] = -1;
        }
    }

    // hash1 find the index of key with the help of random fuction
    // And compress the index under the capapcity
    int randomnum(int i) {

        return random[i];
    }

    int hash1(int key) {
        return key % capacity;
    }

    // get the size of Map
    int getsize() {
        return size;
    }

    // check the Map is full or not
    boolean isfull() {
        return size == capacity;
    }

    // check the Map is empty or not
    boolean isempty() {
        return size == 0;
    }

    // check the key is present in the Map or not.
    boolean contain(int key) {
        if (getval(key) != -1) {
            return true;
        } else {
            return false;
        }
    }

    // get the value of key from the Map
    public int getval(int key) {
        if (isempty()) {
            return -1;
        }
        int prob = hash1(key);

        int index = prob;
        int randomindex = 1;
        // if map is full
        if (isfull()) {
            while (keys[prob] != key && randomindex < capacity) {
                // if key is already present in map so return value of key
                if (keys[prob] == key) {
                    return vals[prob];
                }
                prob = (index + randomnum(randomindex)) % capacity;
                randomindex++;
            }
            if (keys[prob] == key) {
                return vals[prob];
            } else {
                return -1;
            }

        }
        // if map is not full
        else {
            while (keys[prob] != -1 && randomindex < capacity) {
                if (keys[prob] == key) {
                    return vals[prob];
                }

                prob = (index + randomnum(randomindex)) % capacity;
                randomindex++;
            }
        }

        return -1;

    }

    // insert the Keys and Values in Map
    void insertion(int key, int val) {
        if (isfull()) {
            if (!contain(key)) {
                System.out.println("Map is full");
                return;
            }
        }
        long time1 = gettime();
        int prob = hash1(key);
        int index = prob;
        int randomindex = 1;
        while (keys[prob] != -1 && randomindex < capacity) {
            if (keys[prob] == key) {
                break;
            }

            prob = (index + randomnum(randomindex)) % capacity;
            randomindex++;
            collision++;
        }
        if (keys[prob] == key) {
            vals[prob] = val;
        } else {
            keys[prob] = key;
            vals[prob] = val;
            size++;
        }
        long time2 = gettime();
        time += time2 - time1;
    }

    // deletion of Given Key if its present in Map
    void deletion(int key) {
        if (isempty()) {
            System.out.println("Map is empty");
            return;
        }
        if (!contain(key)) {
            System.out.println("Key is not present in Map");
            return;
        }
        int prob = hash1(key);
        int index = prob;
        int randomindex = 1;
        while (keys[prob] != key && randomindex < capacity) {
            prob = (index + randomnum(randomindex)) % capacity;
            randomindex++;
        }
        keys[prob] = -1;
        vals[prob] = 0;
        size--;
        System.out.println("Key is deleted");

    }

    // Display the data of Map
    void display() {
        if (isempty()) {
            System.out.println("Map is empty");
            return;
        }

        for (int prob = 0; prob < capacity; prob++) {
            if (keys[prob] != -1) {
                System.out.println(keys[prob] + "->" + vals[prob]);
            }

        }

    }

    public static void main(String[] args) {

        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter the Capacity of Map");
            int capacity = input.nextInt();
            Randomprobing randomprobing = new Randomprobing(capacity);
            int choice;
            do {
                System.out.println();
                System.out.println("Operations in HashMap(Random hashing)");
                System.out.println();
                System.out.println("  1. Insert the key and value in Map");
                System.out.println("  2. Get the Values Using Key");
                System.out.println("  3. Delete the key in Map");
                System.out.println("  4. Display ");
                System.out.println("  5. GetSize");
                System.out.println("  6. Clear the Data ");
                System.out.println("  7. Check the Map is Empty or not");
                System.out.println("  8.Number of Collision");
                System.out.println("  9.Total time take in insertion");
                System.out.println("  10. Exit ");
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
                        randomprobing.insertion(k, val);
                        break;

                    case 2:
                        System.out.println("Enter the key");
                        int key = input.nextInt();
                        if (randomprobing.isempty()) {
                            System.out.println("Map is empty");
                            break;
                        }
                        int value = randomprobing.getval(key);
                        if (value != -1) {
                            System.out.println("Value of the key is - " + value);
                        } else {
                            System.out.println("Key is not present in Map");
                        }
                        break;

                    case 3:
                        System.out.println("Enter the key");
                        int delkey = input.nextInt();
                        randomprobing.deletion(delkey);
                        break;

                    case 4:
                        if (randomprobing.isempty()) {
                            System.out.println("Map is empty");
                        } else {
                            System.out.println("Display the Keys and Values of Map");
                            System.out.println();
                            System.out.println("Keys->Values");
                            System.out.println();
                            randomprobing.display();
                        }
                        break;

                    case 5:
                        System.out.println("Size of Hashmap is - " + randomprobing.getsize());
                        break;

                    case 6:
                        randomprobing.clear();
                        System.out.println("Data is deleted from Map");
                        break;

                    case 7:
                        if (randomprobing.isempty()) {
                            System.out.println("Map is empty");
                        } else {
                            System.out.println("Map is not empty");
                        }
                        break;

                    case 8:
                        System.out.println("Collision - " + randomprobing.collision);
                        break;

                    case 9:
                        System.out.println("Time taken - " + randomprobing.time);
                        break;

                    case 10:
                        System.out.println("Thank You");
                        break;

                    default:
                        System.out.println("Invalid Choices");
                        break;
                }

            } while (choice != 10);
        }

    }
}
