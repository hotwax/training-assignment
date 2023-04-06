package com.java.demo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LinearProbing {

    node arr[]; // array to hold the hash map
    int size; // size of the hash table
    int collision = 0; // variable to keep track of the number of collisions occurred during insertion
    int noOfDataPresent = 0;

    // constructor
    LinearProbing(int size) {
        arr = new node[size];
        this.size = size;

    }

    // creating node class to store key and value
    static class node {
        int key;
        int value;

        public node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // method to calculate hashcode for a given key
    int hashCode(int key) {
        return Math.abs(key) % size; // using absolute value to handle negative input key
    }

    // method to insert a value in the hash table
    void insert(int key, int value) {
        node to_insert_value = new node(key, value);
        int hash = hashCode(key);
        if (arr.length <= noOfDataPresent) // if hashmap is full
        {
            System.out.println("Hashmap Full");
            return;
        }
        if (arr[hash] != null && arr[hash].key == key) // if the key is already present then update the value
        {
            arr[hash].value = value;
            return;
        } else if (arr[hash] == null) // if the slot corresponding to the hash code is empty, insert the value
        {
            arr[hash] = to_insert_value;
            noOfDataPresent++;
            return;
        } else // if the slot corresponding to the hash code is occupied, handle collision by
               // probing for the next empty slot
        {

            for (int i = hash + 1; i < size; i++) // probing towards the right of the hash code
            {
                collision++;
                if (arr[hash].key == key) // if the key already exists in the hash table, return
                {
                    arr[hash].value = value;
                    return;
                }
                if (arr[i] == null) // if an empty slot is found, insert the value
                {
                    arr[i] = to_insert_value;
                    noOfDataPresent++;
                    return;
                }
            }
            for (int i = 0; i < hash; i++) // probing towards the left of the hash code
            {
                collision++;
                if (arr[hash].key == key) // if the key already exists in the hash table, return
                {
                    arr[hash].value = value;
                    return;
                }
                if (arr[i] == null) // if an empty slot is found, insert the value
                {
                    arr[i] = to_insert_value;
                    noOfDataPresent++;
                    return;
                }
            }
        }

        System.out.println("No value inserted " + value); // if no empty slot is found, the value cannot be inserted

    }

    // method to delete a value from the hash table
    void delete(int key) {
        if (noOfDataPresent == 0) // if no value present in a hashmap
        {
            System.out.println("No value is present in a hashmap ");
            return;
        }
        int hash = hashCode(key);
        if (arr[hash].key == key) // if key found then delete the data
        {
            arr[hash] = null;
            noOfDataPresent--;
            System.out.println("Data deleted");
            return;
        }
        for (int i = hash + 1; i < size; i++) // searching towards the right of the hash code
        {
            if (arr[i].key == key) // if the value is found, delete it and return
            {
                arr[i] = null;
                noOfDataPresent--;
                System.out.println("Data deleted");
                return;
            }
        }
        for (int i = 0; i < hash; i++) // searching towards the left of the hash code
        {
            if (arr[i].key == key) // if the value is found, delete it and return
            {
                arr[i] = null;
                noOfDataPresent--;
                System.out.println("Data deleted");
                return;
            }
        }
        System.out.println("Unable to delete the data"); // if key is not present then delete it

    }

    // Method to display the hashmap
    void display() {
        System.out.println("Hashmap :");
        for (int i = 0; i < size; i++) {
            if (arr[i] != null) {
                System.out.println(arr[i].key + " ->" + arr[i].value);
            }
        }
    }

    // Method to get value by key
    void getValueByKey(int key) {
        int hash = hashCode(key);

        if (arr[hash] != null) {
            System.out.println(arr[hash].value);
        } else {
            System.out.println("Value can not found with " + key + " key");
        }
    }

    // Method to search for a value in the hashmap
    void searchByValue(int value) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && arr[i].value == value) {
                System.out.println(value + " is present at index " + arr[i].key);
                return;
            }
        }
        System.out.println("Value not found");
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the map");
        try {
            int sizeOfMap = sc.nextInt();

            LinearProbing lp = new LinearProbing(sizeOfMap);
            long t1 = System.currentTimeMillis(); // Variable To calculate the time taken at the start.
            boolean flag = true;
            // running the while loop till flag is false
            while (flag) {
                System.out.println("Choose an option from the Dashboard given below:\n");
                System.out.println("1. Insert a value in Hashmap");
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

                                System.out.println("Enter the key and value to be inserted");
                                int key = sc.nextInt();
                                int value = sc.nextInt();
                                lp.insert(key, value);
                                lp.display();
                                break;

                            case 2:
                                System.out.println("Enter  to delete from the hashmap");
                                int key_delete = sc.nextInt();
                                lp.delete(key_delete);
                                lp.display();
                                break;

                            case 3:
                                System.out.println("Enter the key whose value you want");
                                int key_value = sc.nextInt();
                                lp.getValueByKey(key_value);
                                break;

                            case 4:
                                lp.display();
                                break;

                            case 5:
                                System.out.println("Enter the value to be searched");
                                int search_value = sc.nextInt();

                                lp.searchByValue(search_value);
                                break;

                            case 6:
                                System.out.println(lp.collision);
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
