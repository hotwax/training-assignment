package Linearprob;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.System;

//Linear probing class 
public class LinearProbing {
    private int capacity;
    private String keys[];
    private String values[];
    private int currSize;
    int collision;
    long time;

    // constructor
    public LinearProbing(int totalSize) {
        capacity = totalSize;
        keys = new String[totalSize];
        values = new String[totalSize];
        currSize = 0;
        collision = 0;
        time = 0;
    }

    // covert the string in to integer
    // compress the value under the capacity
    int hash(String key) {
        int hashKey = key.hashCode();
        return hashKey % capacity;
    }

    // clear the whole data
    public void clear() {
        currSize = 0;
        keys = new String[capacity];
        values = new String[capacity];
        return;
    }

    // check the Map is full or not
    public boolean isFull() {
        return currSize == capacity;
    }

    // check the Map is empty or not
    public boolean isEmpty() {
        return currSize == 0;
    }

    // get the size of map
    int getSize() {
        return currSize;
    }

    // check the key is present on Map or not
    public boolean contain(String key) {
        if (getValue(key) != null) {
            return true;
        } else {
            return false;
        }
    }

    //get current time 
    private long getTime() {
        return System.currentTimeMillis();
    }

    // Get the value from a key
    public String getValue(String key) {
        if (isEmpty()) {
            return null;
        }
        int prob = hash(key);
        int index = prob;
        while (keys[prob] != null) {
            if (keys[prob].equals(key)) {
                return values[prob];
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
        if (isFull()) {
            if (!contain(key)) {
                System.out.println("Map is Full");
                return;
            }
        }
        long initialTime = getTime();
        int prob = hash(key);
        int index = prob;
        do {
            // no element is present in these index;
            if (keys[index] == null) {
                keys[index] = key;
                values[index] = val;
                currSize++;
                long finalTime = getTime();
                time += (finalTime - initialTime);
                return;
            }
            if (keys[index].equals(key)) {
                values[index] = val;
                long time2 = getTime();
                time += time2 - initialTime;
                return;
            }
            index = (index + 1) % capacity;
            collision++;
        } while (index != prob);
        long time2 = getTime();
        time += (time2 - initialTime);
    }

    // Display the data of Map
    public void display() {
        for (int prob = 0; prob < capacity; prob++) {
            if (keys[prob] != null) {
                System.out.println(keys[prob] + "->" + values[prob]);
            }
        }
    }

    // Delete the key from Map
    String deleted(String key) {
        if (isEmpty()) {
            return null;
        }
        if (!contain(key)) {
            return null;
        }

        // Find position key and delete
        int prob = hash(key);
        while (!key.equals(keys[prob])) {
            prob = (prob + 1) % capacity;
        }
        String tempKey = keys[prob];
        keys[prob] = values[prob] = null;
        // rehash all keys
        for (prob = (prob + 1) % capacity; keys[prob] != null; prob = (prob + 1) % capacity) {
            String tmpKey = keys[prob], tmpValue = values[prob];
            keys[prob] = values[prob] = null;
            currSize--;
            insertion(tmpKey, tmpValue);
        }
        currSize--;
        return tempKey;
    }

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter the Capacity of Map");
            int capacity = input.nextInt();
            LinearProbing linear = new LinearProbing(capacity);
            String choice;
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
                choice = input.next();
                System.out.println();
                switch (choice) {
                    case "1":
                        System.out.println("Enter the key");
                        String key = input.next();
                        System.out.println("Enter the Value");
                        String val = input.next();
                        linear.insertion(key, val);
                        break;

                    case "2":
                        System.out.println("Enter the key");
                        String keyForVal = input.next();
                        String value = linear.getValue(keyForVal);
                        if (linear.isEmpty()) {
                            System.out.println("Map is empty");
                            break;
                        }
                        if (value != null) {
                            System.out.println("Value of the key is - " + value);
                        } else {
                            System.out.println("Key is not present");
                        }
                        break;

                    case "3":
                        System.out.println("Enter the key");
                        String delkey = input.next();
                        if (linear.isEmpty()) {
                            System.out.println("Map is empty");
                            break;
                        }
                        String Key = linear.deleted(delkey);
                        if (Key != null) {
                            System.out.println("Deleted data is - " + Key);
                        } else {
                            System.out.println("Key is not present");
                        }
                        break;

                    case "4":
                        if (linear.isEmpty()) {
                            System.out.println("Map is empty");
                        } else {
                            System.out.println("Display the Keys and Values of Map");
                            System.out.println();
                            System.out.println("Key->Value");
                            System.out.println();
                            linear.display();
                        }
                        break;

                    case "5":
                        System.out.println("Size of Hashmap is - " + linear.getSize());
                        break;

                    case "6":
                        linear.clear();
                        System.out.println("Data is deleted from Map");
                        break;

                    case "7":
                        if (linear.isEmpty()) {
                            System.out.println("Map is empty");
                        } else {
                            System.out.println("Map is not empty");
                        }
                        break;

                    case "8":
                        System.out.println("Collision - " + linear.collision);
                        break;

                    case "9":
                        System.out.println("Time Taken - " + linear.time);
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