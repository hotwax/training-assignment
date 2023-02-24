package com.java.demo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class chaining<K, V> {

    // array of entry
    Entry<K, V>[] table;
    int capacity = 4; // default size of array
    int collision = 0; // to calculate number of collision

    // class Entry for key and value
    static class Entry<K, V> {

        K key;
        V value;
        Entry<K, V> next;

        // Constructor
        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    // Constructor
    public chaining() {
        table = new Entry[capacity];
    }

    // Method to put data in HashMap
    public void put(K newKey, V data) {
        if (newKey == null)
            return; // does not allow to store null.

        // calculate hash of key.
        int hash = hash(newKey);
        // create new entry.
        Entry<K, V> newEntry = new Entry<K, V>(newKey, data, null);

        // if table location does not contain any entry, store entry there.
        if (table[hash] != null && table[hash].key == newKey) {
            table[hash].value = data;
            return;
        }
        if (table[hash] == null) {
            table[hash] = newEntry;
        } else {
            Entry<K, V> current = table[hash];
            while (current.next != null) {
                if (current.key == newKey) {

                    current.value = data;
                    return;
                }

                current = current.next;
            }
            current.next = newEntry;
            collision++;
        }
    }

    // method to get the value form the key
    public V get(K key) {
        int hash = hash(key);
        if (table[hash] == null) { // if array is empty
            return null; // return null
        } else {
            Entry<K, V> temp = table[hash]; // creating new data member
            while (temp != null) {
                if (temp.key.equals(key))
                    return temp.value;
                temp = temp.next; // return value corresponding to key.
            }
            return null; // returns null if key is not found.
        }
    }

    // method to remove the key value from the HashMap
    public boolean remove(K deleteKey) {

        int hash = hash(deleteKey);

        if (table[hash] == null) { // if the table is totally empty then return false
            return false;
        } else {
            Entry<K, V> previous = null;
            Entry<K, V> current = table[hash];

            while (current != null) { // we have reached last entry node of bucket.
                if (current.key.equals(deleteKey)) {
                    if (previous == null) { // delete first entry node.
                        table[hash] = table[hash].next;
                        return true;
                    } else {
                        previous.next = current.next;
                        return true;
                    }
                }
                previous = current;
                current = current.next;
            }
            return false;
        }

    }

    // Method to display the HashMap

    void display() {
        System.out.println("Hashmap :");
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                Entry<K, V> entry = table[i];
                while (entry != null) {
                    System.out.println(entry.key + "->" + entry.value);
                    entry = entry.next;
                }
            }
        }

    }

    // Method to Search the value in a HashMap

    public void search(V value) {
        boolean isfound = false;
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                Entry<K, V> entry = table[i];
                while (entry != null) {
                    if (value == entry.value) {
                        System.out.println(entry.key + "->" + entry.value + " is present in the hash map");
                        isfound = true;
                    }
                    entry = entry.next;
                }
            }
        }

    }

    // method to calculate hash value
    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public static void main(String[] args) {
        // Creating the object of class chaining

        chaining<Integer, Integer> chmap = new chaining<Integer, Integer>();
        boolean flag = true;
        // running the while loop till flag is false
        long t1 = System.currentTimeMillis(); // Variable To calculate the time taken at the start.
        while (flag) {
            System.out.println("Choose an option from the Dashboard given below:\n");
            System.out.println("1. Insert a key value in Hashmap");
            System.out.println("2. Delete a key value in Hashmap");
            System.out.println("3. Get value of the key");
            System.out.println("4. Display the Hashmap ");
            System.out.println("5. Search the value in a Hashmap");
            System.out.println("6. Total collision ");
            System.out.println("7. Total time taken");
            System.out.println("8. EXIT");
            System.out.println();
            Scanner sc = new Scanner(System.in);
            int ch = sc.nextInt();
            if (ch < 1 || ch > 8) {
                System.out.println("Kindly enter the correct option.");
            } else {
                // Taking the choice from the user and performing the corresponding operation
                try {
                    switch (ch) {
                        case 1:

                            System.out.println("Enter the key and value");
                            int key = sc.nextInt();
                            int value = sc.nextInt();
                            chmap.put(key, value);
                            chmap.display();

                            break;

                        case 2:
                            System.out.println("Enter key to delete the value from the hashmap");
                            int key_delete = sc.nextInt();
                            chmap.remove(key_delete);
                            chmap.display();
                            break;

                        case 3:
                            System.out.println("Enter the key whose value you want");
                            int key_value = sc.nextInt();
                            System.out.println(chmap.get(key_value));
                            break;

                        case 4:
                            chmap.display();
                            break;

                        case 5:
                            int search_value = sc.nextInt();

                            chmap.search(search_value);
                            break;

                        case 6:
                            System.out.println(chmap.collision);
                            break;

                        case 7:
                            long t2 = System.currentTimeMillis();
                            System.out.println("Time in milli seconds is " + (t2 - t1));

                        case 8:
                            flag = false;
                            break;

                    }

                    if (flag == false) {
                        System.out.println("Exiting program");
                    }
                } catch (InputMismatchException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        }

    }

}
