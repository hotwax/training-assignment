package Randomprob;
import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

//Random probing class
public class RandomProbing {
    private int capacity;
    private int keys[];
    private int values[];
    private int randomNumbers[];
    private int size;
    int collision;
    long time;
    private Set<Integer> setForUniqueNumber = new LinkedHashSet<>();

    // Constructor
    RandomProbing(int totalSize) {
        capacity = totalSize;
        keys = new int[capacity];
        values = new int[capacity];
        size = 0;
        // initialise the array of Key to -1
        for (int index = 0; index < capacity; index++) {
            keys[index] = -1;
        }
        Random random = new Random();
        // store the unique random number in set
        while (setForUniqueNumber.size() < capacity) {
            Integer next = random.nextInt(capacity);
            setForUniqueNumber.add(next);
        }
        randomNumbers = new int[capacity];
        randomNumbers[0] = 0;
        int fetchIndex = 1;
        // store the random number in array
        // for access the element directly from any index
        for (int s : setForUniqueNumber) {
            if (s != 0) {
                randomNumbers[fetchIndex] = s;
                fetchIndex++;
            }
        }

        // initialise the collision and time
        collision = 0;
        time = 0;
    }

    // get the current time in millis
    private long getTime() {
        return System.currentTimeMillis();
    }

    // Delete the whole data from Map
    void clear() {
        size = 0;
        keys = new int[capacity];
        values = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            keys[i] = -1;
        }
    }

    // hash1 find the index of key with the help of random fuction
    // And compress the index under the capapcity
    private int randomNum(int i) {
        return randomNumbers[i];
    }

    private int hash1(int key) {
        return key % capacity;
    }

    // get the size of Map
    int getSize() {
        return size;
    }

    // check the Map is full or not
    boolean isFull() {
        return size == capacity;
    }

    // check the Map is empty or not
    boolean isEmpty() {
        return size == 0;
    }

    // check the key is present in the Map or not.
    boolean contain(int key) {
        if (getValue(key) != -1) {
            return true;
        } else {
            return false;
        }
    }

    // get the value of key from the Map
    public int getValue(int key) {
        if (isEmpty()) {
            return -1;
        }
        int prob = hash1(key);
        int index = prob;
        int randomIndex = 1;
        // if map is full
        if (isFull()) {
            while (keys[prob] != key && randomIndex < capacity) {
                // if key is already present in map so return value of key
                if (keys[prob] == key) {
                    return values[prob];
                }
                prob = (index + randomNum(randomIndex)) % capacity;
                randomIndex++;
            }
            if (keys[prob] == key) {
                return values[prob];
            } else {
                return -1;
            }

        }
        // if map is not full
        else {
            while (keys[prob] != key && randomIndex < capacity) {
                prob = (index + randomNum(randomIndex)) % capacity;
                randomIndex++;
            }
            if (keys[prob] == key) {
                return values[prob];
            }
        }
        return -1;
    }

    // insert the Keys and Values in Map
    void insertion(int key, int val) {
        if (isFull()) {
            if (!contain(key)) {
                System.out.println("Map is full");
                return;
            }
        }
        long initialTime = getTime();
        int prob = hash1(key);
        int index = prob;
        int randomIndex = 1;
        while (keys[prob] != -1 && randomIndex < capacity) {
            if (keys[prob] == key) {
                break;
            }
            prob = (index + randomNum(randomIndex)) % capacity;
            randomIndex++;
            collision++;
        }
        if (keys[prob] == key) {
            values[prob] = val;
        } else {
            keys[prob] = key;
            values[prob] = val;
            size++;
        }
        long finalTime = getTime();
        time += finalTime - initialTime;
    }

    // deletion of Given Key if its present in Map
    Integer deletion(int key) {
        if (isEmpty()) {
            return -1;
        }
        if (!contain(key)) {
            return -1;
        }
        int prob = hash1(key);
        int index = prob;
        int randomindex = 1;
        while (keys[prob] != key && randomindex < capacity) {
            prob = (index + randomNum(randomindex)) % capacity;
            randomindex++;
        }
        int tempDeletedKey = keys[prob];
        keys[prob] = -1;
        values[prob] = 0;
        size--;
        return tempDeletedKey;
    }

    // Display the data of Map
    void display() {
        if (isEmpty()) {
            System.out.println("Map is empty");
            return;
        }
        for (int prob = 0; prob < capacity; prob++) {
            if (keys[prob] != -1) {
                System.out.println(keys[prob] + "->" + values[prob]);
            }
        }
    }

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter the Capacity of Map");
            int capacity = input.nextInt();
            RandomProbing randomProbing = new RandomProbing(capacity);
            String choice;
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
                choice = input.next();
                System.out.println();
                switch (choice) {
                    case "1":
                        System.out.println("Enter the key");
                        int key = input.nextInt();
                        System.out.println("Enter the Value");
                        int val = input.nextInt();
                        randomProbing.insertion(key, val);
                        break;

                    case "2":
                        System.out.println("Enter the key");
                        int getValOfKey = input.nextInt();
                        if (randomProbing.isEmpty()) {
                            System.out.println("Map is empty");
                            break;
                        }
                        int value = randomProbing.getValue(getValOfKey);
                        if (value != -1) {
                            System.out.println("Value of the key is - " + value);
                        } else {
                            System.out.println("Key is not present in Map");
                        }
                        break;

                    case "3":
                        System.out.println("Enter the key");
                        int delkey = input.nextInt();
                        if (randomProbing.isEmpty()) {
                            System.out.println("Map is empty");
                            break;
                        }
                        int deletedKey = randomProbing.deletion(delkey);
                        if (deletedKey != -1) {
                            System.out.println("deleted Key is -" + deletedKey);
                        } else {
                            System.out.println("key is not present in Map");
                        }
                        break;

                    case "4":
                        if (randomProbing.isEmpty()) {
                            System.out.println("Map is empty");
                        } else {
                            System.out.println("Display the Keys and Values of Map");
                            System.out.println();
                            System.out.println("Keys->Values");
                            System.out.println();
                            randomProbing.display();
                        }
                        break;

                    case "5":
                        System.out.println("Size of Hashmap is - " + randomProbing.getSize());
                        break;

                    case "6":
                        randomProbing.clear();
                        System.out.println("Data is deleted from Map");
                        break;

                    case "7":
                        if (randomProbing.isEmpty()) {
                            System.out.println("Map is empty");
                        } else {
                            System.out.println("Map is not empty");
                        }
                        break;

                    case "8":
                        System.out.println("Collision - " + randomProbing.collision);
                        break;

                    case "9":
                        System.out.println("Time taken - " + randomProbing.time);
                        break;

                    case "10":
                        System.out.println("Thank You");
                        break;

                    default:
                        System.out.println("Invalid Choices");
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