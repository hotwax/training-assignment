package com.java.demo;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class RandomProbing {
    int size;
    Node arr[];
    int collision = 0;
    int randomnumber;
    int numberOfDatapresent = 0;

    // Constructor to initialize the hash map
    RandomProbing(int size) {
        this.size = size;
        this.arr = new Node[size];

    }

    // Node class to store key value
    static class Node {
        int key;
        int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // Display the contents of the hash map
    void display() {
        System.out.println("HashMap : ");
        for (int i = 0; i < size; i++) {
            if (arr[i] != null) {
                System.out.println(arr[i].key + "->" + arr[i].value);
            }
        }
    }

    // Insert the key value in hashmap
    void insert(int key, int value) {
        Random random = new Random();
        Node to_insert = new Node(key, value);
        int hash = hashcode(key);
        if (numberOfDatapresent >= size) // if the hashmap is full
        {
            System.out.println("Hashmap is Full");
            return;
        }
        if (arr[hash] == null) // if array is empty at that hash index then insert the value
        {
            arr[hash] = to_insert;
            return;
        }
        if (arr[hash] != null && arr[hash].key == key) // if key is duplicate then update the value
        {
            arr[hash].value = value;
            return;
        }
        while (arr[hash] != null) // iterate till last node
        {
            if (arr[hash] != null && arr[hash].key == key) {
                collision++;
                arr[hash].value = value;
                return;
            }
            hash = (hash + random.nextInt(arr.length - 1) + 1) % arr.length; // re calculate the hash

        }
        if (arr[hash] == null) // if the array is empty at that index then store the value
        {
            collision++;
            arr[hash] = to_insert;
            numberOfDatapresent++;
            return;
        }

    }

    // Delete a value from the hash map
    void delete(int key) {
        // Calculate the hash code for the value
        int hash = hashcode(key);
        Random random = new Random();
        int counter = 0;
        if (arr[hash] != null && arr[hash].key == key) // if duplicate key then update the value
        {
            arr[hash] = null;
            numberOfDatapresent--;
            System.out.println("Data removed");
            return;
        }
        boolean flag = true;
        while (flag) {
            hash = (hash + random.nextInt(arr.length - 1) + 1) % arr.length;
            counter++;
            if (arr[hash] != null && arr[hash].key == key) {
                arr[hash] = null;
                numberOfDatapresent--;
                System.out.println("Data removed");
                flag = true;
                return;
            }

            if (counter >= 10000) // if loop is run around 10000 and not find empty index in an array then stop
                                  // the loop
            {
                flag = false;
                System.out.println("Unable to delete the data");
                return;
            }
        }

        // Key not found
        System.out.println("Key not found");
    }

    // method to return value by using it's key
    int getValueofKey(int key) {

        Random random = new Random();
        int hash = hashcode(key);
        if (arr[hash] != null && arr[hash].key == key) // if key is already present then update the data
        {
            return arr[hash].value;
        }
        boolean flag = true;
        int counter = 0;
        while (flag) {
            hash = (hash + random.nextInt(arr.length - 1) + 1) % arr.length;
            counter++;
            if (arr[hash] != null && arr[hash].key == key) // if key is already present then update the data
            {
                flag = false;
                return arr[hash].value;
            }

            if (counter > 10000) // if loop is run around 10000 and not find empty index in an array then stop
                                 // the loop
            {
                flag = false;
                return -1;
            }

        }

        return -1;

    }

    // method to get key by it's value
    int getKeybyValue(int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && arr[i].value == value) {
                return arr[i].key;
            }
        }
        return -1;
    }

    // method to get hash value
    int hashcode(int value) {
        // Get the hash code for the given value by taking the absolute value of the
        // value modulo the size of the array
        return Math.abs(value) % size;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the map");
        try {
            int sizeofmap = sc.nextInt();

            RandomProbing rp = new RandomProbing(sizeofmap);
            long t1 = System.currentTimeMillis(); // Variable To calculate the time taken at the start.
            boolean flag = true;
            // running the while loop till flag is false
            while (flag) {
                System.out.println("Choose an option from the Dashboard given below:\n");
                System.out.println("1. Insert a value in Hashmap");
                System.out.println("2. Delete a key value in Hashmap");
                System.out.println("3. Get key from value");
                System.out.println("4. Display the Hashmap ");
                System.out.println("5. Get value from key in a Hashmap");
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
                                rp.insert(key, value);
                                rp.display();
                                break;

                            case 2:
                                System.out.println("Enter key to delete from the hashmap");
                                int key_delete = sc.nextInt();
                                rp.delete(key_delete);
                                ;
                                rp.display();
                                break;

                            case 3:
                                System.out.println("Enter the value whose key you want");
                                int key_value = sc.nextInt();
                                System.out.println(rp.getKeybyValue(key_value));
                                break;

                            case 4:
                                rp.display();
                                break;

                            case 5:
                                System.out.println("Enter the key");
                                int search_value = sc.nextInt();

                                System.out.println(rp.getValueofKey(search_value));
                                break;

                            case 6:
                                System.out.println(rp.collision);
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
