package Quadprob;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.System;

//Quadratic probing class
public class QuadraticProbing {
    private int capacity;
    private String keys[];
    private String values[];
    private int currSize;
    int collision;
    long time;

    // constructor
    QuadraticProbing(int totalsize) {
        capacity = totalsize;
        keys = new String[capacity];
        values = new String[capacity];
        currSize = 0;
        collision = 0;
        time = 0;
    }

    // covert the string in to integer
    // compress the value under the capacity
    private int hash(String key) {
        int hashKey = key.hashCode();
        return hashKey % capacity;
    }

    // get current time in miilis
    private long getTime() {
        return System.currentTimeMillis();
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

    // get the size of Map
    public int getSize() {
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

    // Get the value from a key
    public String getValue(String key) {
        if (isEmpty()) {
            return null;
        }
        int prob = hash(key);
        int quadIndex = 0;
        while (quadIndex < capacity) {
            if (keys[prob] == null) {
                quadIndex++;
                prob = (prob + (quadIndex * quadIndex)) % capacity;
            } else {
                if (keys[prob].equals(key)) {
                    return values[prob];
                }
                quadIndex++;
                prob = (prob + (quadIndex * quadIndex)) % capacity;
            }
        }
        return null;
    }

    // Insert the Key and Value in Map
    void insertion(String key, String val) {
        if (isFull()) {
            if (!contain(key)) {
                System.out.println("Map is full");
                return;
            }
        }
        long initialTime = getTime();
        int index = hash(key);
        int quadIndex = 0;

        // no element is present in these index;
        while (quadIndex < capacity) {
            if (keys[index] == null) {
                keys[index] = key;
                values[index] = val;
                currSize++;
                long finalTime = getTime();
                time += finalTime - initialTime;
                return;
            }

            // the same key is present in these index so update the value
            if (keys[index].equals(key)) {
                values[index] = val;
                long time2 = getTime();
                time += time2 - initialTime;
                return;
            }
            quadIndex++;
            collision++;
            index = (index + quadIndex * quadIndex) % capacity;
            long time2 = getTime();
            time += time2 - initialTime;
        }
    }

    // display the Keys and Values from Map
    public void display() {
        for (int index = 0; index < capacity; index++) {
            if (keys[index] != null) {
                System.out.println(keys[index] + "->" + values[index]);
            }
        }
    }

    // deleted the key from Map
    String deleted(String key) {
        if (!contain(key)) {
            return null;
        }

        // Find position key and delete
        int index = hash(key);
        int quadindex = 0;
        while (!key.equals(keys[index])) {
            quadindex++;
            index = (index + quadindex * quadindex) % capacity;
        }
        String tempDeletedKey = keys[index];
        keys[index] = values[index] = null;

        // rehash all keys
        quadindex = 0;
        for (index = (index + quadindex * quadindex)
                % capacity; keys[index] != null; index = (index + quadindex * quadindex) % capacity) {
            String tempKey = keys[index], tempValue = values[index];
            keys[index] = values[index] = null;
            currSize--;
            quadindex++;
            insertion(tempKey, tempValue);
        }
        currSize--;
        return tempDeletedKey;
    }

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter the Capacity of Map");
            int capacity = input.nextInt();
            QuadraticProbing quadratic = new QuadraticProbing(capacity);
            String choice;
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
                choice = input.next();
                System.out.println();
                switch (choice) {
                    case "1":
                        System.out.println("Enter the key");
                        String key = input.next();
                        System.out.println("Enter the Value");
                        String val = input.next();
                        quadratic.insertion(key, val);
                        break;

                    case "2":
                        System.out.println("Enter the key");
                        String getValOfKey = input.next();
                        String value = quadratic.getValue(getValOfKey);
                        if (quadratic.isEmpty()) {
                            System.out.println("Map is empty");
                            break;
                        }
                        if (value != null) {
                            System.out.println("Value of the key is - " + value);
                        } else {
                            System.out.println("Key is not present in Map");
                        }
                        break;

                    case "3":
                        System.out.println("Enter the key");
                        String delkey = input.next();
                        if (quadratic.isEmpty()) {
                            System.out.println("Map is empty");
                            break;
                        }
                        String deletedKey = quadratic.deleted(delkey);
                        if (deletedKey != null) {
                            System.out.println("deleted Key is -" + deletedKey);
                        } else {
                            System.out.println("Key is not present in Map");
                        }
                        break;

                    case "4":
                        if (quadratic.isEmpty()) {
                            System.out.println("Map is empty");
                        } else {
                            System.out.println("Display the Keys and Values of Map");
                            System.out.println();
                            System.out.println("Keys->Values");
                            System.out.println();
                            quadratic.display();
                        }
                        break;

                    case "5":
                        System.out.println("Size of Hashmap is - " + quadratic.getSize());
                        break;

                    case "6":
                        quadratic.clear();
                        System.out.println("Data is deleted from Map");
                        break;

                    case "7":
                        if (quadratic.isEmpty()) {
                            System.out.println("Map is empty");
                        } else {
                            System.out.println("Map is not empty");
                        }
                        break;

                    case "8":
                        System.out.println("Collision - " + quadratic.collision);
                        break;

                    case "9":
                        System.out.println("Time taken - " + quadratic.time);
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