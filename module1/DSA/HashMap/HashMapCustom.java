package com.java.demo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HashMapCustom {
    private Entry[] table; // Array of Entry .
    private int capacity; // Capacity of HashMap
    // constructor

    public HashMapCustom(int capacity) {
        // Initialize the capacity of the HashMapCustom instance
        this.capacity = capacity;
        // Create a new array of Entry objects with the specified capacity
        table = new Entry[capacity];
    }

    // defining the Entry class
    static class Entry {
        int key;
        int value;
        Entry next;

        public Entry(int key, int value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    // method to insert the value in a hashmap
    public void put(int newKey, int data) {

        // calculate hash of key.
        int hash = hash(newKey);
        // create new entry.
        Entry newEntry = new Entry(newKey, data, null);

        if (table[hash] != null && table[hash].key == newKey) // if key already present the update the value
        {
            table[hash].value = data;
            return;
        }
        // if table location does not contain any entry, store entry there.
        if (table[hash] == null) {
            table[hash] = newEntry;
        } else { // insert the data into linkedlist at hash index
            Entry current = table[hash];
            while (current.next != null) {
                if (current.key == newKey) {
                    current.value = data;
                    return;
                }
                current = current.next;
            }
            if (current.key == newKey) // if key is already present then update the value
            {
                current.value = data;
                return;
            }
            current.next = newEntry;

        }
    }

    // method to get value from the key
    public int get(int key) {
        int hash = hash(key);
        if (table[hash] == null) {
            return -1;
        } else {
            Entry temp = table[hash];
            while (temp != null) {
                if (temp.key == key) // temp.key.equals(key)
                    return temp.value;
                temp = temp.next; // return value corresponding to key.
            }
            return -1; // returns null if key is not found.
        }
    }

    // method to remove the key & value in the hashmap
    public boolean remove(int deleteKey) {

        int hash = hash(deleteKey);

        if (table[hash] == null) {
            return false;
        } else {
            Entry previous = null;
            Entry current = table[hash];

            while (current != null) { // we have reached last entry node of bucket.
                if (current.key == deleteKey) {
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

    // method to display the data
    public void display() {
        System.out.println("Hashmap :");
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                Entry entry = table[i];
                while (entry != null) {
                    System.out.println(entry.key + "->" + entry.value);
                    entry = entry.next;
                }
            }
        }

    }

    // method to search the key by it's value
    public void search(int value) {
        boolean isfound = false;
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                Entry entry = table[i];
                while (entry != null) {
                    if (value == entry.value) {
                        System.out.println(entry.key + "->" + entry.value + " is present in the hash map");
                        isfound = true;
                    }
                    entry = entry.next;
                }
            }
        }

        if (!isfound) {
            System.out.println("value is not found in a hashmap");
        }

    }

    // method to calculate the hash of the key
    private int hash(int key) {
        return Math.abs(key) % capacity;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("Enter the size of Hashmap");
        Scanner sc = new Scanner(System.in);
        int capacityOfHashmap = sc.nextInt();
        HashMapCustom hmap = new HashMapCustom(capacityOfHashmap);

        boolean flag = true;
        // running the while loop till flag is false
        while (flag) {
            System.out.println("Choose an option from the Dashboard given below:\n");
            System.out.println("1. Insert a key value in Hashmap");
            System.out.println("2. Delete a key value in Hashmap");
            System.out.println("3. Get value of the key");
            System.out.println("4. Display the Hashmap ");
            System.out.println("5. Search the value in a Hashmap");
            System.out.println("6. EXIT");
            System.out.println();

            int ch = sc.nextInt();
            if (ch < 1 || ch > 6) {
                System.out.println("Kindly enter the correct option.");
            } else {
                // Taking the choice from the user and performing the corresponding operation
                try {
                    switch (ch) {
                        case 1:

                            System.out.println("Enter the key and value");
                            int key = sc.nextInt();
                            int value = sc.nextInt();
                            hmap.put(key, value);
                            hmap.display();

                            break;

                        case 2:
                            System.out.println("Enter key to delete the value from the hashmap");
                            int key_delete = sc.nextInt();
                            if (hmap.remove(key_delete)) {
                                System.out.println("Data removed");
                            } else {
                                System.out.println("Unable to remove data");
                            }
                            hmap.display();
                            break;

                        case 3:
                            System.out.println("Enter the key whose value you want");
                            int key_value = sc.nextInt();
                            System.out.println(hmap.get(key_value));
                            break;

                        case 4:
                            hmap.display();
                            break;

                        case 5:
                            System.out.println("Enter a value to search");
                            int search_value = sc.nextInt();

                            hmap.search(search_value);
                            break;

                        case 6:
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
