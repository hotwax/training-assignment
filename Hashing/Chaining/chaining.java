package Chaining;

import java.util.Scanner;
import java.lang.System;

class Node {
    String key;
    int val;
    Node next;

    Node(String k, int v) {
        this.key = k;
        this.val = v;
        this.next = null;
    }
}

class Hashmap {

    private Node arr[];
    private int capacity;
    private int size;
    double loadfactor;
    int collision;
    long time;

    // constructor
    Hashmap(int capacity) {
        this.capacity = capacity;
        arr = new Node[capacity];
        this.size = 0;
        collision = 0;
        time = 0;
    }

    // Deleted Whole data from Hashmap
    void clear() {
        size = 0;
        arr = new Node[capacity];
    }

    // Rehashing is required whenever the load factor is grater then 0.75.
    private void rehashing() {
        Node temp[] = arr;
        int c = capacity;
        capacity = capacity * 2;
        arr = new Node[capacity];
        size = 0;
        for (int i = 0; i < c; i++) {
            Node t = temp[i];
            while (t != null) {
                put(t.key, t.val);
                t = t.next;
            }

        }

    }

    long getTime() {
        return System.currentTimeMillis();
    }

    // Convert the Keys in to integer value which lies in capacity
    private int hashcode(String key) {
        int k = key.hashCode();

        return Math.abs(k) % capacity;
    }

    // Insert and update the value
    void put(String key, int val) {
        long time1 = getTime();
        int index = hashcode(key);
        Node newnode = new Node(key, val);
        // Direct put the Key in index
        if (arr[index] == null) {
            arr[index] = newnode;
            size++;
            loadfactor = size / capacity;
            if (loadfactor > 0.75) {
                rehashing();
            }
            return;
        }

        // Check the key is present so update the value
        // or key is not present so insert int Map
        if (arr[index] != null) {

            Node temp = arr[index];
            while (!temp.key.equals(key) && temp.next != null) {
                temp = temp.next;

            }
            if (temp.next == null && !temp.key.equals(key)) {
                temp.next = newnode;
                size++;
                loadfactor = size / capacity;
                collision++;
                if (loadfactor > 0.75) {
                    rehashing();
                }
            } else if (temp.key.equals(key)) {
                temp.val = val;
            }

        }

        long time2 = getTime();

        time += (time2 - time1);

    }

    // Display the elements of HashMap

    void display() {

        for (int i = 0; i < capacity; i++) {
            Node temp = arr[i];
            while (temp != null) {
                System.out.println(temp.key + "->" + temp.val);
                temp = temp.next;
            }

        }

    }

    boolean isempty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }

    }

    // Delete the key from Map if its present
    void delete(String key) {
        if (size == 0) {
            System.out.println("Map is empty");
            return;
        }
        int index = hashcode(key);

        if (arr[index] == null) {
            System.out.println("Key is not present in Map");
        } else if (arr[index].key.equals(key) && arr[index].next == null) {
            arr[index] = null;
            size--;
        } else if (arr[index].key.equals(key) && arr[index].next != null) {
            arr[index] = arr[index].next;
            size--;
        } else if (arr[index].next != null) {
            Node temp1 = arr[index];
            Node temp2 = temp1.next;
            while (!temp2.key.equals(key) && temp2 != null) {
                temp1 = temp2;
                temp2 = temp2.next;
            }
            if (temp2 == null) {
                System.out.println("Key is not present in Map");
            } else {
                temp1.next = temp2.next;
                size--;
            }
        } else {
            System.out.println("Key is not present");

        }

    }

    // get size of Map
    int Size() {
        if (size == 0) {
            return 0;
        } else {
            return size;
        }
    }

    // get the value fro key
    int get(String key) {
        if (size == 0) {
            System.out.println("Map is empty");
            return -1;
        }
        int index = hashcode(key);
        if (arr[index] == null) {
            System.out.println("Key is not present in Map");
            return -1;
        } else {
            Node temp = arr[index];
            while (temp != null) {
                if (temp.key.equals(key)) {
                    return temp.val;
                }

                temp = temp.next;
            }

            System.out.println("Key is not present in Map");
            return -1;

        }

    }

}

public class chaining {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter the capacity of HashMap");
            int capacity = input.nextInt();
            Hashmap hashmap = new Hashmap(capacity);
            int choice;
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
                choice = input.nextInt();
                System.out.println();
                switch (choice) {
                    case 1:
                        System.out.println("Enter the key");
                        String k = input.next();
                        System.out.println("Enter the Value");
                        int val = input.nextInt();
                        hashmap.put(k, val);
                        break;

                    case 2:
                        System.out.println("Enter the key");
                        String key = input.next();
                        int value = hashmap.get(key);
                        if (value != -1) {
                            System.out.println("Value of the key is - " + value);
                        }
                        break;

                    case 3:
                        System.out.println("Enter the key");
                        String delkey = input.next();
                        hashmap.delete(delkey);
                        break;

                    case 4:
                        if (hashmap.isempty()) {
                            System.out.println("Map is empty");
                        } else {
                            System.out.println("Display the Keys and Values of Map");
                            System.out.println();
                            System.out.println("Keys->Values");
                            System.out.println();
                            hashmap.display();
                        }
                        break;

                    case 5:
                        System.out.println("Size of Hashmap is - " + hashmap.Size());
                        break;

                    case 6:
                        hashmap.clear();
                        System.out.println("Data is deleted from Map");
                        break;

                    case 7:
                        if (hashmap.isempty()) {
                            System.out.println("Map is empty");
                        } else {
                            System.out.println("Map is not empty");
                        }
                        break;

                    case 8:
                        System.out.println("collision - " + hashmap.collision);
                        break;

                    case 9:
                        System.out.println("Total Time take in insertion - " + hashmap.time);
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
