package com.java.demo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HashMapCustom<K, V> {
    private Entry<K, V>[] table; // Array of Entry .
    private int capacity = 4; // Initial capacity of HashMap

    // defining the Entry class
    static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    // constructor
    public HashMapCustom() {
        table = new Entry[capacity];
    }

    // method to insert the value in a hashmap
    public void put(K newKey, V data) {
        if (newKey == null)
            return; // does not allow to store null.

        // calculate hash of key.
        int hash = hash(newKey);
        // create new entry.
        Entry<K, V> newEntry = new Entry<K, V>(newKey, data, null);
        if (table[hash] != null && table[hash].key == newKey) {
            table[hash].value = data;
            return;
        }
        // if table location does not contain any entry, store entry there.
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
        }
    }

    // method to get value from the key
    public V get(K key) {
        int hash = hash(key);
        if (table[hash] == null) {
            return null;
        } else {
            Entry<K, V> temp = table[hash];
            while (temp != null) {
                if (temp.key.equals(key))
                    return temp.value;
                temp = temp.next; // return value corresponding to key.
            }
            return null; // returns null if key is not found.
        }
    }

    // method to remove the key & value in the hashmap
    public boolean remove(K deleteKey) {

        int hash = hash(deleteKey);

        if (table[hash] == null) {
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

    // method to display the data
    public void display() {
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

    // method to search the key by it's value
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

    // method to calculate the hash of the key
    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        HashMapCustom<Integer, Integer> hmap = new HashMapCustom<Integer, Integer>();

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
            Scanner sc = new Scanner(System.in);
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
                            hmap.remove(key_delete);
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
