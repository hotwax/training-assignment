package com.java.demo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class chaining {

    // array of entry
    Entry[] table;
    int capacity; // size of array
    int collision = 0; // to calculate number of collision

    // Constructor
    public chaining(int size) {
        this.capacity = size;
        table = new Entry[capacity];
    }

    // class Entry for key and value
    static class Entry {

        int key;
        int value;
        Entry next;

        // Constructor
        public Entry(int key, int value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    // Method to put data in HashMap
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
            return;
        } else {
            Entry current = table[hash];
            collision++;

            while (current.next != null) // insert the data into linkedlist
            {
                if (current.key == newKey) // if key is already present then update the value
                {

                    current.value = data;
                    return;
                }

                current = current.next;
            }
            if (current.key == newKey) {
                current.value = data;
                return;
            }
            current.next = newEntry;

        }
    }

    // method to get the value form the key
    public int get(int key) {
        int hash = hash(key);
        if (table[hash] == null) { // if array is empty
            return -1; // return -1
        } else {
            Entry temp = table[hash]; // creating new data member
            while (temp != null) {
                if (temp.key == key)
                    return temp.value;
                temp = temp.next; // return value corresponding to key.
            }
            return -1; // returns null if key is not found.
        }
    }

    // method to remove the key value from the HashMap
    public boolean remove(int deleteKey) {

        int hash = hash(deleteKey);

        if (table[hash] == null) { // if the table is totally empty then return false
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

    // Method to display the HashMap

    void display() {
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

    // Method to Search the value in a HashMap

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

    // method to calculate hash value
    private int hash(int key) {
        return Math.abs(key) % capacity;
    }

    public static void main(String[] args) {
        // Creating the object of class chaining
        System.out.println("Enter the size of Hashmap");
        Scanner sc = new Scanner(System.in);
        try {
            int sizeOfHashmap = sc.nextInt();
            chaining chmap = new chaining(sizeOfHashmap);
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
                                System.out.println("Enter a value to search");
                                int search_value = sc.nextInt();

                                chmap.search(search_value);
                                break;

                            case 6:
                                System.out.println(chmap.collision);
                                break;

                            case 7:
                                long t2 = System.currentTimeMillis();
                                System.out.println("Time in milli seconds is " + (t2 - t1));
                                break;

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

        } catch (InputMismatchException ex) {
            System.out.println("Please re-run the program and enter the correct value");
        }
    }
}
