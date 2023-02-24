package com.java.demo;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class RandomProbing {
    int size;
    int arr[];
    int collision = 0;
    int randomnumber;
    int numberofdatapresent = 0;

    // Constructor to initialize the hash map
    RandomProbing(int size) {
        this.size = size;
        this.arr = new int[size];
        // Initialize all elements in the array to -1 (empty)
        for (int i = 0; i < size; i++) {
            arr[i] = -1;
        }
        // Generate a random number to be used for probing
        Random random = new Random();
        randomnumber = random.nextInt(size);
    }

    // Check if the hash map is empty
    boolean isempty() {
        return numberofdatapresent != size;
    }

    // Get the size of the hash map
    int getsizeofhashmap() {
        return size;
    }

    // Display the contents of the hash map
    void display() {
        for (int i = 0; i < size; i++) {
            if (arr[i] != -1) {
                System.out.println(i + "->" + arr[i]);
            }
        }
    }

    // Insert a value into the hash map
    void insert(int value) {
        // If the hash map is full, return an error message
        if (numberofdatapresent == size) {
            System.out.println("Hashmap is full");
            return;
        }
        // Calculate the hash code for the value
        int hash = hashcode(value);
        int start = hash;
        while (true) {
            // If the current slot in the array is empty, insert the value
            if (arr[hash] == -1) {
                arr[hash] = value;
                numberofdatapresent++;
                break;
            }
            // If the current slot is not empty, use linear probing to find the next
            // available slot
            hash = (hash + randomnumber * collision) % size;
            collision++;
            // If we have wrapped around and returned to the starting point, exit the loop
            if (start == hash) {
                return;
            }
        }
    }

    // Delete a value from the hash map
    void delete(int value) {
        // Calculate the hash code for the value
        int hash = hashcode(value);
        int start = hash;
        while (true) {
            // If we find the value in the hash map, delete it
            if (arr[hash] == value) {
                arr[hash] = -1;
                numberofdatapresent--;
                break;
            }
            // If the current slot in the array is not the value we're looking for, use
            // linear probing to find the next slot to check
            hash = (hash + randomnumber * collision) % size;
            // If we have wrapped around and returned to the starting point, exit the loop
            if (start == hash) {
                System.out.println("Key not found");
                return;
            }
        }
    }

    int getValueofKey(int value) {
        // Get the hash code for the given value
        int hash = hashcode(value);
        int start = hash;
        while (true) {
            // If the value is found at the current hash index, return the index
            if (arr[hash] == value) {
                return hash;
            }
            // If there is a collision, rehash the value
            hash = (hash + randomnumber * collision) % size;
            // If we've looped through the entire array and haven't found the key, it's not
            // present in the hash map
            if (start == hash) {
                System.out.println("Key not found");
                return -1;
            }
        }
    }

    // method to get key by it's value
    int getKeybyValue(int key) {
        // Return the value stored at the given key
        return arr[key];
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
        int sizeofmap = sc.nextInt();
        RandomProbing rp = new RandomProbing(5);
        long t1 = System.currentTimeMillis(); // Variable To calculate the time taken at the start.
        boolean flag = true;
        // running the while loop till flag is false
        while (flag) {
            System.out.println("Choose an option from the Dashboard given below:\n");
            System.out.println("1. Insert a value in Hashmap");
            System.out.println("2. Delete a key value in Hashmap");
            System.out.println("3. Get value from key");
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

                            System.out.println("Enter the value to be inserted");
                            int value = sc.nextInt();
                            rp.insert(value);
                            rp.display();
                            break;

                        case 2:
                            System.out.println("Enter value to delete from the hashmap");
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
                            System.out.println("Enter the value");
                            int search_value = sc.nextInt();

                            System.out.println(rp.getValueofKey(search_value));
                            break;

                        case 6:
                            System.out.println(rp.collision);
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
