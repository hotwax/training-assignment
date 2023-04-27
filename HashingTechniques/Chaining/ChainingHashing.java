package Chaining;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.System;

//Node class
class Node {
    String key;
    int val;
    Node next;

    // constructor
    Node(String key, int val) {
        this.key = key;
        this.val = val;
        this.next = null;
    }
}

//Chaining class
class ChainingHashing {
    private Node keyValuePairs[];
    private int capacity;
    private int size;
    double loadFactor;
    int collision;
    long time;

    // constructor
    ChainingHashing(int capacity) {
        this.capacity = capacity;
        keyValuePairs = new Node[capacity];
        this.size = 0;
        collision = 0;
        time = 0;
    }

    // Deleted Whole data from Hashmap
    void clear() {
        size = 0;
        keyValuePairs = new Node[capacity];
    }

    // Rehashing is required whenever the load factor is grater then 0.75.
    private void reHashing() {
        Node tempKeyValuePairs[] = keyValuePairs;
        int prevCapacity = capacity;
        capacity = capacity * 2;
        keyValuePairs = new Node[capacity];
        size = 0;
        for (int index = 0; index < prevCapacity; index++) {
            Node tempRefKeyValue = tempKeyValuePairs[index];
            while (tempRefKeyValue != null) {
                insertion(tempRefKeyValue.key, tempRefKeyValue.val);
                tempRefKeyValue = tempRefKeyValue.next;
            }
        }
    }

    //get current time 
    long getTime() {
        return System.currentTimeMillis();
    }

    // Convert the Keys in to integer value which lies in capacity
    private int hashcode(String key) {
        int hashKey = key.hashCode();

        return Math.abs(hashKey) % capacity;
    }

    // Insert and update the value
    void insertion(String key, int val) {
        long initialTime = getTime();
        int index = hashcode(key);
        Node newNode = new Node(key, val);
        // Direct put the Key in index
        if (keyValuePairs[index] == null) {
            keyValuePairs[index] = newNode;
            size++;
            loadFactor = size / capacity;
            if (loadFactor > 0.75) {
                reHashing();
            }
            return;
        }

        // Check the key is present so update the value
        // or key is not present so insert int Map
        if (keyValuePairs[index] != null) {
            Node tempKeyValue = keyValuePairs[index];
            while (!tempKeyValue.key.equals(key) && tempKeyValue.next != null) {
                tempKeyValue = tempKeyValue.next;
            }
            if (tempKeyValue.next == null && !tempKeyValue.key.equals(key)) {
                tempKeyValue.next = newNode;
                size++;
                loadFactor = size / capacity;
                collision++;
                if (loadFactor > 0.75) {
                    reHashing();
                }
            } else if (tempKeyValue.key.equals(key)) {
                tempKeyValue.val = val;
            }
        }
        long finalTime = getTime();
        time += (finalTime - initialTime);
    }

    // Display the elements of HashMap
    void display() {
        for (int index = 0; index < capacity; index++) {
            Node tempKeyValue = keyValuePairs[index];
            while (tempKeyValue != null) {
                System.out.println(tempKeyValue.key + "->" + tempKeyValue.val);
                tempKeyValue = tempKeyValue.next;
            }
        }
    }

    // check the map is empty or not
    boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    // Delete the key from Map if its present
    String delete(String key) {
        if (size == 0) {
            return null;
        }
        int index = hashcode(key);
        Node tempKeyValue = keyValuePairs[index];
        if (keyValuePairs[index] == null) {
            return null;
        } else if (keyValuePairs[index].key.equals(key) && keyValuePairs[index].next == null) {
            keyValuePairs[index] = null;
            size--;
            return tempKeyValue.key;
        } else if (keyValuePairs[index].key.equals(key) && keyValuePairs[index].next != null) {
            keyValuePairs[index] = keyValuePairs[index].next;
            size--;
            return tempKeyValue.key;
        } else if (keyValuePairs[index].next != null) {
            Node tempNextKeyValue = tempKeyValue.next;
            while (!tempNextKeyValue.key.equals(key) && tempNextKeyValue != null) {
                tempKeyValue = tempNextKeyValue;
                tempNextKeyValue = tempNextKeyValue.next;
            }
            if (tempNextKeyValue == null) {
                return null;
            } else {
                Node tempRefKeyValue = tempNextKeyValue;
                tempKeyValue.next = tempNextKeyValue.next;
                size--;
                return tempRefKeyValue.key;
            }
        }
        return null;
    }

    // get size of Map
    int getSize() {
        if (size == 0) {
            return 0;
        } else {
            return size;
        }
    }

    // get the value fro key
    int getValue(String key) {
        if (size == 0) {
            return -1;
        }
        int index = hashcode(key);
        if (keyValuePairs[index] == null) {
            return -1;
        } else {
            Node tempKeyValue = keyValuePairs[index];
            while (tempKeyValue != null) {
                if (tempKeyValue.key.equals(key)) {
                    return tempKeyValue.val;
                }
                tempKeyValue = tempKeyValue.next;
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter the capacity of HashMap");
            int capacity = input.nextInt();
            ChainingHashing hashMap = new ChainingHashing(capacity);
            String choice;
            do {
                System.out.println();
                System.out.println("Operations in HashMap(Sequential Chaining)");
                System.out.println();
                System.out.println("  1. Insert the key and value in Map");
                System.out.println("  2. Get the Values Using Key");
                System.out.println("  3. Delete the key in Map");
                System.out.println("  4. Display ");
                System.out.println("  5. GetSize");
                System.out.println("  6.Clear the Data ");
                System.out.println("  7.Check the Map is Empty or not");
                System.out.println("  8.Number of Collision");
                System.out.println("  9. Total time taken for insertion");
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
                        int val = input.nextInt();
                        hashMap.insertion(key, val);
                        break;

                    case "2":
                        System.out.println("Enter the key");
                        String getValOfKey = input.next();
                        if (hashMap.isEmpty()) {
                            System.out.println("Map is empty");
                            break;
                        }
                        int value = hashMap.getValue(getValOfKey);
                        if (value != -1) {
                            System.out.println("Value of the key is - " + value);
                        } else {
                            System.out.println("Key is not Prsent");
                        }
                        break;

                    case "3":
                        System.out.println("Enter the key");
                        String delkey = input.next();
                        if (hashMap.isEmpty()) {
                            System.out.println("Map is Empty");
                            break;
                        }
                        String deletedKey = hashMap.delete(delkey);
                        if (deletedKey == null) {
                            System.out.println("Key is not present in Map");
                        } else {
                            System.out.println("Deleted Key is -" + deletedKey);
                        }
                        break;

                    case "4":
                        if (hashMap.isEmpty()) {
                            System.out.println("Map is empty");
                        } else {
                            System.out.println("Display the Keys and Values of Map");
                            System.out.println();
                            System.out.println("Keys->Values");
                            System.out.println();
                            hashMap.display();
                        }
                        break;

                    case "5":
                        System.out.println("Size of Hashmap is - " + hashMap.getSize());
                        break;

                    case "6":
                        hashMap.clear();
                        System.out.println("Data is deleted from Map");
                        break;

                    case "7":
                        if (hashMap.isEmpty()) {
                            System.out.println("Map is empty");
                        } else {
                            System.out.println("Map is not empty");
                        }
                        break;

                    case "8":
                        System.out.println("collision - " + hashMap.collision);
                        break;

                    case "9":
                        System.out.println("Total Time take in insertion - " + hashMap.time);
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